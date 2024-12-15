package co.istad.mbbanking.feactures.accounttype.dto;

public record AccountTypeUpdateRequest(
        String  description,
        Boolean isDeleted
){
}
