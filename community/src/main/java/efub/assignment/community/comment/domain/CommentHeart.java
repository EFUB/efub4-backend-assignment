package efub.assignment.community.comment.domain;

import efub.assignment.community.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Long id;

    /* Comment-Heart
     * 외래키의 주인: Comment */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "댓글은 필수로 입력되어야 합니다.")
    @JoinColumn(name = "comment_id", updatable = false)
    private Comment comment;

    /* Member-Heart
     * 외래키의 주인: Member */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "작성자는 필수로 입력되어야 합니다.")
    @JoinColumn(name = "member_id", updatable = false)
    private Member writer;

    @Builder
    public CommentHeart(Comment comment, Member writer) {
        this.comment = comment;
        this.writer = writer;
    }
}
