package com.notebook.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.notebook.bean.Contact;
import com.notebook.bean.Phone;

class ContactDAOTestUpdate {
	@Test
	void checkUpdateContactSimple() throws Exception {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);
		
		assertEquals(1, ContactDAO.updateContact(contact, phones));
	}
	
	@Test
	void checkUpdateContactDouble() throws Exception {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");
		
		Phone phone1 = new Phone();
		phone1.setType("phone1");
		phone1.setPhoneNumber(41987658765l);
		Phone phone2 = new Phone();
		phone2.setType("phone2");
		phone2.setPhoneNumber(41999998888l);
		
		phones.add(phone1);
		phones.add(phone2);
		
		assertEquals(1, ContactDAO.updateContact(contact, phones));
	}
	
	@Test
	void checkUpdateContactInvalidContactId() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setId(2000);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");
		
		
		Phone phone1 = new Phone();
		phone1.setType("phone1");
		phone1.setPhoneNumber(41987658765l);
		Phone phone2 = new Phone();
		phone2.setType("phone2");
		phone2.setPhoneNumber(41999998888l);
		
		phones.add(phone1);
		phones.add(phone2);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}
	
	@Test
	void checkUpdateContactMissingFirstName() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setId(2);
		//contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}
	
	@Test
	void checkUpdateContactMissingLastName() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		//contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}
	
	@Test
	void checkUpdateContactMissingDateOfBirth() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		//contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}
	
	@Test
	void checkUpdateContactMissingRelativeDegree() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		//contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}

	@Test
	void checkUpdateContactMissingPhone() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}

	@Test
	void checkUpdateContactMissingPhoneType() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");
		
		Phone phone = new Phone();
		//phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);
		
		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}

	@Test
	void checkUpdateContactMissingPhoneNumber() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");
		
		Phone phone = new Phone();
		phone.setType("phone1");
		//phone.setPhoneNumber(41987658765l);
		
		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactDAO.updateContact(contact, phones);
		});
	}

}
