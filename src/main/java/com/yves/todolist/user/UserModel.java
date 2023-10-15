package com.yves.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "users")
@Data
public class UserModel {
  
  @Id
  private UUID id = UUID.randomUUID();

  @Indexed(unique = true)
  private String username;

  private String name;
  private String password;

  private LocalDateTime createdAt = LocalDateTime.now();
}
