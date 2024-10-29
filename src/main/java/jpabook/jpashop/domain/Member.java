package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue  // 시퀀스 값을 씀
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    List<Order> orders = new ArrayList<>();

}
