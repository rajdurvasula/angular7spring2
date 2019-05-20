package net.rdurvasula.angjs.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.rdurvasula.angjs.sample.model.Contact;
import net.rdurvasula.angjs.sample.repository.ContactRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ContactController {

	@Autowired
	private ContactRepository contactRepo;
	
	@CrossOrigin
	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/contacts/{contactId}")
	public Resource<Contact> getById(@PathVariable("contactId") Long contactId) {
		Optional<Contact> optionalObj = contactRepo.findById(contactId);
		if (optionalObj.isPresent()) {
			return new Resource<Contact>(optionalObj.get());
		} else {
			throw new ResourceNotFoundException("contact", "id", contactId.toString());
		}
	}
	
	@CrossOrigin
	@PostMapping("/contacts")
	public Contact addContact(@Valid @RequestBody Contact contact) {
		return contactRepo.save(contact);
	}
	
	@CrossOrigin
	@PutMapping("/contacts")
	public Contact updateContact(@Valid @RequestBody Contact contact) {
		Optional<Contact> optionalObj = contactRepo.findById(contact.getId());
		if (optionalObj.isPresent()) {
			return contactRepo.save(contact);
		} else {
			throw new ResourceNotFoundException("contact", "id", String.valueOf(contact.getId()));
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/contacts/{id}")
	public Map<String, Boolean> deleteContact(@PathVariable("id") long id) {
		Map<String, Boolean> response = new HashMap<>();
		Optional<Contact> optionalObj = contactRepo.findById(id);
		if (optionalObj.isPresent()) {
			contactRepo.delete(optionalObj.get());
			response.put("deleted", Boolean.TRUE);
		} else {
			throw new ResourceNotFoundException("contact", "id", String.valueOf(id));
		}
		return response;
	}
}
