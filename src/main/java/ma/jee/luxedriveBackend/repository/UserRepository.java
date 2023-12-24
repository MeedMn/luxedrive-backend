package ma.jee.luxedriveBackend.repository;

import ma.jee.luxedriveBackend.entity.auth.User;
import ma.jee.luxedriveBackend.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<User> findUsersByAuthority_Authority(Role role);
}
