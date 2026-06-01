package lk.ijse.aad.service;

import lk.ijse.aad.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
}
