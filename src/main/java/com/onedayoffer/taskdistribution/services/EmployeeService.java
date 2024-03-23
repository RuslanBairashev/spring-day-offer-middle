package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;
import com.onedayoffer.taskdistribution.repositories.EmployeeRepository;
import com.onedayoffer.taskdistribution.repositories.TaskRepository;
import com.onedayoffer.taskdistribution.repositories.entities.Employee;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.DEFAULT_DIRECTION;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDTO> getEmployees(@Nullable String sortDirection) {
        //throw new java.lang.UnsupportedOperationException("implement getEmployees");

        List<Employee> employees;
        if (sortDirection != null) {
            Sort.Direction direction = Sort.Direction.fromString(sortDirection);
            employees = employeeRepository.findAllAndSort(Sort.by(direction, "fio"));
        } else {
            employees = employeeRepository.findAll();
        }

        Type listType = new TypeToken<List<EmployeeDTO>>() {}.getType();
        List<EmployeeDTO> employeeDTOS = modelMapper.map(employees, listType);

        return employeeDTOS;

        // if sortDirection.isPresent() ..
        // Sort.Direction direction = ...
        // employees = employeeRepository.findAllAndSort(Sort.by(direction, "fio"))
        // employees = employeeRepository.findAll()
        // Type listType = new TypeToken<List<EmployeeDTO>>() {}.getType()
        // List<EmployeeDTO> employeeDTOS = modelMapper.map(employees, listType)
    }

    public List<EmployeeDTO> getSortedByIdEmployees(@Nullable String sortDirection) {

        List<Employee> employees;
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        employees = employeeRepository.findAllAndSort(Sort.by(direction, "id"));

        Type listType = new TypeToken<List<EmployeeDTO>>() {}.getType();
        List<EmployeeDTO> employeeDTOS = modelMapper.map(employees, listType);

        return employeeDTOS;
    }

    @Transactional
    public EmployeeDTO getOneEmployee(Integer id) {
        List<EmployeeDTO> employeeDTOS = getSortedByIdEmployees("ASC");
        return employeeDTOS.get(id - 1);

        //throw new java.lang.UnsupportedOperationException("implement getOneEmployee");
    }

    public List<TaskDTO> getTasksByEmployeeId(Integer id) {
        //throw new java.lang.UnsupportedOperationException("implement getTasksByEmployeeId");
        List<TaskDTO> tasks = new ArrayList<>();
        TaskDTO idTask = new TaskDTO();
        idTask.setId(id);
        tasks.add(idTask);

        return tasks;
    }

    @Transactional
    public void changeTaskStatus(Integer taskId, TaskStatus status) {
        throw new java.lang.UnsupportedOperationException("implement changeTaskStatus");
    }

    @Transactional
    public void postNewTask(Integer employeeId, TaskDTO newTask) {
        throw new java.lang.UnsupportedOperationException("implement postNewTask");
    }
}
