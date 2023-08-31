//package com.betrybe.agrix.evaluation;
//
//import com.betrybe.agrix.ebytr.staff.entity.Person;
//import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
//import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
//import com.betrybe.agrix.ebytr.staff.service.PersonService;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class PersonServiceTest {
//  @Autowired
//  PersonService myPersonService;
//
//  @MockBean
//  PersonRepository myPersonRepository;
//
//  @Test
//  public void personRetrievalTest() {
//    Person myPerson = new Person();
//    myPerson.setId(1L);
//    myPerson.setUsername("Son Goku");
//    myPerson.setPassword("12345");
//
//    Mockito.when(myPersonRepository.findById(eq(1L)))
//      .thenReturn(Optional.of(myPerson));
//
//    Person returnedMyPerson = myPersonService.getPersonById(1L);
//
//    Mockito.verify(myPersonRepository).findById(eq(1L));
//
//    assertEquals(returnedMyPerson.getId(), myPerson.getId());
//    assertEquals(returnedMyPerson.getUsername(), myPerson.getUsername());
//    assertEquals(returnedMyPerson.getPassword(), myPerson.getPassword());
//  }
//
//  @Test
//  public void personRetrievalNotFoundTest() {
//    Mockito.when(myPersonRepository.findById(any()))
//      .thenReturn(Optional.empty());
//
//    assertThrows(PersonNotFoundException.class, () -> myPersonService.getPersonById(99999L));
//
//    Mockito.verify(myPersonRepository).findById(eq(99999L));
//  }
//
//  @Test
//  public void personRetrievalByUsernameSuccessfullyTest() {
//    String username = "Goku";
//    Person expectedMyPerson = new Person();
//    Mockito.when(myPersonRepository.findByUsername(eq(username)))
//      .thenReturn(Optional.of(expectedMyPerson));
//
//    Person returnedUsername = myPersonService.getPersonByUsername(username);
//
//    assertEquals(expectedMyPerson, returnedUsername);
//  }
//
//  @Test
//  public void personRetrievalByUsernameNotFoundTest() {
//    String username = "Bulma";
//    Mockito.when(myPersonRepository.findByUsername(eq(username)))
//      .thenReturn(Optional.empty());
//
//    assertThrows(PersonNotFoundException.class,
//      () -> myPersonService.getPersonByUsername(username));
//
//    Mockito.verify(myPersonRepository).findByUsername(eq(username));
//  }
//
//  @Test
//  public void personCreationTest() {
//    Person myPerson = new Person();
//    myPerson.setUsername("Son Goku");
//    myPerson.setPassword("12345");
//
//    Person returnedMyPerson = new Person();
//    returnedMyPerson.setId(2L);
//    returnedMyPerson.setUsername(myPerson.getUsername());
//    returnedMyPerson.setPassword((myPerson.getPassword()));
//
//    Mockito.when(myPersonRepository.save(any(Person.class)))
//      .thenReturn((returnedMyPerson));
//
//    Person savedMyPerson = myPersonService.create(myPerson);
//
//    Mockito.verify(myPersonRepository).save(any(Person.class));
//
//    assertEquals(2L, savedMyPerson.getId());
//    assertEquals(myPerson.getUsername(), savedMyPerson.getUsername());
//    assertEquals(myPerson.getPassword(), savedMyPerson.getPassword());
//
//  }
//}