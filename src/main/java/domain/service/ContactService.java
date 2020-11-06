package domain.service;

import domain.db.*;
import domain.model.Contact;

import java.util.List;

public class ContactService {
    private ContactDB db = new ContactDBSQL();

    public void add(Contact contact) {
        db.add(contact);
    }

    public List<Contact> getAll() {
        return db.getAll();
    }

    public String popularFitness() {
        return db.popularFitness();
    }
}
