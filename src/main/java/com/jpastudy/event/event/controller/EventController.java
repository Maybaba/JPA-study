package com.jpastudy.event.event.controller;

import com.jpastudy.event.entity.Event;
import com.jpastudy.event.event.dto.request.EventSaveDto;
import com.jpastudy.event.event.dto.response.EventDetailDto;
import com.jpastudy.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // jsp 말고 리액트 연동
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class EventController {

    private final EventService eventService;

    // 전체 조회 요청
    @GetMapping
    public ResponseEntity<?> getList(@RequestParam(required = false, defaultValue = "date")String sort) {
        List<EventDetailDto> events = eventService.getEvents(sort);
        return ResponseEntity.ok().body(events);
    }

    // 등록 요청
    @PostMapping
    public ResponseEntity<?> register(@RequestBody EventSaveDto dto) {
        List<EventDetailDto> events = eventService.saveEvent(dto);
        return ResponseEntity.ok().body(events);
    }


}
