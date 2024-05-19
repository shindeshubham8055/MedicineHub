package tda.darkarmy.medicalecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.medicalecommerce.model.Role;
import tda.darkarmy.medicalecommerce.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRoleName(Role role);
}
