package team1.spring.training.DAO.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="table_files")
public class UploadFile {

    @Id
    @GeneratedValue
    @JsonProperty
    Long id;

    @Column(name="location")
    @JsonProperty
    private String location;

    @Column(name="time")
    @JsonProperty
    private String timestamp;

    public UploadFile(long id, String location, String timestamp) {
        this.id = id;
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
}
