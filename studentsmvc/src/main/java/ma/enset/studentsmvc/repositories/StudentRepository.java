package ma.enset.studentsmvc.repositories;

import ma.enset.studentsmvc.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findStudentByNomContains(String keyword, Pageable pageable);
}
