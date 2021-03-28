package com.ey.batch.student.repository;

import com.ey.batch.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {

    Student findByName(String studentName);
}
