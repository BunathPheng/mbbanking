package co.istad.mbbanking.feactures.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRenameRequest(
        @NotBlank(message = "alias  is not blank")
        String  alias
) {
}
