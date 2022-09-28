package dev.marcorangel.health_care_backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ApplicationUser implements UserDetails {
    @Id
    public String user_name;
    public String user_email;
    public String password;
    public String user_mobile;
    public String location;
    public String token;

    public ApplicationUser(String user_name, String user_email, String password, String user_mobile, String location) {
        super();
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.user_mobile = user_mobile;
        this.location = location;
    }

    public ApplicationUser(String user_name, String password) {
        super();
        this.user_name = user_name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApplicationUser that = (ApplicationUser) o;
        return user_name != null && Objects.equals(user_name, that.user_name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return user_email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
