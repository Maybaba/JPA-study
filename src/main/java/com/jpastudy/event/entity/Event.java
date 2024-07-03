package com.jpastudy.event.entity;

import com.fasterxml.classmate.AnnotationOverrides;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //정수 타입이면 링크 투에서 ㅣㄹㅇ크가 안열려서 링크 연겷 문자열로 바꺼ㅜ야 하낟.

    @Column(name = "ev_title", nullable = false, length = 50)
    private String title; // 이벤트 제목

    @Column(name = "ev_desc")
    private String description; // 이벤트 설명

    @Column(name = "ev_image_path")
    private String image; // 이벤트 메인 이미지 경로

    @Column(name = "ev_start_date")
    private LocalDate date; // 이벤트 행사 시작 날짜

    @CreationTimestamp
    private LocalDateTime createdAt; // 이벤트 등록 날짜

}
