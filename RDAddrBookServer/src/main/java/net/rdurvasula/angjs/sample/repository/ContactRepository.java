package net.rdurvasula.angjs.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.rdurvasula.angjs.sample.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
