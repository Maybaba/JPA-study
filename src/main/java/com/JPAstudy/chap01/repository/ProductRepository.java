package com.JPAstudy.chap01.repository;

import com.JPAstudy.chap01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//my-batis 와 같은 기능이다
//JPA 르포 첫번재 제너릭 T : 엔터티클래스타입
// 두번째 제너릭 K : PK 타입
public interface ProductRepository extends JpaRepository<Product, Long> {


}
