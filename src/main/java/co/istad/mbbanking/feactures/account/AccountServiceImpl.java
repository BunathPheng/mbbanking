package co.istad.mbbanking.feactures.account;

import co.istad.mbbanking.domain.Account;
import co.istad.mbbanking.domain.AccountType;
import co.istad.mbbanking.domain.User;
import co.istad.mbbanking.feactures.account.dto.*;
import co.istad.mbbanking.feactures.accounttype.AccountTypeRepository;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeUpdateRequest;
import co.istad.mbbanking.feactures.user.UserRepository;
import co.istad.mbbanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    @Override
    public AccountResponeDto createNewAccount(AccountCreateRequestDto accountCreateRequestDto) {
        //validate  account has  in  database
        if (accountRepository.existsByAccountNo(accountCreateRequestDto.accountNo()))
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , "Account already exists in system");
        }
        // validate   accountType  fromm  data base
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequestDto.accountTypeAlias()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account type not found in database")
        );
        //validate  uuid  for  user  in   system
        User user = userRepository.findByUuid(accountCreateRequestDto.uuidUser()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "User not found in database")
        );

        //validate  balance  > 100
        if(accountCreateRequestDto.balance().compareTo(BigDecimal.valueOf(100)) < 0)
        {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST , "Insufficient balance");
        }
        Account account = accountMapper.fromAccountCreateRequestDto(accountCreateRequestDto);
                 account.setAccountType(accountType);
                 account.setAccountType(accountType);
                 account.setUser(user);
                 account.setAccountName(user.getName());
                 account.setIsHidden(false);
                 account.setIsDeleted(false);
                 account.setTransferLimit(BigDecimal.valueOf(1000));
        account = accountRepository.save(account);
        return accountMapper.toAccountResponeDto(account);
    }

    @Override
    public Page<AccountResponeDto> getAllAccount(Integer pageNumber , Integer pageSize) {
        Sort  sortById =  Sort.by(Sort.Direction.DESC , "acId");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize , sortById);
        Page<Account> accounts = accountRepository.findAll(pageRequest);
        return  accounts.map(accountMapper::toAccountResponeDto);
    }

    @Override
    public AccountResponeDto getAccountByAccountNo(String accountNo) {
            Account account = accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")
            );
        return accountMapper.toAccountResponeDto(account);
    }

    @Override
    public AccountResponeDto renameAccount(String accountNo, AccountRenameRequest accountRenameRequestDto) {
        //Validate  the  account
        Account account =  accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")

        );
        account.setAlias(accountRenameRequestDto.alias());
        accountRepository.save(account);
        return accountMapper.toAccountResponeDto(account);
    }

    @Override
    public void AccountHide(String accountNo) {
        //Validate the account
        Account account = accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")
        );
        account.setIsHidden(true);
        accountRepository.save(account);
    }

    @Override
    public void updateTransferLimit(String accountNo, AccountTransferLimitRequest accountTransferLimitRequest) {
        //validate  to  account transfer limit
        Account account = accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")
        );
        account.setTransferLimit(accountTransferLimitRequest.amount());
        accountRepository.save(account);
    }

    @Override
    public AccountResponeDto updateAccountByAccountNo(String accountNo, AccountUpdateRequest accountUpdateRequest) {
        //validate  the  account
        Account  account = accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")
        );
        account.setBalance(accountUpdateRequest.balance());
        account.setAccountName(accountUpdateRequest.accountName());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponeDto(account);
    }

    @Override
    public void deleteAccountByAccountNo(String accountNo) {
        //deleteAccount by Hard deletion
        //valid   account
        Account account = accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")
        );
         accountRepository.delete(account);
    }

    @Override
    public void deleteAccountByAccountNo(String accountNo, AccountDeleteRequest accountDeleteRequest) {
         //validate  account
        Account account = accountRepository.findAllByAccountNo(accountNo).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Account not found in system")
        );
        account.setIsDeleted(true);
        accountRepository.save(account);
    }
}
