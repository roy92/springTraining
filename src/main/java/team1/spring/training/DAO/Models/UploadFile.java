package team1.spring.training.DAO.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="table_file")
public class UploadFile {

    @Id
    @JsonProperty
    @Getter @Setter
    private String name;

    @JsonProperty
    @Getter @Setter
    private String location;

    @Column(name="time")
    @JsonProperty
    @Getter @Setter
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
}
