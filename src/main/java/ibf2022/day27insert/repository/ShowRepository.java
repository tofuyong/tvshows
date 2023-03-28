package ibf2022.day27insert.repository;

import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import ibf2022.day27insert.Utils;
import ibf2022.day27insert.model.Review;
import ibf2022.day27insert.model.Show;

@Repository
public class ShowRepository {

    Logger logger = LoggerFactory.getLogger(ShowRepository.class);

    
    @Autowired
    MongoTemplate mongoTemplate;

    // db.tvshows.distinct("name")
    public List<String> findAllShows() {
        List<String> showNames = mongoTemplate.findDistinct(new Query(), "name", "tvshows", String.class);
        logger.info(">>>>> show names =" + showNames);
        return showNames;
    }

    /*
     * db.tvshows.find(
     * { name: "Under the Dome" },
     * { _id:0, name: 1, rating:1, summary:1 })
     */
    public Show findShowByName(String showName) {
        logger.info(">>>showName: " + showName);  
        Criteria criteria = Criteria.where("name").regex(showName, "i"); 

        Query query = Query.query(criteria);
        query.fields().include("name", "id", "summary").exclude("_id");

       return mongoTemplate.find(query, Document.class, "tvshows")
       		.stream()
            .findFirst()
       		.map(doc -> doc.toJson())
       		.map(Show::toShow)
            .orElse(null); //to deal with Optional
    }


    /*
     * db.tvshows.update(
     *  { id: 123 },
     *  { $push: { reivew: { review } } }
     * )
     */
    public void insertReview(Review review) {
        // Create a document first, to be inserted into the DB
        Document doc = Utils.toDocument(review);
        Criteria criteria = Criteria.where("id").is(review.getId());
        Query query = Query.query(criteria);

        Update updateOps = new Update()
                .push("review", doc);
                
        UpdateResult updated = mongoTemplate.upsert(query, updateOps, Document.class, "tvshows");

        System.out.printf("matched: %d\n", updated.getMatchedCount());
        System.out.printf("modified: %d\n", updated.getModifiedCount());
    }

}
