package com.notebook.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.notebook.bean.Contact;
import com.notebook.controller.ContactController;

class ContactDAOTestRemove {

	void checkDeleteContact() throws Exception {
		Contact contact = new Contact();
		contact.setId(2);
		assertEquals(1, ContactController.removeContact(contact));
	}
	
	@Test
	void checkDeleteContactById() throws Exception {
		int contactId = 2;
		assertEquals(1, ContactController.removeContactById(contactId));
	}
	
	@Test
	void checkDeleteContactInvalidContactId() {
		Contact contact = new Contact();
		contact.setId(2000);
		assertThrows(Exception.class, () -> {
			ContactController.removeContact(contact);
		});
	}
	
	@Test
	void checkDeleteContactInvalidContactIdById() {
		int contactId = 2000;
		assertThrows(Exception.class, () -> {
			ContactController.removeContactById(contactId);
		});
	}

}
