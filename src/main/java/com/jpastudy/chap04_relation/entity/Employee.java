package com.jpastudy.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter
@ToString(exclude = "department") //연관관계 필드 제외 -> 자동 조인 결과 제외
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id; //사원번호
    @Column(name = "emp_name", nullable = false)
    private String name;

    //단방향 매핑 - 데이터베이스처럼 한쪽에 상대방의 PK를 Fk로 갖는 형태
    //EAGER loading : 연관된 데이터를 항상 join 을 통해 같이 가져옴
    //LAZY loading: 해당 엔터티 데이터만 가져오고 필요한 경우 연관엔터티를 가져옴
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dept_id")
    private Department department;

    //단방향 매핑 - 데이터베이스처럼 한쪽에 상대방의 PK를 Fk로 갖는 형태
//    @ManyToOne
//    @JoinColumn(name="send_dept_id")
//    private Department department2;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", department=" + department +
                '}';
    }

    //이 메서드로 양방향 관계에서 한 쪽 수정시 자동으로 반대쪽도 수정되게끔 유틸을 만들어서
    //다른 쪽에서도 수정이 자동으로 일어나게 하도록 한다.
    public void changeDepartment(Department department) {
        this.department = department;
        department.getEmployees().add(this);
    }
}
