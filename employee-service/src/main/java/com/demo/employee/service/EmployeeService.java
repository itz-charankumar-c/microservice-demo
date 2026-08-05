package com.demo.employee.service;

import com.demo.employee.client.DepartmentServiceClient;
import com.demo.employee.dto.DepartmentDto;
import com.demo.employee.dto.EmployeeDetailsDto;
import com.demo.employee.entity.Employee;
import com.demo.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentServiceClient departmentServiceClient;

    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /** Fetches employee from DB, then calls department-service to build the combined response. */
    public EmployeeDetailsDto getEmployeeDetails(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id=" + id));

        DepartmentDto department = departmentServiceClient.getDepartmentById(employee.getDepartmentId());

        return EmployeeDetailsDto.builder()
                .id(employee.getId())
                .name(employee.getFirstName() + " " + employee.getLastName())
                .department(new EmployeeDetailsDto.DepartmentInfo(department.getId(), department.getName()))
                .build();
    }
}
