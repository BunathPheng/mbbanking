package co.istad.mbbanking.feactures.account;
import co.istad.mbbanking.feactures.account.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    Page<AccountResponeDto> getAccountAll(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        return accountService.getAllAccount(pageNumber, pageSize);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponeDto createAccount(@Valid @RequestBody AccountCreateRequestDto accountCreateRequestDto) {
        return accountService.createNewAccount(accountCreateRequestDto);
    }

    @GetMapping("/{accountNo}")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountResponeDto getAllAccountByAccountNo(@PathVariable String accountNo) {
        return accountService.getAccountByAccountNo(accountNo);
    }

    @PutMapping("/{accountNo}/rename")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponeDto renameAccount(@PathVariable String accountNo, @Valid @RequestBody AccountRenameRequest accountRenameRequest) {
        return accountService.renameAccount(accountNo, accountRenameRequest);
    }

    @PutMapping("/{accountNo}/hide")
    @ResponseStatus(HttpStatus.OK)
    public void AccountHide(@PathVariable String accountNo) {
        accountService.AccountHide(accountNo);
    }

    @PutMapping("/{accountNo}/transfer-limits")
    @ResponseStatus(HttpStatus.OK)
    public void updateTransferLimit(@PathVariable String accountNo, @Valid @RequestBody
    AccountTransferLimitRequest accountTransferLimitRequest) {
        accountService.updateTransferLimit(accountNo, accountTransferLimitRequest);
    }

    //delete the account by hard  deleting
    @DeleteMapping("/{accountNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountHardAccount(@PathVariable String accountNo) {
        accountService.deleteAccountByAccountNo(accountNo);
    }

    //delete  the  account by soft account
    @PutMapping("/{accountNo}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccountSoftAccount(@PathVariable String accountNo,
                                         @Valid @RequestBody AccountDeleteRequest accountDeleteRequest) {
        accountService.deleteAccountByAccountNo(accountNo, accountDeleteRequest);

    }
    @PutMapping("/{accountNo}/updates")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponeDto updateAccount(@PathVariable String accountNo,
                                                          @Valid @RequestBody AccountUpdateRequest accountUpdateRequest) {
        return  accountService.updateAccountByAccountNo(accountNo , accountUpdateRequest);
    }
}
