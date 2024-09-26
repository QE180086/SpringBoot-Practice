package atula.order_service.repository;

import atula.order_service.model.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PlaceOrder, Long> {
}
