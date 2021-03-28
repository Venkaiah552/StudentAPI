package com.ey.batch.student.controller;


import com.ey.batch.student.domain.Address;
import com.ey.batch.student.domain.Student;
import com.ey.batch.student.repository.AddressRepository;
import com.ey.batch.student.repository.StudentRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/all/{name}")
    public Student listStudents(@PathVariable(name = "name") String name){

        Student student = studentRepository.findByName(name);

        // Enrich student data
        // example: call other service(any rest endpoint) to populate required data based on studentID
        Address address = callAddressDetails(student.getStudentId());

        // set Address to student
        student.setAddress(address);

        // Save the Address: if concerned about duplicate date use below code else only save fine
        // Here instead use insert or update
        Optional<Address> existingAddress = addressRepository.findByStudentId(student.getStudentId());
                if(existingAddress.isPresent()){
                    // Do Update existing record
                    address.setCountry("updated country");
                    address.setAddressId(existingAddress.get().getAddressId());
                    BeanUtils.copyProperties(address, existingAddress.get());
                    addressRepository.save(existingAddress.get()); //existing record update
                }else{
                    addressRepository.save(address);
                }

        //return the object

        return student;

    }

    /**
     * This can be any 3rd party rest service
     * @param studentId
     * @return
     */
    private Address callAddressDetails(Long studentId) {

        return Address.builder().studentId(studentId)
                .street("MG Road")
                .city("guntur")
                .state("AP")
                .zipCode("560037")
                .country("IN")
                .build();
    }
}
