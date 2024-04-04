package efub.assignment.community.member.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberResponseDto;
import efub.assignment.community.member.dto.MemberRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto signUp(MemberRequestDto requestDto){

        if(memberRepository.existsByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = memberRepository.save(requestDto.toEntity());
        MemberResponseDto responseDto = MemberResponseDto.toDto(member);
        return responseDto;
    }

    public MemberResponseDto updateMember(Long memberId, MemberRequestDto requestDto){

        Member member = memberRepository.findById(memberId).orElseThrow(()->{
            throw new IllegalArgumentException("존재하지 않는 memberId입니다.");
        });
        member.changeNickname(requestDto.getNickname());
        MemberResponseDto responseDto = MemberResponseDto.toDto(memberRepository.save(member));
        return responseDto;
    }

}
