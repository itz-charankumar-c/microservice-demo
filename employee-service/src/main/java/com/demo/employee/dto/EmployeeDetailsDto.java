package com.demo.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Combined response for GET /api/employees/{id}/details. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailsDto {

    private Long id;
    private String name;            // firstName + lastName
    private DepartmentInfo department;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentInfo {
        private Long id;
        private String departmentName;  // mapped from DepartmentDto.name
    }
}

