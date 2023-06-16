package com.notebook.interfaces;

import java.util.List;

import com.notebook.bean.Contact;
import com.notebook.bean.Phone;

public interface ContactInterface {
	List<Contact> getAllContacts(int userId);
	Contact getContactById(int contactId);
	int createContact(Contact contact, List<Phone> phones);
	int updateContact(Contact contact, List<Phone> phones);
	int removeContact(Contact contact);
	int removeContactById(int contactId);
	void groupContactPhone(List<Contact> contactList, List<Phone> phoneList);
	List<Phone> fillPhoneWithContactId(List<Phone> phones, int contactId);
	String checkMissingContactData(Contact contact, List<Phone> phones);
	List<Integer> getContactIds(List<Contact> contactList);
}
