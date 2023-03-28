package ibf2022.day27insert.model;

public class Review {
    private int id; //movieid
    private String username;
    private Float rating;
    private String comments;


    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return this.username;}
    public void setUsername(String username) {this.username = username;}

    public Float getRating() {return this.rating;}
    public void setRating(Float rating) {this.rating = rating;}

    public String getComments() {return this.comments;}
    public void setComments(String comments) {this.comments = comments;}


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", rating='" + getRating() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }

}
