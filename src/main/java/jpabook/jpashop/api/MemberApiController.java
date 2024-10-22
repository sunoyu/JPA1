package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService service;

    @GetMapping("/api/v2/members")
    public Result memberV2() {  // 추가적으로 요구사항이 들어온다면? 어레이만 반환하면 추가 요구사항을 반영하기 어려움.
        List<Member> findMembers = service.findMembers();
        List<MemverDto> collect = findMembers.stream().
                map(m -> new MemverDto(m.getName())).collect(Collectors.toList());

        return new Result(collect.size(), collect);

    }


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

    @Data
    @AllArgsConstructor
    private class Result<T> {
        private int count; // 이런식으로 추가로 넣어줄 수 있음.
        private T data;
    }

    @Data
    @AllArgsConstructor
    private class MemverDto {
        private String name;
    }
}
