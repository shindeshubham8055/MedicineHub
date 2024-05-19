package tda.darkarmy.medicalecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.medicalecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
}
