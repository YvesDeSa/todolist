package com.yves.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITaskRepository extends MongoRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID idUser);
}
