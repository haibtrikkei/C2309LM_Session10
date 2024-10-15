package upload_firebase.demo_thymeleaf_firebase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upload_firebase.demo_thymeleaf_firebase.model.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
