package com.Joel.todolistapp.data.reposittories;

import com.Joel.todolistapp.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findFirstUserByUsername(String username);

}
