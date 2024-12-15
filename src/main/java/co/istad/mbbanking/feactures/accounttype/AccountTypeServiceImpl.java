package co.istad.mbbanking.feactures.accounttype;

import co.istad.mbbanking.domain.AccountType;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeCreateRequest;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeRespone;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeUpdateRequest;
import co.istad.mbbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeRepository accountTypeRepository;
    @Override
    public List<AccountTypeRespone> getAccountTypeRespone() {
        if (accountTypeRepository.findAll().isEmpty())
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , "Account Type is  not have in database" );
        }
        Sort sortId  = Sort.by(Sort.Direction.DESC , "id");
        List<AccountType> accounts = accountTypeRepository.findAll(sortId);
        return  accountTypeMapper.toAccountTypeRespone(accounts);
    }

    @Override
    public  AccountTypeRespone createAccountType(AccountTypeCreateRequest accountTypeCreateRequest) {
          //valid-date  of  alias
        if (accountTypeRepository.existsByAlias(accountTypeCreateRequest.alias())){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Alias: " + accountTypeCreateRequest.alias() + " is already exist" );
        }
         AccountType accountType = accountTypeMapper.fromToAccountTypeCreateRequest(accountTypeCreateRequest);
         accountType.setIsActive(true);
         accountType.setIsDeleted(false);
         accountTypeRepository.save(accountType);
        return accountTypeMapper.toAccountTypeRespone(accountType);
    }

    @Override
    public AccountTypeRespone getAccountTypeByAlias(String alias) {
        AccountType accountType = accountTypeRepository.findByAlias(alias).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountType not found with alias " + alias));
        return accountTypeMapper.toAccountTypeRespone(accountType);
    }

    @Override
    public AccountTypeRespone updateAccountTypeByAlias(String alias, AccountTypeUpdateRequest accountTypeUpdateRequest) {
        // validate alias
        AccountType accountType = accountTypeRepository.findByAlias(alias).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountType not found with alias " + alias));
        log.info("Before mapping :{} , {} , {}", accountType.getId() , accountType.getDescription() , accountType.getIsDeleted());
        accountTypeMapper.fromAccountTypeUpdateRequest(accountTypeUpdateRequest, accountType);
        log.info("After mapping :{} , {} , {}", accountType.getId() , accountType.getDescription() , accountType.getIsDeleted());
        accountType  = accountTypeRepository.save(accountType);
        return accountTypeMapper.toAccountTypeRespone(accountType);
    }
    @Override
    public void deleteAccountTypeByAlias(String  alias) {
        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountType not found with id " + alias));
        accountTypeRepository.delete(accountType);
    }
}
