package efub.assignment.community.member.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberResponseDto;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /* 회원가입 기능 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id = memberService.signUp(requestDto);
        Member findMember = memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }
    /* 멤버(1명) 조회 기능 */
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId){
        Member findMember = memberService.findMemberById(memberId);
        return MemberResponseDto.from(findMember);
    }
    /* 회원정보 수정 */
    @PatchMapping("/profile/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto update(@PathVariable final Long memberId, @RequestBody @Valid final MemberUpdateRequestDto requestDto){
        Long id = memberService.update(memberId, requestDto);
        Member findMember = memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    /* 회원 탈퇴 기능 */
    @PatchMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable Long memberId){
        memberService.withdraw(memberId);
        return "삭제가 완료되었습니다.";
    }


}
