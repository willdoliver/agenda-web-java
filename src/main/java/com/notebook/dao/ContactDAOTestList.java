package com.notebook.dao;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.notebook.bean.Contact;
import com.notebook.controller.ContactController;

class ContactDAOTestList {

	@Test
	void checkGetAllContactsInvalidUserId() {
		assertThrows(Exception.class, () -> {
			ContactController.getAllContacts(2000);
		});
	}

	@Test
	void checkGetAllContacts() throws Exception {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts = ContactController.getAllContacts(2);
		assertTrue(contacts.size() > 0);
	}

	@Test
	void checkGetContactByIdInvalidContactId() {
		assertThrows(Exception.class, () -> {
			ContactController.getContactById(2000);
		});
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void checkGetContactByContactId() throws Exception {
		Contact contact = new Contact();
		contact = ContactController.getContactById(2);
		assertThat(contact, instanceOf(Contact.class));
	}

}
