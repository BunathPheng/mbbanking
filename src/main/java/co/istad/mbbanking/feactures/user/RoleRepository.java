package co.istad.mbbanking.feactures.user;

import co.istad.mbbanking.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
