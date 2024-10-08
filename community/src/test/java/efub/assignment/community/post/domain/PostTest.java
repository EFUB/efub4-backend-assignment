package efub.assignment.community.post.domain;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.board.domain.Board;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostTest {

    private Account testAccount;
    private Board testBoard;

    @BeforeEach
    void testAccount_persist_setUp() {
        // 테스트를 위한 Account 인스턴스 생성
        String email = "email@domain.com";
        String password = "password";
        String nickname = "사용자명";
        String university = "Test Univ.";
        String studentId = "1234";

        testAccount = Account.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();
    }

    @BeforeEach
    void testBoard_persist_setUp() {
        // 테스트를 위한 Board 인스턴스 생성
        String boardName = "보드이름";
        String boardDescription = "보드설명";
        String boardNotice = "보드공지";

        testBoard = Board.builder()
                .account(testAccount)
                .boardName(boardName)
                .boardDescription(boardDescription)
                .boardNotice(boardNotice)
                .build();
    }

    @Order(1)
    @Test
    void post_build_test() {
        // given
        String title = "제목";
        String content = "내용";
        String writerOpen = "PUBLIC";

        // when
        Post post = Post.builder()
                .account(testAccount)
                .board(testBoard)
                .title(title)
                .content(content)
                .writerOpen(writerOpen)
                .build();

        // then
        assertEquals(testAccount, post.getAccount());
        assertEquals(testBoard, post.getBoard());
        assertEquals(title, post.getTitle());
        assertEquals(content, post.getContent());
        assertEquals(writerOpen, post.getWriterOpen());
    }

    @Order(2)
    @Test
    void post_update_test() {
        // given
        String title = "제목";
        String content = "내용";
        String writerOpen = "PUBLIC";

        Post post = Post.builder()
                .account(testAccount)
                .board(testBoard)
                .title(title)
                .content(content)
                .writerOpen(writerOpen)
                .build();

        String newTitle = "새 제목";
        String newContent = "새 내용";
        String newWriterOpen = "PRIVATE";

        // when
        post.update(newTitle, newContent, newWriterOpen);

        // then
        assertEquals(newTitle, post.getTitle());
        assertEquals(newContent, post.getContent());
        assertEquals(newWriterOpen, post.getWriterOpen());
    }
}