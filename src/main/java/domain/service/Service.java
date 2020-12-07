package domain.service;

import domain.db.contact.ContactDB;
import domain.db.contact.ContactDBSQL;
import domain.db.coronapositive.CoronaPositiveDB;
import domain.db.coronapositive.CoronaPositiveDBSQL;
import domain.db.person.PersonDB;
import domain.db.person.PersonDBSQL;
import domain.model.Contact;
import domain.model.CoronaPositiveModel;
import domain.model.Person;

import java.util.List;

public class Service {
    private PersonDB persondb = new PersonDBSQL();
    private CoronaPositiveDB coronaPositivedb = new CoronaPositiveDBSQL();
    private ContactDB contactdb = new ContactDBSQL();

    //Contact

    public void addContact(Contact contact) {
        contactdb.add(contact);
    }

    public List<Contact> getContactAll() {
        return contactdb.getAll();
    }

    public String popularFitness() {
        return contactdb.popularFitness();
    }

    public List<Contact> getPossibleCorona(String Email) {return contactdb.getPossibleCorona(Email);}

    //CoronaPositive

    public void addCoronaPositive(CoronaPositiveModel coronaPositiveModel){
        coronaPositivedb.add(coronaPositiveModel);
    }

    public List<CoronaPositiveModel> getCoronaPositiveAll(){
        return coronaPositivedb.getAll();
    }

    //Person

    public void addPerson(Person person){
        persondb.add(person);
    }

    public List<Person> getPersonAll(){
        return persondb.getAll();
    }

    public Person get(String id) {
        return persondb.get(id);
    }
}
