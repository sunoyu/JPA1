package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  // 반드시 스트링으로!!!
    private DeliveryStatus status;


}
