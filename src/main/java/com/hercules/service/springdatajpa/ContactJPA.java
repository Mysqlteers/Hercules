package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.repository.ContactRepository;
import com.hercules.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ContactJPA implements ContactService {
    private ContactRepository contactRepository;

    public ContactJPA(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Set<Contact> findAll() {
        Set<Contact> contactSet = new HashSet<>();
        contactRepository.findAll().forEach(contactSet::add);
        return contactSet;
    }

    @Override
    public Contact save(Contact object) {
        return contactRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        contactRepository.deleteById(aLong);
    }

    @Override
    public Optional<Contact> findById(Long aLong) {
        return contactRepository.findById(aLong);
    }

    @Override
    public Optional<Contact> findContactByCaseId(Long caseId) {
        return contactRepository.findContactByCaseId(caseId);
    }
}
