package ma.jee.luxedriveBackend.repository;

import ma.jee.luxedriveBackend.entity.auth.Authority;
import ma.jee.luxedriveBackend.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(Role authority);
}
