package bg.softuni.judge.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.judge.enums.Roles;
import bg.softuni.judge.persistence.Role;
import bg.softuni.judge.persistence.entities.JpaRole;

@Repository
public interface RoleRepository extends JpaRepository<JpaRole, String> {
	Optional<JpaRole> findByRole(Roles role);
}
