package com.nit.saif.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nit.saif.entity.Contact;
import com.nit.saif.repository.ContactRepository;

@Component
public class ContactValidator {
	@Autowired
	private ContactRepository repo;
	
	public Map<String, String> validation(@Valid Contact contact ){
		Map<String,String> errors=new HashMap<>();
		
		if(repo.existsByEmail(contact.getEmail())) {
			errors.put("email", contact.getEmail()+"alrady exist");			
		}
		if(repo.existsByName(contact.getName())) {
			errors.put("name", contact.getName()+"alrady exist");			
		}
		
		if(repo.existsByName(contact.getPhno())) {
			errors.put("phno", contact.getPhno()+"alrady exist");			
		}
		return errors;
	}

}
