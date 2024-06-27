package com.jpastudy.chap06_querydsl.repository;

import com.jpastudy.chap06_querydsl.entity.Group;
import com.jpastudy.chap06_querydsl.entity.Idol;
import com.jpastudy.chap06_querydsl.entity.QIdol;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.jpastudy.chap06_querydsl.entity.QIdol.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Rollback(false)
@Transactional
class QueryDslBasicTest {

    @Autowired
    IdolRepository idolRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    EntityManager em;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void setUp() {

        //given
        Group leSserafim = new Group("르세라핌");
        Group ive = new Group("아이브");

        groupRepository.save(leSserafim);
        groupRepository.save(ive);

        Idol idol1 = new Idol("김채원", 24, leSserafim);
        Idol idol2 = new Idol("사쿠라", 26, leSserafim);
        Idol idol3 = new Idol("가을", 22, ive);
        Idol idol4 = new Idol("리즈", 20, ive);

        idolRepository.save(idol1);
        idolRepository.save(idol2);
        idolRepository.save(idol3);
        idolRepository.save(idol4);

    }

    @Test
    @DisplayName("JPQL 특정 아이돌 조회하기")
    void jpqltest() {
        //given
        String jpqlQuery = "SELECT i FROM Idol i WHERE i.idolName = ?1";
        //when
        Idol foundIdol = em.createQuery(jpqlQuery, Idol.class)
                .setParameter(1, "가을")
                .getSingleResult();

        //then
        assertEquals("아이브", foundIdol.getGroup().getGroupName());

        System.out.println("\n\n\n\n");
        System.out.println("foundIdol = " + foundIdol);
        System.out.println("foundIdol.getGroup() = " + foundIdol.getGroup());
        System.out.println("\n\n\n\n");
    }

    @Test
    @DisplayName("QueryDsl로 특정 이름의 아이돌 조회하기")
    void queryDslTest() {
        //given
        // QueryDsl로 JPQL을 만드는 빌더
//        JPAQueryFactory factory = new JPAQueryFactory(em);
        //when
        Idol foundIdol = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.idolName.eq("가을"))
                .fetchOne();

        //then
        assertEquals("아이브", foundIdol.getGroup().getGroupName());

        System.out.println("\n\n\n\n");
        System.out.println("foundIdol = " + foundIdol);
        System.out.println("foundIdol.getGroup() = " + foundIdol.getGroup());
        System.out.println("\n\n\n\n");
    }

    @Test
    @DisplayName("이름과 나이로 아이돌 조회하기")
    void searchTest() {
        //given
        String name = "리즈";
        int age = 20;
        //when
        Idol foundIdol = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(
                        idol.idolName.eq(name)
                                .and(idol.age.eq(age))
                )
                .fetchOne();

        //then
        assertNotNull(foundIdol);
        assertEquals("아이브", foundIdol.getGroup().getGroupName());

        System.out.println("\n\n\n\n");
        System.out.println("foundIdol = " + foundIdol);
        System.out.println("foundIdol.getGroup() = " + foundIdol.getGroup());
        System.out.println("\n\n\n\n");

        //        idol.idolName.eq("리즈") // idolName = '리즈'
//        idol.idolName.ne("리즈") // username != '리즈'
//        idol.idolName.eq("리즈").not() // username != '리즈'
//        idol.idolName.isNotNull() //이름이 is not null
//        idol.age.in(10, 20) // age in (10,20)
//        idol.age.notIn(10, 20) // age not in (10, 20)
//        idol.age.between(10,30) //between 10, 30
//        idol.age.goe(30) // age >= 30
//        idol.age.gt(30) // age > 30
//        idol.age.loe(30) // age <= 30
//        idol.age.lt(30) // age < 30
//        idol.idolName.like("_김%")  // like _김%
//        idol.idolName.contains("김") // like %김%
//        idol.idolName.startsWith("김") // like 김%
//        idol.idolName.endsWith("김") // like %김

    }

    @Test
    @DisplayName("조회 결과 반환하기 ")
    void fetchTest() {
        //given
        //리스트 조회  (fetch)
        List<Idol> idolList = jpaQueryFactory
                .select(idol)
                .from(idol)
                .fetch();

        System.out.println("\n\n==========fetch================\n");
        idolList.forEach(System.out::println);

        //단일행 조회(fetchOne)
        Idol idol1 = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.age.lt(21))
                .fetchOne();


        System.out.println("\n\n==========fetch ONE================\n");
        System.out.println("idol1 = " + idol1);



        //단일행 조회시, null safety를 위한 optional로 받고 싶을 때
        Optional<Idol> foundIdolOptional = Optional.ofNullable(jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.age.lt(21))
                .fetchOne());


        System.out.println("\n\n========== OPTIONAL fetch ONE null safety================\n");
        System.out.println("foundIdolOptional = " + foundIdolOptional);
        //나이가 24세 이상인 아이돌
        //단일행 조회(fetchOne)
        List<Idol> idolList4 = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.age.goe(24))
                .fetch();

        System.out.println("idolList4 = " + idolList4);

        //이름에 김이라는 ㅁ누자열 포함 : idol_name like '%김%'
        List<Idol> idolList1 = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.idolName.contains("김"))
                .fetch();
        System.out.println("idolList1 = " + idolList1);
        //20~25세 사이
        List<Idol> idolList2 = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.age.between(20, 25))
                .fetch();

        System.out.println("idolList2 = " + idolList2);
        //그룹이름이 르세라핌인 아이돌 조회
        List<Idol> idolList3 = jpaQueryFactory
                .select(idol)
                .from(idol)
                .where(idol.group.groupName.eq("르세라핌"))
                .fetch();

        System.out.println("idolList3 = " + idolList3);
        //then
    }



}