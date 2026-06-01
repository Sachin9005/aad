package lk.ijse.aad.service;

import lk.ijse.aad.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO updateStudent(StudentDTO studentDTO);

}
