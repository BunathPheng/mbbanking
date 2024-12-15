package co.istad.mbbanking.feactures.accounttype.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeCreateRequest(
        @NotBlank(message = "AccountTypeName is not blank")
        String name ,
        @NotBlank(message = "Alias is not blank")
        String alias,
        String description
) {
}
