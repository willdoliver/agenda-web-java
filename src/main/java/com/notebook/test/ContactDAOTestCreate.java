package com.notebook.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.notebook.bean.Contact;
import com.notebook.bean.Phone;
import com.notebook.controller.ContactController;

class ContactDAOTestCreate {
	@Test
	void checkCreateContactSimple() throws Exception {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);
		
		assertEquals(1, ContactController.createContact(contact, phones));
	}
	
	@Test
	void checkCreateContactDouble() throws Exception {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
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
		
		assertEquals(1, ContactController.createContact(contact, phones));
	}
	
	@Test
	void checkCreateContactInvalidUserId() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2000);
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
			ContactController.createContact(contact, phones);
		});
	}
	
	@Test
	void checkCreateContactMissingFirstName() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		//contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}
	
	@Test
	void checkCreateContactMissingLastName() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setFirstName("Temeka");
		//contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}
	
	@Test
	void checkCreateContactMissingDateOfBirth() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		//contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}
	
	@Test
	void checkCreateContactMissingRelativeDegree() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();

		contact.setUserId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		//contact.setRelativeDegree("Amigo(a)");

		Phone phone = new Phone();
		phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);

		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}

	@Test
	void checkCreateContactMissingPhone() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}

	@Test
	void checkCreateContactMissingPhoneType() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");
		
		Phone phone = new Phone();
		//phone.setType("phone1");
		phone.setPhoneNumber(41987658765l);
		
		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}

	@Test
	void checkCreateContactMissingPhoneNumber() {
		Contact contact = new Contact();
		List<Phone> phones = new ArrayList<>();
		
		contact.setUserId(2);
		contact.setFirstName("Temeka");
		contact.setLastName("Adams");
		contact.setDateOfBirth("1990-10-18");
		contact.setRelativeDegree("Amigo(a)");
		
		Phone phone = new Phone();
		phone.setType("phone1");
		//phone.setPhoneNumber(41987658765l);
		
		phones.add(phone);

		assertThrows(Exception.class, () -> {
			ContactController.createContact(contact, phones);
		});
	}

}
