package com.jpastudy.event.event.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpastudy.event.entity.Event;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventOneDto {
    @JsonProperty("event-id")
    private String id;
    private String title;
    private String desc;
    @JsonProperty("img-url")
    private String image;
    @JsonProperty
    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate date;

    public EventOneDto(Event e) {
        this.id = e.getId().toString();
        this.title = e.getTitle();
        this.desc = e.getDescription();
        this.image = e.getImage();
        this.date = e.getDate();
    }

}
