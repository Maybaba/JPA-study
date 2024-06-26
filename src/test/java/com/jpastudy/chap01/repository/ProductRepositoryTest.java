package com.jpastudy.chap01.repository;

import com.jpastudy.chap01.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.jpastudy.chap01.entity.Product.Category.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
//@Rollback
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach //테스트 이전 실행할 코드
    void insertBeforeTest() {
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(2000000)
                .build();
        Product p2 = Product.builder()
                .name("탕수육")
                .category(FOOD)
                .price(20000)
                .build();
        Product p3 = Product.builder()
                .name("구두")
                .category(FASHION)
                .price(300000)
                .build();
        Product p4 = Product.builder()
                .name("주먹밥")
                .category(FOOD)
                .price(1500)
                .build();

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);

    }

    @Test
    @DisplayName("상품을 데이터베이스에 저장한다. ")
    void saveTest() {
        //given
        Product product = Product.builder()
                .name("마라탕")
//                .price(120000)
//                .category(Product.Category.FASHION)
                .build();

        //when
        Product saved = productRepository.save(product);
        //then
        assertNotNull(saved);
    }

    @Test
    @DisplayName("1번 상품을 사겢한다. ")
    void deleteTest() {
        //given
        Long id = 1L;
        //when
        productRepository.deleteById(id);
        //then
        Product foundProd = productRepository.findById(id)
                .orElse(null);
        //null check
        assertNull(foundProd);
    }


    @Test
    @DisplayName("3번 상품을 단일조회하면 그 상품명이 구두이다.")
    void fineOneTest() {
        //given
        Long id = 3L;
        //when
        Product foundProd = productRepository.findById(id).orElse(null);
        //then
        assertEquals("구두", foundProd.getName());
        System.out.println("\n\n\nfoundProduct = " + foundProd + "\n\n\n\n");
    }

    @Test
    @DisplayName("상품을 전체조회하면 상품의 ㅈ총 개수사 4개이다. ")
    void findAllTest() {
        //given
        //when
        List<Product> all = productRepository.findAll();
        //then
        System.out.println("\n\n\n");
        all.forEach(System.out::println);
        System.out.println("\n\n\n");
        assertEquals(4, all.size());
    }

    @Test
    @DisplayName("2번 상품의 이름과 카테고리를 수정하여 업데이트 한다. ")
    void modifyTest() {
        //given
        Long id = 2L;
        String newName = "청소기";
        Product.Category newCategory = ELECTRONIC;
        //when
        /*
        JPA 에서는 수정메서드를 따로 제공하지 않는다.
        단일조회를 수행한 후 setter를 통해 값을 변경하고
        다시 save를 하면 INSERT 대신 UPDATE 문이 나간다.
         */
        Product product = productRepository.findById(id).orElse(null);

        //then
        product.setName(newName);
        product.setCategory(newCategory);

        Product saved = productRepository.save(product);
        assertEquals(newName, saved.getName());

    }



}