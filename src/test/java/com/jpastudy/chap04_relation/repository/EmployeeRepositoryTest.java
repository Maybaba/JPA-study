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

    //양방향 연관관계에서 리스트에 데이터 갱신 시 주의사항
    @Test
    @DisplayName("양바향 연관관계에서 연관데이터 수정")
    void changeTest() {
        //given
        //3번 사원의 부서를 2번 부서에서 1번 부서로 수정
        //3번 사원 정보 조회
        Employee employee = employeeRepository.findById(3L).orElseThrow();
        //1번 부서 정보 조회
        Department department = departmentRepository.findById(1L).orElseThrow();

        //when
        //사원정보 수정
        employee.setDepartment(department);
        //이렇게 수정 !! 양뱡향에서는 수정시 반대편도 같이 수정
        department.getEmployees().add(employee);

        employeeRepository.save(employee);

        employee.changeDepartment(department);

        /*
        사원정보다 employ 엔터티에서 수정되어도
        반대편 엔터티인 department에서는 리스트에 바로반영되지 않는다.
        해결방안은 데이터 반대편 엔터티도 같이 수정을 해야 한다. 양쪽관계도 같이 해줘야 한다.
         */

        //then
        //바뀐 부서의 사원목록 조회
        List<Employee> employees = department.getEmployees();
        System.out.println("\n\n\n");
        employees.forEach(System.out::println);
        System.out.println("\n\n\n");
    }

        @Test
        @DisplayName("고아 객체 삭제하기")
        void orphanRemovalTest() {
            //given
            //1번 부서 조회
            Department department = departmentRepository.findById(1L).orElseThrow();
            //1번 부서 사원 목록 조회
            List<Employee> employeeList = department.getEmployees();
            //2번 부서 사원 목록 가져오기
            Employee employee = employeeList.get(1);

            // 사원 목록 삭제하기 : casecade로 연관되어서 삭제되어야 할 것
//            employeeList.remove(employee);
//            employee.setDepartment(null);
              department.removeEmployee(employee);
            //갱신 반영
//            departmentRepository.save(department);
            //then
        }

        @Test
        @DisplayName("양방향 관계에서 리스트에 데이터를 추가하면 DB에도 INSERT된다")
        void cascadePersistT() {
            //given

            //2번 부서 조회
            Department department = departmentRepository.findById(2L).orElseThrow();

            //새로운 사원 생성
            Employee employee = Employee.builder()
                    .name("뽀로로")
                    .build();

            //when
            department.addEmployee(employee);
            //then
    }


    @Test
    @DisplayName("부서가 사라지면 해당 사원도 같이 사라진다.")
    void cascadRemoveTest() {
        //given
        Department department = departmentRepository.findById(2L).orElseThrow();

        //when
        departmentRepository.delete(department);

        //then
    }



}