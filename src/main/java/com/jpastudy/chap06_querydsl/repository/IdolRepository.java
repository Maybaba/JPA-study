package com.jpastudy.chap06_querydsl.repository;


import com.jpastudy.chap06_querydsl.entity.Idol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdolRepository extends JpaRepository<Idol, String> {
}
