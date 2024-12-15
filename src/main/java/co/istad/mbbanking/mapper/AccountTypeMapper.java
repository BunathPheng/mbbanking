package co.istad.mbbanking.mapper;
import co.istad.mbbanking.domain.AccountType;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeCreateRequest;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeRespone;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    //Partially
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest ,
                                      @MappingTarget AccountType accountType);
    AccountType fromToAccountTypeCreateRequest(AccountTypeCreateRequest accountTypeCreateRequest);
    AccountType fromToAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest);
    AccountTypeRespone toAccountTypeRespone(AccountType accountType);
    List<AccountTypeRespone> toAccountTypeRespone(List<AccountType> accountTypes);
}
