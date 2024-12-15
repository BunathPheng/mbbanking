package co.istad.mbbanking.domain;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acId;
    private String accountNo;
    private String accountName;
    private String  alias;
    private Boolean isHidden;
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private Boolean isDeleted;
    @ManyToOne
    private AccountType accountType;

    @OneToOne
    private  Card card;

    @JoinTable(
              name = "user_account"
//            , joinColumns = @JoinColumn(name = "account_id" , referencedColumnName = "ac_i")
//            , inverseJoinColumns = @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    )
    @ManyToOne
    private User  user;
    @OneToMany(mappedBy = "owner")
    private List<Transition>  transactions;

}
