package co.istad.mbbanking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50 , nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 50 , nullable = false )
    private String alias;
    @Column(nullable = false)
    private  Boolean  isActive;
    private  Boolean  isDeleted;
    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;
}
