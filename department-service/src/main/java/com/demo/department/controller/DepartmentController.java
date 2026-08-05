package com.demo.department.controller;

import com.demo.department.dto.DepartmentResponseDto;
import com.demo.department.entity.Department;
import com.demo.department.service.DepartmentService;
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

// POST /api/departments  – create | GET /api/departments/{id} – fetch
@Slf4j
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@Valid @RequestBody Department department) {
        log.info("POST /api/departments - creating department with code={}", department.getCode());
        DepartmentResponseDto created = departmentService.createDepartment(department);
        log.info("POST /api/departments - department created with id={}", created.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id) {
        log.info("GET /api/departments/{} - fetching department", id);
        return departmentService.getDepartmentById(id)
                .map(d -> { log.info("GET /api/departments/{} - found name={}", id, d.getName()); return ResponseEntity.ok(d); })
                .orElseGet(() -> { log.warn("GET /api/departments/{} - not found", id); return ResponseEntity.notFound().build(); });
    }
}
