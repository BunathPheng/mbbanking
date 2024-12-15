package co.istad.mbbanking.feactures.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        @NotBlank(message = "Account update request account name")
        String  accountName,
        @NotNull(message = "balance is required")
                @Positive
        BigDecimal balance
) {
}
