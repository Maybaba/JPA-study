package com.jpastudy.event.repository;

import com.jpastudy.event.entity.Event;

import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findEvents(String sort);

    // 동적 쿼리 작서앟기
}
