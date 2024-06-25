package com.JPAstudy.chap01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product {
    //엔터티를 설계하면 알아서 테이블을 짜준다.

    @Id
    @Column(name="prod_id")
    private Long id;
    @Column(name="prod_nm", length = 30, nullable = false)
    private String name;
    @Column(name="price")
    private int price;

}
