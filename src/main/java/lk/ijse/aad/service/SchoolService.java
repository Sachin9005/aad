package lk.ijse.aad.service;

import lk.ijse.aad.dto.SchoolDTO;

import java.util.List;

public interface SchoolService {
    SchoolDTO saveSchool(SchoolDTO schoolDTO);
    List<SchoolDTO> getAllSchools();
    SchoolDTO getSchoolById(Long id);
    SchoolDTO updateSchool(SchoolDTO schoolDTO);
}
