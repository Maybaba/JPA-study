package com.jpastudy.chap05.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = {"user", "goods"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_mtm_purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perchase_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn
    private User user;// 하나의 구매가 한명의 유저에게 할당된다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

}
