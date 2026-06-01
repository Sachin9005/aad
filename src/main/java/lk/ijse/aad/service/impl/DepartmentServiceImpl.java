package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.DepartmentDTO;
import lk.ijse.aad.entity.Department;
import lk.ijse.aad.repository.DepartmentRepository;
import lk.ijse.aad.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepo = departmentRepository;
    }

    @Override
    public void saveDepartment(DepartmentDTO departmentDTO) {

        Department department = new Department();
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentLocation(departmentDTO.getDepartmentLocation());
        departmentRepo.save(department);

    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        try {
            List<DepartmentDTO> departmentDTOS = new java.util.ArrayList<>();
            List<Department> departments = departmentRepo.findAll();
            for (Department department : departments) {
                DepartmentDTO departmentDTO = new DepartmentDTO();
                departmentDTO.setDepartmentId(department.getDepartmentId());
                departmentDTO.setDepartmentName(department.getDepartmentName());
                departmentDTO.setDepartmentLocation(department.getDepartmentLocation());
                departmentDTOS.add(departmentDTO);
            }
            return departmentDTOS;
        }catch (Exception e) {
            log.error("Error occurred while fetching departments: {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        try {
            Optional<Department> department = departmentRepo.findById(id);
            if (department.isEmpty()) {
                throw new RuntimeException("Department not found with id: " + id);
            }
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartmentId(department.get().getDepartmentId());
            departmentDTO.setDepartmentName(department.get().getDepartmentName());
            departmentDTO.setDepartmentLocation(department.get().getDepartmentLocation());
            return departmentDTO;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        try {
            Optional<Department> departmentOptional = departmentRepo.findById(departmentDTO.getDepartmentId());
            if (departmentOptional.isEmpty()) {
                throw new RuntimeException("Department not found with id: " + departmentDTO.getDepartmentId());
            }
            Department department = departmentOptional.get();
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDepartmentLocation(departmentDTO.getDepartmentLocation());
            Department dep = departmentRepo.save(department);
            log.info("Department updated successfully with id: {}", departmentDTO.getDepartmentId());
            return new DepartmentDTO(dep.getDepartmentId(), dep.getDepartmentName(), dep.getDepartmentLocation());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
