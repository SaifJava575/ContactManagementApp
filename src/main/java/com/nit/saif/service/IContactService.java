package com.nit.saif.service;

import java.util.List;

import com.nit.saif.entity.Contact;

public interface IContactService {
	
	public String upsert(Contact contact);
	public List<Contact> getAllContact();
	public Contact getOneContacById(Integer id);
	public String deleteContactById(Integer id);

}
