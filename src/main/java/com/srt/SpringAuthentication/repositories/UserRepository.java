package com.srt.SpringAuthentication.repositories;

import com.srt.SpringAuthentication.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> getUserByUsername(String username);
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.firstName = :firstName WHERE username = :username")
    void updateFirstNameByUsername(@Param("firstName") String firstName, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastName = :lastName WHERE username = :username")
    void updateLastNameByUsername(@Param("lastName") String lastName, @Param("username") String username);
}
