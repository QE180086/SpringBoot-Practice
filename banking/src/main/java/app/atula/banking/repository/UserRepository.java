package app.atula.banking.repository;

import app.atula.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByEmail(String email);
    public User findByAccountNumber(String accountNumber);







}
