package co.istad.mbbanking.feactures.accounttype;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeCreateRequest;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeRespone;
import co.istad.mbbanking.feactures.accounttype.dto.AccountTypeUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
// TODO: Implement AccountTypeController and AccountTypeService methods here.
public class AccountTypeController {
    private final AccountTypeService accountTypeService;
     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
    public AccountTypeRespone createAccountType(@Valid @RequestBody AccountTypeCreateRequest accountTypeCreateRequest){
         return accountTypeService.createAccountType(accountTypeCreateRequest);}

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AccountTypeRespone>  getAccountTypes(){
         return accountTypeService.getAccountTypeRespone();

   }
   @GetMapping("/{alias}")
   @ResponseStatus(HttpStatus.FOUND)
    public AccountTypeRespone getAccountTypeById(@PathVariable String alias){
         return accountTypeService.getAccountTypeByAlias(alias);
   }
   @PatchMapping("/{alias}")
//   @ResponseStatus(HttpStatus.OK)
    public AccountTypeRespone updateAccountTypeByAlias(
            @PathVariable String alias,
            @RequestBody AccountTypeUpdateRequest accountTypeUpdateRequest){
         return accountTypeService.updateAccountTypeByAlias(alias,accountTypeUpdateRequest);
   }
    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountTypeById(@PathVariable String alias){
         accountTypeService.deleteAccountTypeByAlias(alias);
    }
}
