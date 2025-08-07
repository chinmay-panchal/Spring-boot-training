package com.example.todoList.service;

import com.example.todoList.dto.addTodoRequestDto;
import com.example.todoList.entity.Priority;
import com.example.todoList.entity.Status;
import com.example.todoList.entity.TodoList;
import com.example.todoList.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TodoList> getAllTasks() {
        return todoRepository.findAll();
    }

    @Override
    public TodoList getTaskById(Long id) {
        TodoList task = todoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Task not found by Id: "+id));
        return task;
    }

    @Override
    public TodoList createNewTask(addTodoRequestDto task) {
        TodoList newTask = modelMapper.map(task, TodoList.class);
        newTask.setStatus(Status.Pending);
        newTask.setCreatedAt(LocalDate.now());
        return todoRepository.save(newTask);
    }

    @Override
    public void deleteTaskById(Long id) {
        TodoList task = todoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Task not found by Id: "+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoList editTaskById(Long id, addTodoRequestDto update) {
        TodoList task = todoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Task not found by Id: "+id));
        try {
            modelMapper.map(update, task);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid data in update request: " + e.getMessage() +
                            ". Allowed enum values (case-sensitive):\n" +
                            "Priority: Normal, Medium, Urgent\n" +
                            "Status: InProgress, Completed, pending"
            );
        }
        task.setStatus(Status.Pending);
        task.setUpdatedAt(LocalDate.now());
        return todoRepository.save(task);
    }

    @Override
    public TodoList editPartialTask(Long id, Map<String, Object> updates) {
        TodoList task = todoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Task not found by Id: "+id));
        updates.forEach((field, value)-> {
            switch (field){
                case "title": task.setTitle((String) value);
                    break;
                case "description": task.setDescription((String) value);
                     break;
                case "priority": if(value instanceof String){
                    try{
                        task.setPriority(Priority.valueOf((String) value));
                    }
                    catch (IllegalArgumentException e){
                        throw new IllegalArgumentException("Invalid priority value: "+ value + ". Allowed values: Normal, Medium, Urgent");
                    }
                }
                     break;
                case "status": if(value instanceof String){
                    try{
                        task.setStatus(Status.valueOf((String) value));
                    }
                    catch (IllegalArgumentException e){
                        throw new IllegalArgumentException("Invalid status value: "+value+". Allowed values: InProgress, Completed, Pending");
                    }
                }
                    break;
            }
        });
        task.setUpdatedAt(LocalDate.now());
        return todoRepository.save(task);
    }


    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }
}
