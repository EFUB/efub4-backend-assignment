package efub.assignment.community.post;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import efub.assignment.community.account.AccountRepository;
import efub.assignment.community.account.domain.Account;
import efub.assignment.community.board.domain.Board;
import efub.assignment.community.post.domain.Post;
import java.util.List;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostJpaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PostRepository postRepository;

    static Account account;
    static Board board;
    static String title;
    static String content;
    static String writerOpen;

    @BeforeAll
    static void beforeAll() {
        account = Account.builder()
                .email("abcd@gmail.com")
                .password("123akd!")
                .nickname("닉네임")
                .university("이화여대")
                .studentId("123456")
                .build();

        board = Board.builder()
                .account(account)
                .boardName("boardName")
                .boardDescription("des")
                .boardNotice("notice")
                .build();

        title = "title";
        content = "content";
        writerOpen = "true";
    }

    // 성공
    @Test
    public void 게시글_삭제_테스트() {

        Post post = Post.builder()
                .account(account)
                .board(board)
                .title(title)
                .content(content)
                .writerOpen(writerOpen)
                .build();

        testEntityManager.persist(post);

        postRepository.delete(post);

        assertThat(postRepository.findAll(), IsEmptyCollection.empty());
    }

    // 실패
    @Test
    public void 존재하지않는_ID_게시글조회_테스트() {

        Post post1 = Post.builder()
                .account(account)
                .board(board)
                .title(title)
                .content(content)
                .writerOpen(writerOpen)
                .build();

        testEntityManager.persist(post1);

        assertThat(postRepository.findById(10l).get(), is(post1));
    }
}