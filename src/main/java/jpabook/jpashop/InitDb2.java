//package jpabook.jpashop;
//
//import jpabook.jpashop.domain.Address;
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.domain.item.Book;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb2 {
//
//
//    @PostConstruct
//    public void init() {}
//
//
//    @Component
//    @RequiredArgsConstructor
//    @Transactional
//    static class InitService {
//        private final EntityManager em;
//
//        public void dbInit1() {
//            Member member = new Member();
//            member.setName("userA");
//            member.setAddress(new Address("서울", "1", "1111"));
//            em.persist(member);
//
//            Book book1 = new Book();
//            book1.setName("JPA1 Book");
//            book1.setPrice(10000);
//            book1.setStockQuantity(100);
//
//
//            Book book2 = new Book();
//            book2.setName("JPA2 Book");
//            book2.setPrice(20000);
//            book2.setStockQuantity(100);
//
//
//        }
//    }
//
//}
