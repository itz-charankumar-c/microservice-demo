package com.demo.department.service;

import com.demo.department.dto.DepartmentResponseDto;
import com.demo.department.entity.Department;
import com.demo.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentResponseDto createDepartment(Department department) {
        Department saved = departmentRepository.save(department);
        return toDto(saved);
    }

    public Optional<DepartmentResponseDto> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(this::toDto);
    }

    private DepartmentResponseDto toDto(Department d) {
        return DepartmentResponseDto.builder()
                .id(d.getId())
                .name(d.getName())
                .code(d.getCode())
                .description(d.getDescription())
                .build();
    }
}
