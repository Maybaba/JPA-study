package com.jpastudy.chap04_relation.repository;

import com.jpastudy.chap04_relation.entity.Department;
import com.jpastudy.chap04_relation.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class DepartmentRepositoryTest {
    //트랜잭션을 보고 싶을 때 yml 설정 : create -> update 로 바꿔주기

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

//    @BeforeEach
//    void beforeInsert() {
//
//        Department d1 = Department.builder()
//                .name("영업부")
//                .build();
//        Department d2 = Department.builder()
//                .name("개발부")
//                .build();
//
//        departmentRepository.save(d1);
//        departmentRepository.save(d2);
//
//        Employee e1 = Employee.builder()
//                .name("라이옹")
//                .department(d1)
//                .build();
//        Employee e2 = Employee.builder()
//                .name("어피치")
//                .department(d1)
//                .build();
//        Employee e3 = Employee.builder()
//                .name("프로도")
//                .department(d2)
//                .build();
//        Employee e4 = Employee.builder()
//                .name("네오")
//                .department(d2)
//                .build();
//
//        employeeRepository.save(e1);
//        employeeRepository.save(e2);
//        employeeRepository.save(e3);
//        employeeRepository.save(e4);
//    }

    @Test
    @DisplayName("dummyTest")
    void ttttest() {
        //given
        Long id = 2L;
        //when
        Employee employee = employeeRepository.findById(id).orElseThrow();
        /*
        select 쿼리가 실행이 안됐는데 조회가 정상적으로 된 이유는
        JPA에 존재하는 영속성 컨텍스트라는 개념 때문에
        하나의 트랜잭션에서 insert가 일어났을 때 저장한 객체를
        영속성 컨텐스트라는 영역에 저장해두고 다음번에 트랜잭션
        안에서 다시 조회할 시 select문을 실행하지 않고 성능최적화를 위해 컨텍스트 안에서 가져온다.
         */
        //then
        System.out.println("employee = " + employee);


    }
}