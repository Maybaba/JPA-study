package com.JPAstudy.chap01.repository;

import com.JPAstudy.chap01.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품을 데이터베이스에 저장한다. ")
    void saveTest() {
        //given
        Product product = Product.builder()
                .name("rain boots")
                .price(120000)
                .category(Product.Category.FASHION)
                .build();

        //when
        Product saved = productRepository.save(product);
        //then
        assertNotNull(saved);
    }


}