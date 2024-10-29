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

    @OneToMany(mappedBy = "member") // order 테이블에 있는 member 필드에 의해서 매핑이 된거다 라는 뜻. 거울일뿐이야~
    List<Order> orders = new ArrayList<>();

}
