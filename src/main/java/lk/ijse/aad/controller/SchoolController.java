package lk.ijse.aad.controller;

import lk.ijse.aad.dto.SchoolDTO;
import lk.ijse.aad.service.SchoolService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/schools")
public class SchoolController {
    private final SchoolService schoolService;
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolDTO saveSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.saveSchool(schoolDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SchoolDTO> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolDTO searchSchool(@PathVariable Long id) {
        return schoolService.getSchoolById(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     public SchoolDTO updateSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.updateSchool(schoolDTO);
    }


}
