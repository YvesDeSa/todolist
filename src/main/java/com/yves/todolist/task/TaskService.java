package com.yves.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yves.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskService {
    @Autowired
    private ITaskRepository taskRepository;

    public ResponseEntity<?> saveTaskModel(TaskModel taskModel){
        var task = taskRepository.save(taskModel);
        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.badRequest().body("A Data de início / data final deve ser maior que a data atual. ");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.badRequest().body("A Data final deve ser maior que a data inícial. ");
        }

        return ResponseEntity.ok().body(task);
 
    }

     public List<TaskModel> getAllTaskById(UUID idUser){
        var tasks = this.taskRepository.findByIdUser(idUser);
        return tasks;
    }

    public ResponseEntity<?> updateTaskModel(TaskModel taskModel, UUID id, HttpServletRequest request){
        var task = this.taskRepository.findById(id).orElse(null);

        var idUser = request.getAttribute("idUser");

        if(task == null){
            return ResponseEntity.badRequest().body("Tarefa não encontrada.");
        }

        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.badRequest().body("Usuário não tem permissão para fazer alteração nessa tarefa.");
        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        
        return ResponseEntity.ok().body(taskUpdated);
    }
}
