package efub.assignment.community.account.controller;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.account.dto.AccountResponseDto;
import efub.assignment.community.account.dto.AccountUpdateRequestDto;
import efub.assignment.community.account.dto.SignUpRequestDto;
import efub.assignment.community.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Account account = accountService.signUp(requestDto);
        return AccountResponseDto.from(account);
    }

    @GetMapping("/{account_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto getAccount(@PathVariable Long account_id){
        Account account = accountService.findAccountById(account_id);
        return AccountResponseDto.from(account);
    }

    @PatchMapping("/profile/{account_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountResponseDto update(@PathVariable final Long account_id, @RequestBody @Valid final AccountUpdateRequestDto requestDto){
        Account account = accountService.updateAccount(account_id,requestDto);
        return AccountResponseDto.from(account);
    }

    @PatchMapping("/{account_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String withdraw(@PathVariable Long account_id){
        accountService.withdraw(account_id);
        return "계쩡 비활성화가 완료되었습니다.";
    }
}
