package co.istad.mbbanking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false, length = 50, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 10)
    private String pin;
    @Column(nullable = false, length = 20, unique = true)
    private String nationalCardId;

    @Column(length = 20, unique = true)
    private String studentCardId;

    @Column(nullable = false)
    private String password;

    private String profilePicture;

    @Column(length = 100)
    private String village;

    @Column(length = 100)
    private String sangkatOrCommune;

    @Column(length = 100)
    private String khanOrDistrict;

    @Column(length = 100)
    private String cityOrProvince;

    @Column(length = 100)
    private String street;

    @Column(length = 50)
    private String position;

    private BigDecimal monthlyIncomeRange;

    @Column(length = 50)
    private String employer;

    @Column(length = 50)
    private String companyName;

    @Column(length = 50)
    private String mainSourceOfIncome;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Boolean isBlocked;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles"  ,
            joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id")
            )
    private  List<Role>  roles;
}
