package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.StudentDTO;
import lk.ijse.aad.entity.Student;
import lk.ijse.aad.repository.StudentRepository;
import lk.ijse.aad.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository  studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
       try {
           if (studentDTO == null) {
               log.warn("StudentDTO is null");
               throw new IllegalArgumentException("StudentDTO cannot be null");
           }
           if (studentDTO.getFirstName() == null || studentDTO.getLastName() == null) {
               log.warn("StudentDTO has missing required fields");
               throw new IllegalArgumentException("First name and last name are required");
           }
           if (studentDTO.getDOB() == null) {
               log.warn("StudentDTO has missing DOB");
               throw new IllegalArgumentException("DOB is required");
           }
           if (studentDTO.getAddress() == null) {
               log.warn("StudentDTO has missing address");
               throw new IllegalArgumentException("Address is required");
           }
            Student student = new Student();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setDOB(studentDTO.getDOB());
            student.setAddress(studentDTO.getAddress());

            Student studentSave = studentRepository.save(student);
            log.info("Student saved successfully: {}", studentSave.getId());
            return new StudentDTO(studentSave.getId(), studentSave.getFirstName(), studentSave.getLastName(), studentSave.getDOB(), studentSave.getAddress());
        } catch (Exception e) {
           log.info("Error saving student: {}", e.getMessage());
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        try {
            List<Student> students = studentRepository.findAll();
            List<StudentDTO> studentDTOS =  new java.util.ArrayList<>();
            for (Student student : students) {
                studentDTOS.add(new StudentDTO(student.getId(), student.getFirstName(), student.getLastName(), student.getDOB(), student.getAddress()));
            }
            return studentDTOS;
        } catch (Exception e) {
            log.info("Error fetching students: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student == null) {
                log.warn("Student not found: {}", id);
                return null;
            }
            Student studentGet = student.get();
            return new StudentDTO(studentGet.getId(), studentGet.getFirstName(), studentGet.getLastName(), studentGet.getDOB(), studentGet.getAddress());
        } catch (Exception e) {
            log.info("Error fetching student: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        try {
            if (studentDTO == null) {
                log.warn("StudentDTO is null");
                throw new IllegalArgumentException("StudentDTO cannot be null");
            }
            if (studentDTO.getFirstName() == null || studentDTO.getLastName() == null) {
                log.warn("StudentDTO has missing required fields");
                throw new IllegalArgumentException("First name and last name are required");
            }
            if (studentDTO.getDOB() == null) {
                log.warn("StudentDTO has missing DOB");
                throw new IllegalArgumentException("DOB is required");
            }
            if (studentDTO.getAddress() == null) {
                log.warn("StudentDTO has missing address");
                throw new IllegalArgumentException("Address is required");
            }
            Student student = new Student();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setDOB(studentDTO.getDOB());
            student.setAddress(studentDTO.getAddress());

            Student studentUpdate = studentRepository.save(student);
            log.info("Student updated successfully: {}", studentUpdate.getId());
            return new StudentDTO(studentUpdate.getId(), studentUpdate.getFirstName(), studentUpdate.getLastName(), studentUpdate.getDOB(), studentUpdate.getAddress());
        } catch (Exception e) {
            log.info("Error updating student: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
