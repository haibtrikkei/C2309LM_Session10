package upload_firebase.demo_thymeleaf_firebase.service;

import upload_firebase.demo_thymeleaf_firebase.model.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findById(Integer personId);
    Person insert(Person person);
}
