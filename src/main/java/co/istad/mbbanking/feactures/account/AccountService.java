package co.istad.mbbanking.feactures.account;

import co.istad.mbbanking.feactures.account.dto.*;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeRespone;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;


public interface AccountService {
    AccountResponeDto createNewAccount(AccountCreateRequestDto accountCreateRequestDto);
    /**
     * find  all account by pagination
     * @param  pageNumber the page number
     * @param  pageSize the page size of the account
     * @return {@link Page<AccountResponeDto>}
     */
    Page<AccountResponeDto> getAllAccount(Integer pageNumber  , Integer pageSize);
    AccountResponeDto getAccountByAccountNo(String accountNo);
    AccountResponeDto renameAccount(String accountNo , AccountRenameRequest accountRenameRequestDto);
    void  AccountHide(String accountNo);
    void  updateTransferLimit(String  accountNo , AccountTransferLimitRequest accountTransferLimitRequest);
    AccountResponeDto  updateAccountByAccountNo(String accountNo ,  AccountUpdateRequest accountUpdateRequest);
    void deleteAccountByAccountNo(String accountNo);
    void deleteAccountByAccountNo(String accountNo, AccountDeleteRequest accountDeleteRequest);
}
