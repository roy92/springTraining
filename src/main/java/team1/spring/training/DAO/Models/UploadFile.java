package team1.spring.training.DAO.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="table_file")
public class UploadFile {

    @Id
    @JsonProperty
    @Column(name="name")
    String name;

    @Column(name="location")
    @JsonProperty
    private String location;

    @Column(name="time")
    @JsonProperty
    private String timestamp;

    public UploadFile(String name, String location, String timestamp) {
        this.name = name;
        this.location = location;
        this.timestamp = timestamp;
    }

    public UploadFile(String location, String timestamp) {
        this.location = location;
        this.timestamp = timestamp;
    }

    public UploadFile() {
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
