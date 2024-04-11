package efub.assignment.community.board.dto;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardRequestDto {
    @NotBlank(message = "멤버 id는 필수입니다.")
    private String memberId;

    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String boardName;

    @NotBlank(message = "게시판 주인 닉네임은 필수입니다.")
    private String ownerNickname;

    private String description;
    private String notice;


    public Board toEntity(Member member) {
        return Board.builder()
                .member(member)
                .boardName(boardName)
                .description(description)
                .notice(notice)
                .ownerNickname(ownerNickname)
                .build();
    }
}
