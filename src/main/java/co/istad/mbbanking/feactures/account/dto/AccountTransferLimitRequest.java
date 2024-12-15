package co.istad.mbbanking.feactures.account.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountTransferLimitRequest(
        @NotNull(message = "Account   Transfer Limit is required")
        @Min(1000)
        BigDecimal amount
) {
}
