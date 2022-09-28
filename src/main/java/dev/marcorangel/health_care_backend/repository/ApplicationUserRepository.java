package dev.marcorangel.health_care_backend.repository;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserRepository  extends JpaRepository<ApplicationUser, String> {
    @Query("SELECT u FROM ApplicationUser u WHERE  u.user_email = :user_email")
    Optional<ApplicationUser> findByEmail(@Param("user_email") String user_email);
    @Query("SELECT u FROM ApplicationUser u WHERE  u.user_name = :user_name")
    Optional<ApplicationUser> findByUsername(@Param("user_name") String user_name);
}