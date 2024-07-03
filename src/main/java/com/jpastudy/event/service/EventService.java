package com.jpastudy.event.service;

import com.jpastudy.event.entity.Event;
import com.jpastudy.event.event.dto.request.EventSaveDto;
import com.jpastudy.event.event.dto.response.EventDetailDto;
import com.jpastudy.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional //서비스에 붙이는 트랜젝셔녈 중요!!!!!!!!!!!!🪬 커밋, 롤백 자동으로 해줌
public class EventService {
    private final EventRepository eventRepository;

    //전체 조회
    public List<EventDetailDto> getEvents(String sort) {
        return eventRepository.findEvents(sort)
                .stream()
                .map(EventDetailDto::new)
                .collect(Collectors.toList())                ;
    }

    //이벤트 등록
    public List<EventDetailDto> saveEvent(EventSaveDto dto) {
        Event savedEvent = eventRepository.save(dto.toEntity());
        log.info("saved event : {}", savedEvent);
        return getEvents("date"); // 날짜로 정렬하기
    }

}
