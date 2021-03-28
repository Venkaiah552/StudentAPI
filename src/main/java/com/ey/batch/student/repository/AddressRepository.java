package com.ey.batch.student.repository;

import com.ey.batch.student.domain.Address;
import com.ey.batch.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByStudentId(Long studentId);
}
