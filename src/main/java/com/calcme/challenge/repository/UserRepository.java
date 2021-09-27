package com.calcme.challenge.repository;

import com.calcme.challenge.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User>findAll();

}