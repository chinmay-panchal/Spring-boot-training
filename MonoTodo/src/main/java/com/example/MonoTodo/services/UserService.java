package com.example.MonoTodo.services;

import com.example.MonoTodo.dto.TaskDto;
import com.example.MonoTodo.dto.UserDto;
import com.example.MonoTodo.entities.Priority;
import com.example.MonoTodo.entities.Task;
import com.example.MonoTodo.entities.User;
import com.example.MonoTodo.repositories.PriorityRepository;
import com.example.MonoTodo.repositories.TaskRepository;
import com.example.MonoTodo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public UserDto createUser(User user){
        User newUser = userRepository.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }

    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User not found with ID: "+id));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto createTaskById(Long id, Task task){
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User not found with ID: "+id));
        task.setUser(user);

        if (user.getTask() == null || user.getTask().isEmpty()) {
            System.out.println("Executed user task list is null");
            user.setTask(new ArrayList<>());
        }

        // Add debugging here
        System.out.println("About to create task: " + task.getName());
        if (task.getPriority() != null) {
            System.out.println("Priority: " + task.getPriority().getPriority());
            //first save the priority in the priority db table
            Priority priority = priorityRepository.save(task.getPriority());
            task.setPriority(priority);
            System.out.println("Priority ID: " + task.getPriority().getId());
        }

        try {
            // save the task in the task db
            Task task1 = taskService.createTask(id, task);
            System.out.println("Task created successfully with ID: " + task1.getId());

            // retrieve or fetch user's task
            List<Task> listOfTask = taskService.getTasksByUser(id);
            List<TaskDto> listOfTaskDto = new ArrayList<>();

            for (Task t : listOfTask){
                TaskDto taskDto = new TaskDto();
                taskDto = modelMapper.map(t, TaskDto.class);
//                taskDto.setId(t.getId());
//                taskDto.setTitle(t.getName());
//                taskDto.setStatus(t.getStatus().toString());
//                taskDto.setPriority(t.getPriority().getPriority());
                listOfTaskDto.add(taskDto);
            }

            user.setTask(listOfTask);
            userDto.setId(id);
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setTasks(listOfTaskDto);
            return userDto;
//            return modelMapper.map(user, UserDto.class);
        } catch (Exception e) {
            System.out.println("Error creating task: " + e.getMessage());
            throw e;
        }
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserDto>>() {}.getType();
        return modelMapper.map(users, listType);
    }
}
