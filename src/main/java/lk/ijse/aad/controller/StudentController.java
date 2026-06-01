package lk.ijse.aad.controller;

import lk.ijse.aad.dto.StudentDTO;
import lk.ijse.aad.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO searchStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }
}
