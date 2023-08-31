package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository myPersonRepository;
  private PasswordEncoder myPasswordEncoder;

  @Autowired
  public void setPasswordEncoder(PasswordEncoder myPasswordEncoder) {
    this.myPasswordEncoder = myPasswordEncoder;
  }

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
   * Returns a person for a given username.
   */

  public Person getPersonByUsername(String username) {
    Optional<Person> person = myPersonRepository.findByUsername(username);
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

  /**
   * Creating a loadUserByUsername.
   */

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Person> optionalPerson = myPersonRepository.findByUsername(username);
    Person person = optionalPerson.orElseThrow(() ->
            new UsernameNotFoundException("Person not found with username: " + username));

    return person;
  }
}
