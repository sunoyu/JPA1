package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable  // 어딘가에 내장이 될 수 있다.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address(){}   // JPA구현 라이브러리가 객체를 생성 할 때 Reflection 과 Proxy같은 기술을 수용할 수 있도록 지원하기 위한 용도


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
