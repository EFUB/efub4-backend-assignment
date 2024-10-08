package efub.assignment.community.account.repository;

import efub.assignment.community.account.domain.Account;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE, connection = EmbeddedDatabaseConnection.H2)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    private Account testAccount;

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
        testEntityManager.persist(testAccount);
    }

    @Test
    void save_test() {
        // when
        Account foundAccount = testEntityManager.find(Account.class, testAccount.getAccountId());
        // then
        assertEquals(testAccount, foundAccount);
    }

    @Test
    void existsByEmail_test() {
        // when
        Boolean isExisting = accountRepository.existsByEmail("email@domain.com");
        // then
        assertTrue(isExisting);
    }

    @Test
    void existsByNickname_test() {
        // when
        Boolean isExisting = accountRepository.existsByNickname("사용자명");
        // then
        assertTrue(isExisting);
    }

    @Test
    void findById_test() {
        // when
        Optional<Account> accountOptional = accountRepository.findById(testAccount.getAccountId());
        Account foundAccount = accountOptional.get();
        // then
        assertNotNull(accountOptional);
        assertEquals(testAccount, foundAccount);
    }

    @Test
    void findByEmail_test() {
        // when
        Optional<Account> accountOptional = accountRepository.findByEmail(testAccount.getEmail());
        Account foundAccount = accountOptional.get();
        // then
        assertNotNull(accountOptional);
        assertThat(testAccount).isEqualTo(foundAccount); // assertJ의 assertion을 사용
    }

    @Test
    void findByNickname_test() {
        // when
        Optional<Account> accountOptional = accountRepository.findByNickname("사용자명");
        Account foundAccount = accountOptional.get();
        // then
        assertNotNull(accountOptional);
        assertThat(testAccount).isEqualTo(foundAccount);
    }

}