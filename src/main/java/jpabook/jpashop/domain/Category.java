package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",   // 중간테이블 매핑을 해줘야함. 관계형 디비는 일대다 다대일로 풀어주는 ? , 실전에선 쓰지 않는게...
            joinColumns = @JoinColumn(name = "category_id"),  // 중간테이블에 있는 카테고리
            inverseJoinColumns = @JoinColumn(name = "item_id"))  // 아이템쪽으로 들어가는
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
