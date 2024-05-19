package tda.darkarmy.medicalecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.medicalecommerce.model.Product;
import tda.darkarmy.medicalecommerce.model.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
}
