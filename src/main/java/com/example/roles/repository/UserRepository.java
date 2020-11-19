package com.example.roles.repository;

import com.example.roles.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("select e from User e left join UserRole ur on ur.user.login=e.login where e.login=:login")
    Optional<User> findUserByLoginWithRoles(@Param("login") String login);
}
