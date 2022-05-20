package com.nit.saif.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nit.saif.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
	
	public boolean existsByEmail(String email);
	public boolean existsByName(String name);
	public boolean existsByPhno(String phno);
	
}
