package co.istad.mbbanking.feactures.account.dto;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeRespone;
import lombok.Builder;
import java.math.BigDecimal;
@Builder
public record AccountResponeDto(
        String  accountNo,
        BigDecimal balance,
        String  accountName,
        String  alias,
        AccountTypeRespone accountType
) {
}
