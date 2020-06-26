package bg.softuni.judge.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.judge.persistence.entities.JpaUser;

@Repository
public interface UserRepository extends JpaRepository<JpaUser, String> {
	Optional<JpaUser> findByUsername(String username);
	JpaUser getOneByUsername(String username);
}
