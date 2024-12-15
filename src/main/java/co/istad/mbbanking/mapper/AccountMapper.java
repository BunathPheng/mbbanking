package co.istad.mbbanking.mapper;

import co.istad.mbbanking.domain.Account;
import co.istad.mbbanking.feactures.account.dto.AccountCreateRequestDto;
import co.istad.mbbanking.feactures.account.dto.AccountResponeDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    //Map Account  to  AccountResponse
    //Source  Account
    //Target AccountResponseDto
    //for accountType.alias  from  field name in  database account
    //accountTypeAlias  is  name  AccountRequestDto  client request
//    @Mapping(source = "accountType.alias", target="accountTypeAlias")
    AccountResponeDto toAccountResponeDto(Account account);
    Account  fromAccountCreateRequestDto(AccountCreateRequestDto accountCreateRequestDto);

}
