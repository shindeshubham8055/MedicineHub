package tda.darkarmy.medicalecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.medicalecommerce.model.Cart;
import tda.darkarmy.medicalecommerce.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public Cart findByUser(User user);
}
