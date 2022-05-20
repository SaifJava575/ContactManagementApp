package com.nit.saif.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.saif.entity.Contact;
import com.nit.saif.exception.ContactNotFoundException;
import com.nit.saif.repository.ContactRepository;
import com.nit.saif.service.IContactService;


@Service
public class ContactServiceImpl implements IContactService {
	@Autowired
	private ContactRepository repo;

	@Override
	public String upsert(Contact contact) {
		repo.save(contact);
		return "Contact created Successfully";
	}

	@Override
	public List<Contact> getAllContact() {
		return repo.findAll();
	}

	@Override
	public Contact getOneContacById(Integer id) {
		Optional<Contact> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			 throw new ContactNotFoundException("Contact"+id+"Not exist");
		}	
	}

	@Override
	public String deleteContactById(Integer id) {
		Optional<Contact> opt=repo.findById(id);
		
		if(opt.isPresent()) {
			Contact contact= opt.get();
			System.out.println(contact);
			contact.setActiveSw("N");
			repo.save(contact);
		} else {
			 throw new ContactNotFoundException("Contact"+id+"Not exist");
		}	
		return "Contact Deleted SuccessFully";
	}

}
