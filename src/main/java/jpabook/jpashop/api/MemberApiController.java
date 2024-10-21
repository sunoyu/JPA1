package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService service;

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest req) {
        Member member = new Member();
        member.setName(req.getName());

        Long id = service.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {
        service.update(id, request.getName());
        Member findMember = service.findOne(id);   // 위 업데이트에서 member로 반환하지 않고 그냥 새로 쿼리를 날려서 찾음.  커맨드와 쿼리를 분리하는 스타일. 유지보수 증대
        return new UpdateMemberResponse(id, findMember.getName());
    }



    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {this.id = id;}
    }


    //dto
    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }
}
