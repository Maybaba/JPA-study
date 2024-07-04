package com.jpastudy.event.event.controller;

import com.jpastudy.event.event.dto.request.EventSaveDto;
import com.jpastudy.event.event.dto.response.EventDetailDto;
import com.jpastudy.event.event.dto.response.EventOneDto;
import com.jpastudy.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // jsp 말고 리액트 연동
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class EventController {

    private final EventService eventService;

    // 전체 조회 요청 : 예외 처리
    @GetMapping
    public ResponseEntity<?> getList(
            @RequestParam(required = false)
            String sort) {

        if(sort == null) {
            return ResponseEntity.badRequest().body("sort param이 없습니둥... ");
        }

        List<EventDetailDto> events = eventService.getEvents(sort);
        return ResponseEntity.ok().body(events);
    }

    // 등록 요청
    @PostMapping
    public ResponseEntity<?> register(@RequestBody EventSaveDto dto) {
        List<EventDetailDto> events = eventService.saveEvent(dto);
        return ResponseEntity.ok().body(events);
    }

    //단일 조회 요청
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEvent(
            @PathVariable Long eventId
    ) {
        if(eventId == null || eventId < 1) {
            String errorMessage = "eventID가 정확하지 않습니다. ";
            log.warn(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        EventOneDto eventDetail = eventService.getEventDetail(eventId);
        return ResponseEntity.ok().body(eventDetail);
    }





}
