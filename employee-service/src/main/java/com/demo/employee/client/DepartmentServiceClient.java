package com.demo.employee.client;

import com.demo.employee.dto.DepartmentDto;
import com.demo.employee.exception.DepartmentServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/** Calls the department-service REST API. Single responsibility: HTTP communication only. */
@Slf4j
@Component
@RequiredArgsConstructor
public class DepartmentServiceClient {

    private final RestClient departmentRestClient;

    public DepartmentDto getDepartmentById(Long id) {
        try {
            return departmentRestClient.get()
                    .uri("/api/departments/{id}", id)
                    .retrieve()
                    .body(DepartmentDto.class);
        } catch (RestClientException ex) {
            log.error("Failed to fetch department id={}: {}", id, ex.getMessage());
            throw new DepartmentServiceException(
                    "Department service unavailable while fetching department id=" + id);
        }
    }
}

