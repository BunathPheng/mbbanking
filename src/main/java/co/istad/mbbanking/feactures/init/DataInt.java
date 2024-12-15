package co.istad.mbbanking.feactures.init;
import co.istad.mbbanking.domain.AccountType;
import co.istad.mbbanking.domain.Role;
import co.istad.mbbanking.domain.User;
import co.istad.mbbanking.feactures.accounttype.AccountTypeRepository;
import co.istad.mbbanking.feactures.user.RoleRepository;
import co.istad.mbbanking.feactures.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInt {
    private  final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private  final PasswordEncoder passwordEncoder;
    @PostConstruct
    void dataInt(){
        if(accountTypeRepository.count()==0) {
            AccountType payroll = new AccountType();
            payroll.setName("Payroll");
            payroll.setAlias("Payroll");
            payroll.setDescription("Payroll account  of user");
            payroll.setIsDeleted(false);
            payroll.setIsActive(true);

            AccountType saving = new AccountType();
            saving.setName("saving");
            saving.setAlias("saving");
            saving.setDescription("saving account  of user");
            saving.setIsDeleted(false);
            saving.setIsActive(true);
            accountTypeRepository.saveAll(List.of(payroll, saving));

        }
        if(userRepository.count() == 0) {
            //we  have  role  for    this    website  user  customer  manager  admin
            //Role  user
            Role  user  = new Role();
            user.setName("USER");
            //Role  customer
            Role  customer = new Role();
            customer.setName("CUSTOMER");
            //Role  manager
            Role manager = new Role();
            manager.setName("MANAGER");
            //Role  admin
            Role admin = new Role();
            admin.setName("ADMIN");
            roleRepository.saveAll(List.of( user,customer , manager , admin));
            User user1 = User.builder()
                    .name("Pheng  Bunath")
                    .gender("Male")
                    .phoneNumber("087270311")
                    .pin("123456")
                    .nationalCardId("BUNATH-1212")
                    .uuid(UUID.randomUUID().toString())
                    .password(passwordEncoder.encode("admin123"))
                    .profilePicture("/image.png")
                    .studentCardId("ISTAD-10")
                    .isBlocked(false)
                    .isDeleted(false)
                    .build();
            user1.setRoles(List.of(user , admin));
            User user2 = User.builder()
                    .name("Pheng")
                    .gender("Male")
                    .phoneNumber("087270999")
                    .pin("123456")
                    .nationalCardId("BUNATH-121223")
                    .uuid(UUID.randomUUID().toString())
                    .password(passwordEncoder.encode("manager123"))
                    .profilePicture("/image.png")
                    .studentCardId("ISTAD-103")
                    .isBlocked(false)
                    .isDeleted(false)
                    .build();
            user2.setRoles(List.of(user , manager));
            User user3 = User.builder()
                    .name("Pheng")
                    .gender("Male")
                    .phoneNumber("08181167465")
                    .pin("123456")
                    .nationalCardId("BUNATH-121256")
                    .uuid(UUID.randomUUID().toString())
                    .password(passwordEncoder.encode("customer123"))
                    .profilePicture("/image.png")
                    .studentCardId("ISTAD-104")
                    .isBlocked(false)
                    .isDeleted(false)
                    .build();
            user3.setRoles(List.of(user , customer));
            userRepository.saveAll(List.of(user1 , user2 , user3));
        }

    }
}
