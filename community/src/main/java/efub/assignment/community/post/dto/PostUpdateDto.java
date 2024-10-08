package efub.assignment.community.post.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateDto {

    @NotBlank(message = "글의 제목은 필수입니다.")
    private String title;

    @NotBlank(message = "글의 내용은 필수입니다.")
    private String content;

    @NotBlank(message = "글의 공개 상태는 필수입니다.")
    private String writerOpen;

    @Builder
    public PostUpdateDto(String title, String content, String writerOpen){
        this.title = title;
        this.content = content;
        this.writerOpen = writerOpen;
    }
}
