package com.jpastudy.chap04_relation.repository;

import com.jpastudy.chap04_relation.entity.Department;
import com.jpastudy.chap04_relation.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class EmployeeRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("특정 부서를 조회하면 해당 소속부서원들이 함께 조회된다, ")
    void findDeptEvent() {
        //given
        Long id = 1L;

        //when
        Optional<Department> department = departmentRepository.findById(id);

        //then
        System.out.println("department = " + department);
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);


    }


}