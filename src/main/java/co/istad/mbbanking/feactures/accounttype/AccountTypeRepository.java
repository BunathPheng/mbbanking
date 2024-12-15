package co.istad.mbbanking.feactures.accounttype;
import co.istad.mbbanking.domain.Account;
import co.istad.mbbanking.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    Optional<AccountType> findByAlias(String  alias);
    List<AccountType> findAll();
    AccountType findAllById(Integer id);
    Boolean existsAccountTypesById(Integer id);
    void deleteAccountTypeByAccounts(List<Account> accounts);
    void deleteAccountTypeById(Integer id);
    boolean existsByAlias(String alias);
}
