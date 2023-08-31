package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository myPersonRepository;
  private final PasswordEncoder myPasswordEncoder;

  @Autowired
  public PersonService(
      PersonRepository myPersonRepository, PasswordEncoder myPasswordEncoder) {
    this.myPersonRepository = myPersonRepository;
    this.myPasswordEncoder = myPasswordEncoder;
  }

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = myPersonRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Creating a new person.
   */
  public Person create(Person person) {

    String myEncodedPassword = myPasswordEncoder.encode(person.getPassword());
    person.setPassword(myEncodedPassword);

    return myPersonRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    UserDetails person = myPersonRepository.findByUsername(username);
    return person;
  }
}
