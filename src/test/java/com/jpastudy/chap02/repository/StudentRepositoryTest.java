package com.jpastudy.chap02.repository;

import com.jpastudy.chap02.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 꼭 꼭 jpa 쓸 때는 test, service에 사용하기 !
@Rollback(false) //롤백이 기본적으로 되어있다. 로그 보고 싶으면 false
class StudentRepositoryTest {

        @Autowired
        StudentRepository studentRepository;
        @BeforeEach
        void insertData() {
            Student s1 = Student.builder()
                    .name("쿠로미")
                    .city("청양군")
                    .major("경제학")
                    .build();

            Student s2 = Student.builder()
                    .name("춘식이")
                    .city("서울시")
                    .major("컴퓨터공학")
                    .build();

            Student s3 = Student.builder()
                    .name("어피치")
                    .city("제주도")
                    .major("화학공학")
                    .build();

            studentRepository.save(s1);
            studentRepository.save(s2);
            studentRepository.save(s3);
        }

    @Test
    @DisplayName("이름이 춘식이인 학생의 모든 정보를 조회한다.")
    void findByNameTest() {
        //given
        String name = "춘식이";
        //when
        List<Student> students = studentRepository.findByName(name);
        //then
        assertEquals(1, students.size());

        System.out.println("\n\n\n\n");
        System.out.println("students.get(0) = " + students.get(0));
        System.out.println("\n\n\n\n");
    }

    @Test
    @DisplayName("도시 이름과 전공으로 학생을 조회")
    void findByCityAndMajor() {
        //given
        String city = "제주도";
        String major = "화학공학";

        List<Student> students = studentRepository.findByCityAndMajor(city, major);
        //then
        System.out.println("\n\n\n\n");

        System.out.println("\n\n\n\n");

    }

    @Test
    @DisplayName("전공이 공학으로 끝나는 학생들 조회")
    void findByMajorContainingT() {
        //given
        String majorContaining = "공학";

        //when
        List<Student> byMajorContaining = studentRepository.findByMajorContaining(majorContaining);

        //then
        System.out.println("\n\n\n\n");
        byMajorContaining.forEach(System.out::println);
        System.out.println("\n\n\n\n");
    }

    @Test
    @DisplayName("도시 또는 이름으로 학생을 조회")
    void nativeSQLTest() {
        //given
        String name = "춘식이";
        String city = "제주도";
        //when
        List<Student> studentByNameOrCity = studentRepository.getStudentByNameOrCity2(name, city);

        //then

        System.out.println("\n\n\n\n");
        studentByNameOrCity.forEach(System.out::println);
        System.out.println("\n\n\n\n");
    }

    @Test
    @DisplayName("JPQL로 학생 조회하기")
    void jpqlTest() {
        //given
        String city = "제주도";
        //when
        Student student = studentRepository.getByCityWithJPQL(city)
                // 학생이 조회가 안되면 예외를 발생시켜라
                .orElseThrow(() -> new RuntimeException("학생이 없음!"));
        //then
        assertNotNull(student);

        System.out.println("\n\n\nstudent = " + student + "\n\n\n");
//        assertThrows(RuntimeException.class, () -> new RuntimeException());
    }


    @Test
    @DisplayName("JPQL로 특정 이름이 포함환 학생 조회하기")
    void specifyT () {
        //given
        String containingName = "춘";
        //when
        List<Student> students = studentRepository.searchByNameWithJPQL(containingName);

        //then
        System.out.println("\n\n\n");
        students.forEach(System.out::println);
        System.out.println("\n\n\n");
    }

    @Test
    @DisplayName("jqpl로 삭제하기")
    void deleteT() {
         //given
        String name = "어피치";
        String city = "제주도";
        //when
        studentRepository.deleteByNameAndCityWithJPQL(name, city);
        //then
        assertEquals(0, studentRepository.findByName(name).size());
    }

}

