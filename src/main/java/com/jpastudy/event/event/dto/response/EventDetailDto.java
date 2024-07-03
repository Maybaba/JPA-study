package com.jpastudy.event.event.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpastudy.event.entity.Event;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDetailDto {

    private String id;
    private String title;
    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate startDate;

    @JsonProperty(value = "img-url")
    private String imgUrl;

    public EventDetailDto(Event event) {
        this.id = event.getId().toString();
        this.title = event.getTitle();
        this.startDate = event.getDate();
        this.imgUrl = event.getImage();
    }
}

