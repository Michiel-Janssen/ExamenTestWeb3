package domain.service;

import domain.model.Person;
import domain.service.PersonService;

import java.util.List;

public class ContactTracingService {
    private PersonService personDb = new PersonService();

    public ContactTracingService() throws IllegalAccessException {
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonService getPersonDb() {
        return personDb;
    }
}
