package com.ey.batch.student;

import com.ey.batch.student.domain.Address;
import com.ey.batch.student.domain.Student;
import com.ey.batch.student.repository.AddressRepository;
import com.ey.batch.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(StudentRepository studentRepository,
                                         AddressRepository addressRepository) {
        return args -> {

            // create a Student instance
            Student student = new Student("venki", 32, "venki552@gmail.com");

            // save the parent
            // which will save the child (address) as well
            studentRepository.save(student);
        };
    }
}
