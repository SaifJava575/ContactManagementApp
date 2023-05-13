package com.nit.saif.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.saif.entity.Contact;
import com.nit.saif.exception.ContactNotFoundException;
import com.nit.saif.service.IContactService;
import com.nit.saif.validator.ContactValidator;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class ContactRestController {
	@Autowired
	private IContactService service;
	@Autowired
	private ContactValidator validator;

	@PostMapping("/contact")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> saveContact(@Valid @RequestBody Contact contact) {
		ResponseEntity<?> response = null;
		Map<String, String> errors = validator.validation(contact);
		if (errors.isEmpty()) {
			String message = service.upsert(contact);
			response = ResponseEntity.ok(message);
		} else {
			response = new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/contact")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Contact>> getAllContact() {
		ResponseEntity<List<Contact>> response = null;
		List<Contact> list = service.getAllContact();
		response = ResponseEntity.ok(list);
		return response;
	}

	@GetMapping("/contact/{id}")
	 @PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer id) {
		ResponseEntity<Contact> response = null;
		try {
			Contact contact = service.getOneContacById(id);
			response = ResponseEntity.ok(contact);
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}

	@DeleteMapping("/contact/{id}")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
		ResponseEntity<String> response = null;
		try {
			String status = service.deleteContactById(id);
			response = ResponseEntity.ok(status);
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}
