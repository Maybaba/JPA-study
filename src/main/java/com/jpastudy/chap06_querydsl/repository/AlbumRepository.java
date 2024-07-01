package com.jpastudy.chap06_querydsl.repository;

import com.jpastudy.chap06_querydsl.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
