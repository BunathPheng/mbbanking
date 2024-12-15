package co.istad.mbbanking.feactures.account;
import co.istad.mbbanking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAllByAccountNo(String accountNo);
    Boolean existsByAccountNo(String accountNo);
}


