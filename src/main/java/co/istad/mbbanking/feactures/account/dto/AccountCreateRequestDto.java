package co.istad.mbbanking.feactures.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountCreateRequestDto(
        @NotBlank(message = "Account   is  not  required ")
        String accountNo,
        @NotNull(message = "Balance  is  required")
                @Positive
        BigDecimal  balance,
        @NotBlank(message = "AccountType is required")
        String  accountTypeAlias,
        @NotBlank(message = "AccountType  is required")
        String uuidUser
) {
}
