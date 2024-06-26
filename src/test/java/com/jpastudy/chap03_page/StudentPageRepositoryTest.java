package com.jpastudy.chap03_page;

import com.jpastudy.chap02.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback
class StudentPageRepositoryTest {

    @Autowired
    StudentPageRepository repository;

    @BeforeEach
    void bulkInsert() {
        for (int i = 1; i <= 147; i++) {
            Student ss = Student.builder()
                    .city("시골"+i)
                    .name("춘사미"+i)
                    .major("숨쉬기"+i)
                    .build();
            repository.save(ss);
        }
    }

    @Test
    @DisplayName("기본적인 페이지 조회 테스트")
    void basicPageTest() {
        //given
        int pageNo = 1;
        int amount = 10;

        // 페이징 정보 객체 생성 (Pageable)
        // 여기서는 페이지 번호가 zero-based. 1페이지 = 0(month와 동일 취급)
        Pageable pageInfo = PageRequest.of(1, 5);

        //when
        Page<Student> students = repository.findAll(pageInfo);
        //실질적인 데이터 꺼내기
        List<Student> studentList = students.getContent();
        //총 페이지 수
       int totalPages = students.getTotalPages();
        // 총 학생 수
        long count = students.getTotalElements();

        //then
        System.out.println("\n\n\n");
        System.out.println("totalPages = " + totalPages);
        System.out.println("count = " + count);
        System.out.println();
        studentList.forEach(System.out::println);
        System.out.println("\n\n\n");
    }

    @Test
    @DisplayName("페이징 + 정렬")
    void pageAndSortTest() {
        //given
        PageRequest pageInfo = PageRequest.of(
          0,
          10,
                //매개값으로는 엔터티 필드명으로 작성 !!!!!!!!!!!!!!!!!!!!!!
//                Sort.by("name").descending() //내림차

                //여러 조건으로 정렬
                Sort.by(
                        Sort.Order.desc("name"),
                        Sort.Order.asc("city")

                )
        );

        //when
        Page<Student> studentPage = repository.findAll(pageInfo);

        //then
        studentPage.getContent().forEach(System.out::println);
    }


}