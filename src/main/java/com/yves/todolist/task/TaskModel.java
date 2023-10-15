package com.yves.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Document(collection = "tasks")
@Data
public class TaskModel {
    @Id
    @Indexed(useGeneratedName = true)
    private UUID id = UUID.randomUUID();
    private String description;

    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String title;
    private String priority;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    
    private UUID idUser;
    private LocalDateTime createdAt = LocalDateTime.now();
}
