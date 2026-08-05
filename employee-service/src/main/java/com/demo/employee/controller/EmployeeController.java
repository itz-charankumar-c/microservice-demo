package com.demo.employee.controller;

import com.demo.employee.dto.EmployeeDetailsDto;
import com.demo.employee.entity.Employee;
import com.demo.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// POST /api/employees  – create | GET /api/employees/{id} – fetch | GET /api/employees/{id}/details – combined
@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        log.info("POST /api/employees - creating employee with email={}", employee.getEmail());
        Employee created = employeeService.createEmployee(employee);
        log.info("POST /api/employees - employee created with id={}", created.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("GET /api/employees/{} - fetching employee", id);
        return employeeService.getEmployeeById(id)
                .map(e -> { log.info("GET /api/employees/{} - found", id); return ResponseEntity.ok(e); })
                .orElseGet(() -> { log.warn("GET /api/employees/{} - not found", id); return ResponseEntity.notFound().build(); });
    }

    /** Fetches employee + department details via service-to-service call. */
    @GetMapping("/{id}/details")
    public ResponseEntity<EmployeeDetailsDto> getEmployeeDetails(@PathVariable Long id) {
        log.info("GET /api/employees/{}/details - fetching combined employee+department details", id);
        EmployeeDetailsDto details = employeeService.getEmployeeDetails(id);
        log.info("GET /api/employees/{}/details - returning details for employee name={}", id, details.getName());
        return ResponseEntity.ok(details);
    }
}
