package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.SchoolDTO;
import lk.ijse.aad.entity.School;
import lk.ijse.aad.repository.SchoolRepository;
import lk.ijse.aad.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }
    @Override
    public SchoolDTO saveSchool(SchoolDTO schoolDTO) {
        try {
            if (schoolDTO == null) {
                log.warn("SchoolDTO is null");
                throw new IllegalArgumentException("SchoolDTO cannot be null");
            }
            if (schoolDTO.getName() == null) {
                log.warn("SchoolDTO has missing name");
                throw new IllegalArgumentException("Name is required");
            }
            if (schoolDTO.getLocation() == null) {
                log.warn("SchoolDTO has missing location");
                throw new IllegalArgumentException("Location is required");
            }
            School school = new School();
            school.setName(schoolDTO.getName());
            school.setLocation(schoolDTO.getLocation());
            School savedSchool = schoolRepository.save(school);
            log.info("School saved successfully: {}", savedSchool.getId());
            return new SchoolDTO(savedSchool.getId(), savedSchool.getName(), savedSchool.getLocation());
        } catch (Exception e) {
            log.error("Error saving school: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<SchoolDTO> getAllSchools() {
        try {
            List<School> schools = schoolRepository.findAll();
            List<SchoolDTO> schoolDTOS =  new ArrayList<>();
            for (School school : schools) {
                schoolDTOS.add(new SchoolDTO(school.getId(), school.getName(), school.getLocation()));
            }
            return schoolDTOS;
        } catch (Exception e) {
            log.error("Error fetching schools: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public SchoolDTO getSchoolById(Long id) {
        try {
            Optional<School> school = schoolRepository.findById(id);
            if (school.isEmpty()) {
                log.warn("School not found: {}", id);
                return null;
            }
            return new SchoolDTO(school.get().getId(), school.get().getName(), school.get().getLocation());
        } catch (Exception e) {
            log.error("Error fetching school: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public SchoolDTO updateSchool(SchoolDTO schoolDTO) {
        try {
            if (schoolDTO == null) {
                log.warn("SchoolDTO is null");
                throw new IllegalArgumentException("SchoolDTO cannot be null");
            }
            if (schoolDTO.getName() == null) {
                log.warn("SchoolDTO has missing name");
                throw new IllegalArgumentException("Name is required");
            }
            if (schoolDTO.getLocation() == null) {
                log.warn("SchoolDTO has missing location");
                throw new IllegalArgumentException("Location is required");
            }
            Optional<School> school = schoolRepository.findById(schoolDTO.getId());
            if (school == null) {
                log.warn("School not found: {}", schoolDTO.getId());
                return null;
            }
            School schoolGet = school.get();
            schoolGet.setName(schoolDTO.getName());
            schoolGet.setLocation(schoolDTO.getLocation());
            School updatedSchool = schoolRepository.save(schoolGet);
            log.info("School updated successfully: {}", updatedSchool.getId());
            return new SchoolDTO(updatedSchool.getId(), updatedSchool.getName(), updatedSchool.getLocation());
        } catch (Exception e) {
            log.error("Error updating school: {}", e.getMessage());
            throw e;
        }
    }
}
