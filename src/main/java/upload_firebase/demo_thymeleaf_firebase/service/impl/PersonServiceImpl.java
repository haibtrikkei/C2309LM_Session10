package upload_firebase.demo_thymeleaf_firebase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upload_firebase.demo_thymeleaf_firebase.model.entity.Person;
import upload_firebase.demo_thymeleaf_firebase.repository.PersonRepository;
import upload_firebase.demo_thymeleaf_firebase.service.PersonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Integer personId) {
        return personRepository.findById(personId).orElseThrow(()->new NoSuchElementException("Khong ton tai person co id: "+personId));
    }

    @Override
    public Person insert(Person person) {
        return personRepository.save(person);
    }
}
