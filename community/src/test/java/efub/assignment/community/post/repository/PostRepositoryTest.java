package efub.assignment.community.post.repository;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.post.domain.Post;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PostRepository postRepository;

    private Account testAccount;
    private Board testBoard;
    private Post testPost;


    @BeforeEach
    void post_persist_setUp() {
        // given
        // 테스트를 위한 인스턴스 생성
        testAccount = Account.builder()
                .email("email@domain.com")
                .password("password")
                .nickname("사용자명")
                .university("Test Univ.")
                .studentId("1234")
                .build();
        testEntityManager.persist(testAccount);

        testBoard = Board.builder()
                .account(testAccount)
                .boardName("보드이름")
                .boardDescription("보드설명")
                .boardNotice("보드공지")
                .build();
        testEntityManager.persist(testBoard);

        String title = "제목";
        String content = "내용";
        String writerOpen = "PUBLIC";

        // when
        testPost = Post.builder()
                .account(testAccount)
                .board(testBoard)
                .title(title)
                .content(content)
                .writerOpen(writerOpen)
                .build();
        testEntityManager.persist(testPost);
    }

    @Test
    void save_test() {
        // when
        Post foundPost = testEntityManager.find(Post.class, testPost.getPostId());
        // then
        assertEquals(testPost, foundPost);
    }

    @Test
    void findById_test() {
        // when
        Optional<Post> postOptional = postRepository.findById(testPost.getPostId());
        Post foundPost = postOptional.get();
        // then
        assertNotNull(postOptional);
        assertEquals(testPost, foundPost);
    }
}
