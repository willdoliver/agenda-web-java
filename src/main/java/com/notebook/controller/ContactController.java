package com.notebook.controller;

import com.notebook.bean.Contact;
import com.notebook.bean.Phone;
import com.notebook.dao.ContactDAO;

import java.util.ArrayList;
import java.util.List;


public class ContactController {

	public static List<Contact> getAllContacts(int userId) throws Exception {
		List<Contact> contactList = new ArrayList<Contact>();
		List<Integer> phoneList = new ArrayList<Integer>();
		List<Phone> phones = new ArrayList<Phone>();
		String validationError = "";

		try {
			validationError = ContactDAO.checkUserIdExistsById(userId);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}

			contactList = ContactDAO.getContactsByUserId(userId);

			if (contactList.size() > 0) {
				phoneList = getContactIds(contactList);
				phones = ContactDAO.getPhonesByIds(phoneList);
				
				groupContactPhone(contactList, phones);
			}

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}

		return contactList;
	}

	public static Contact getContactById(int contactId) throws Exception {
		List<Phone> phones = new ArrayList<Phone>();
		List<Integer> phoneList = new ArrayList<Integer>();
		Contact contact = new Contact();
		String validationError = "";

		try {
			phoneList.add(contactId);
			
			validationError = ContactDAO.checkContactIdExistsById(contactId);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}
			
			contact = ContactDAO.getContactByContactId(contactId);
			phones = ContactDAO.getPhonesByIds(phoneList);

			contact.setPhoneList(phones);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		return contact;
	}

	public static int createContact(Contact contact, List<Phone> phones) throws Exception {
		System.out.println("createContact::Start");
		int status = 0;
		int contactId = 0;
		int statusPhone = 0;
		String validationError = "";

		try {
			validationError = ContactDAO.checkUserIdExists(contact);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}

			validationError = checkMissingContactData(contact, phones);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}
			
			contactId = ContactDAO.createContact(contact);

			if (contactId > 0) {
				phones = fillPhoneWithContactId(phones, contactId);
				statusPhone = ContactDAO.createPhone(phones);
				System.out.println("createContact::Param::statusPhone: " + statusPhone);

			}

			if (contactId > 0 && statusPhone > 0) {
				status = 1;
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		System.out.println("createContact::Finish::status: " + status);
		return status;
	}

	public static int updateContact(Contact contact, List<Phone> phones) throws Exception {
		int status = 0;
		int statusContact = 0;
		int statusPhone = 0;
		String validationError = "";

		System.out.println("updateContact::Started");
		try {
			validationError = ContactDAO.checkContactIdExists(contact);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}

			validationError = checkMissingContactData(contact, phones);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}

			statusContact = ContactDAO.updateContact(contact);

			if (statusContact == 1) {
				phones = fillPhoneWithContactId(phones, contact.getId());
				statusPhone = ContactDAO.updatePhone(phones, contact.getId());
				System.out.println("updateContact::Param::statusPhone: " + statusPhone);
			}

			if (statusContact == 1 && statusPhone > 0) {
				status = 1;
			}

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		System.out.println("updateContact::Param::status: " + status);
		return status;
	}

	public static int removeContact(Contact contact) throws Exception {
		int status = 0;
		String validationError = "";

		try {
			validationError = ContactDAO.checkContactIdExists(contact);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}

			status = ContactDAO.removeContact(contact);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		return status;
	}

	public static int removeContactById(int contactId) throws Exception {
		int status = 0;
		String validationError = "";

		try {
			validationError = ContactDAO.checkContactIdExistsById(contactId);
			if (validationError.length() > 0) {
				throw new Exception(validationError);
			}
			
			status = ContactDAO.removeContactById(contactId);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		return status;
	}

	private static void groupContactPhone(List<Contact> contactList, List<Phone> phoneList) {
		System.out.println("getAllContacts::groupContactPhone::Started");
		for (Contact contact : contactList) {
			// System.out.println("getAllContacts::groupContactPhone::contactId:
			// "+contact.getId());
			List<Phone> contactListPhone = new ArrayList<Phone>();

			for (Phone phone : phoneList) {
				// System.out.println("getAllContacts::groupContactPhone::phoneId:
				// "+phone.getId());
				if (contact.getId() == phone.getContactId()) {
					// System.out.println("getAllContacts::groupContactPhone::phone Added");
					contactListPhone.add(phone);
				}
			}
			contact.setPhoneList(contactListPhone);
			// System.out.println("getAllContacts::groupContactPhone::Contact Set");
		}
		System.out.println("getAllContacts::groupContactPhone::Finished");
	}

	private static List<Phone> fillPhoneWithContactId(List<Phone> phones, int contactId) {
		try {
			System.out.println("fillPhoneWithContactId::Started");
			for (Phone phone : phones) {
				phone.setContactId(contactId);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return phones;
	}
	
	private static String checkMissingContactData(Contact contact, List<Phone> phones) {
		String error = "";

		try {
			if (contact.getFirstName() == null || contact.getFirstName().isEmpty()) {
				return "Faltando nome";
			}
			if (contact.getLastName() == null || contact.getLastName().isEmpty()) {
				return "Faltando sobrenome";
			}
			if (contact.getDateOfBirth() == null || contact.getDateOfBirth().isEmpty()) {
				return "Faltando data de nascimento";
			}
			if (contact.getRelativeDegree() == null || contact.getRelativeDegree().isEmpty()) {
				return "Faltando parentesco";
			}
			if (phones == null || phones.isEmpty()) {
				return "Faltando telefones";
			}
			
			for (Phone phone : phones) {
				if (phone.getType() == null || phone.getType().isEmpty()) {
					return "Faltando tipo do telefone";
				}
				if (phone.getPhoneNumber() == 0) {
					return "Faltando numero do telefone";
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return error;
	}
	
	private static List<Integer> getContactIds(List<Contact> contactList) throws Exception {
		List<Integer> phoneList = new ArrayList<Integer>();
		
		try {
			for (Contact contact : contactList) {
				phoneList.add(contact.getId());
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		
		return phoneList;
	}

}
