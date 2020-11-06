package domain.service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.util.List;


public class PersonService {
	private PersonDB db = new PersonDBSQL();

	public void add(Person person){
		db.add(person);
	}
	
	public List<Person> getAll(){
		return db.getAll();
	}

	/*
	public Person get(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}
	 */

	/*
	public void update(Person person){
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}

	 */

	/*
	public void delete(String personId){
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}

	public int getNumberOfPersons() {
		return persons.size();
	}

	 */
}
