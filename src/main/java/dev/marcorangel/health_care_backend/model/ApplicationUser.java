package dev.marcorangel.health_care_backend.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Builder
@Data
public class ApplicationUser {
    @Id
    @Column(name = "username")
    public String user_name;
    public String user_email;
    public String password;
    public String user_mobile;
    public String location;

    public ApplicationUser(String user_name, String user_email, String password, String user_mobile, String location) {
        super();
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_mobile = user_mobile;
        this.location = location;
    }

    public ApplicationUser() {
        super();
    }

    public ApplicationUser(String user_name, String password) {
        super();
        this.user_name = user_name;
        this.password = password;
    }

    //    public Date user_dob;

}
