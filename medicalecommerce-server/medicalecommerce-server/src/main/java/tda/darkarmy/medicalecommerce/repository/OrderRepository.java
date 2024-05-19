package tda.darkarmy.medicalecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.medicalecommerce.model.Orders;
import tda.darkarmy.medicalecommerce.model.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);
}
