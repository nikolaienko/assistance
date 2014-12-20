package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    public String login;
    public Long password;
    public String fullName;
    public boolean isAdmin;
    
    public User() {}
    
    public User(String login, Long password, String fullName) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
    }
 
}
