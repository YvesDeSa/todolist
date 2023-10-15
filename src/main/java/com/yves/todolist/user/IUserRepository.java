package com.yves.todolist.user;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
