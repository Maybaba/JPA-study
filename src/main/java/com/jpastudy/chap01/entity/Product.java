package com.jpastudy.chap01.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString(exclude = "nickName") //중괄호 안에 여러 개 쓸 수 있다.
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id", "name"} )//아이디, PK 만 보고 같은 객체 인지 판단 (전체 칼럼 대조 하여 판단 하지 아니함)

@Entity
@Table(name = "tbl_product")
public class Product {

    //엔터티를 설계하면 알아서 테이블을 짜준다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql(IDENTITY) 인지 oracle(SEQUENCE)인지에 따라 타입을 바꾼다.
    @Column(name="prod_id")
    private Long id;

    @Setter
    @Column(name="prod_nm", length = 30, nullable = false)
    private String name;

    @Column(name="price")
    private int price;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @CreationTimestamp //insert 시 자동으로 서버시간 저장
    @Column(updatable = false) //수정 불가
    private LocalDateTime createAt; //상품 등록시간

    @UpdateTimestamp //update문 실행 시 자동으로 시간이 저장
    private LocalDateTime updateAt; //상품 수정시간

    @Transient // 데이터 베이스에는 저장안하고 클래스 내부에서만 사용할 필드
    private String nickName;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }

    @PrePersist //컬럼 기본값 설정
    public void prePersist() {
        if(this.price == 0) {
            this.price = 10000;
        }
        if(this.category == null) {
            this.category = Category.FOOD;
        }
    }
}
