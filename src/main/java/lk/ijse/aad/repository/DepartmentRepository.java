package lk.ijse.aad.repository;

import lk.ijse.aad.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Query("SELECT d FROM Department d WHERE " +
            "(?1 IS NULL OR d.departmentName LIKE %?1%) AND " +
            "(?2 IS NULL OR d.departmentLocation LIKE %?2%)")
    List<Department> findByFiltering(String depName, String depLocation);
}
