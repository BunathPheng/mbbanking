package co.istad.mbbanking.feactures.accounttype;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeCreateRequest;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeRespone;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeUpdateRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AccountTypeService {

    /**
     * find  all accountTypes
     * @return {@link List<AccountTypeRespone>}
     */
    List<AccountTypeRespone> getAccountTypeRespone();
    AccountTypeRespone createAccountType(AccountTypeCreateRequest accountTypeCreateRequest);
    AccountTypeRespone getAccountTypeByAlias(String  alias);
    AccountTypeRespone updateAccountTypeByAlias(String alias , AccountTypeUpdateRequest accountTypeUpdateRequest);
    void deleteAccountTypeByAlias(String alias);
}
