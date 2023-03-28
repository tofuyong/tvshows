package ibf2022.day27insert;

import org.bson.Document;

import ibf2022.day27insert.model.Review;

public class Utils {
    
    public static Document toDocument(Review review) {
        Document doc = new Document();
        doc.put("name", review.getUsername());
        doc.put("rating", review.getRating());
        doc.put("comments", review.getComments());
        return doc;
    }
    
}
