package efub.assignment.community.member.domain;

import efub.assignment.community.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static efub.assignment.community.member.domain.MemberStatus.REGISTERED;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false, updatable = false, length = 16)
    private String nickname;

    @Column(nullable = false, length = 60)
    private String university;

    @Column(nullable = false, length = 10)
    private String studentId;

    @Enumerated(EnumType.STRING) // enum 타입
    private MemberStatus status;

    @Builder // 객체 생성
    public Member(String email, String password, String nickname, String university, String studentId) {
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.university = university;
        this.studentId = studentId;
        this.status = REGISTERED;
    }

    //닉네임 수정하기
    public void updateMember(String nickname) {
        this.nickname = nickname;
    }

    //회원 탈퇴
    public void withdrawMember() {
        this.status = MemberStatus.UNREGISTERED;
    }

}