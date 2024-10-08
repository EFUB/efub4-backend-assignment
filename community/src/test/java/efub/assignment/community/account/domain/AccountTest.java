package efub.assignment.community.account.domain;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTest {

    @Order(1)
    @Test
    void account_build_test() {
        // given
        String email = "email@domain.com";
        String password = "password";
        String nickname = "사용자명";
        String university = "Test Univ.";
        String studentId = "1234";

        // then
        Account account = Account.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();

        // then
        assertEquals(email, account.getEmail());
        assertEquals(password, account.getPassword());
        assertEquals(nickname, account.getNickname());
        assertEquals(university, account.getUniversity());
        assertEquals(studentId, account.getStudentId());
    }

    @Order(2)
    @Test
    void updateAccount_test() {
        // given
        String email = "email@domain.com";
        String password = "password";
        String nickname = "사용자명";
        String university = "Test Univ.";
        String studentId = "1234";

        Account account = Account.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();

        // when
        String newEmail = "newemail@domain.com";
        String newNickname = "새 사용자명";
        String newPassword = "newpassword";

        account.updateAccount(newEmail, newNickname, newPassword);

        // then
        assertEquals(newEmail, account.getEmail());
        assertEquals(newNickname, account.getNickname());
        assertEquals(newPassword, account.getPassword());
    }

    @Test
    void withdrawAccount_test() {
        // given
        String email = "email@domain.com";
        String password = "password";
        String nickname = "사용자명";
        String university = "Test Univ.";
        String studentId = "1234";

        Account account = Account.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();

        // when
        account.withdrawAccount();

        // then
        assertEquals(account.getStatus(), AccountStatus.UNREGISTERED);
    }
}
