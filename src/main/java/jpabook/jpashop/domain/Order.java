package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 연관관계 주인
@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @ManyToOne  // order , member의 양방향 연관관계
    @JoinColumn(name = "member_id")  // foreign key가 Member Id가 된다.
    private Member member;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne   // 1대1, 보통 주문에서 배송을 조회. FK를 딜리버리로 설정, FK가 가까운 Order가 연관관계 주인
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;


    private LocalDateTime localDateTime;  // java8부터 로컬데이트타임을 쓰면 하이버네이트가 자동 지원
    private OrderStatus status;



}
