package team1.spring.training.DAO.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_user")
public class User {

    @Id
    @JsonProperty
    private String user;

    @JsonProperty
    private String password;
}
