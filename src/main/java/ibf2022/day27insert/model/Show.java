package ibf2022.day27insert.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Show {
    
    private Integer id;
    private String name;
    private String summary;


    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}
 
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}


    public String getSummary() {return this.summary;}
    public void setSummary(String summary) {this.summary = summary;}


    // Create method to convert Json into Show object (needed for stream method)
    public static Show toShow(String jsonStr) {
        Show show = new Show();
        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        JsonObject o = reader.readObject();
        show.setId(o.getInt("id"));
        show.setName(o.getString("name"));
        show.setSummary(o.getString("summary"));
    
        return show;
    }
}
