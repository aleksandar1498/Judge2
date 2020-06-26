package bg.softuni.judge.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.softuni.judge.persistence.entities.JpaHomework;

@Repository
public interface HomeRepository extends JpaRepository<JpaHomework, String>{

}
