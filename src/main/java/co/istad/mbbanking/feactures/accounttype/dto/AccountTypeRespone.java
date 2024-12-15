package co.istad.mbbanking.feactures.accounttype.dto;

public record AccountTypeRespone(
        String name,
        String alias,
        String description,
        String isDeleted
) {
}
