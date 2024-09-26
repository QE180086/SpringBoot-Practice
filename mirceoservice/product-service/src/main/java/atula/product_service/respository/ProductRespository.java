package atula.product_service.respository;

import atula.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface ProductRespository extends JpaRepository<Product, String> {
}
