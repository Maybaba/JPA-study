package com.jpastudy.chap05.repository;

import com.jpastudy.chap05.entity.Goods;
import com.jpastudy.chap05.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}

