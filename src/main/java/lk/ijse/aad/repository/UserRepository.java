package lk.ijse.aad.repository;

import lk.ijse.aad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = " SELECT u FROM User u WHERE (?1 IS NULL OR u.firstName LIKE %?1%) AND " +
            "(?2 IS NULL OR u.lastName LIKE %?2%)")
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
