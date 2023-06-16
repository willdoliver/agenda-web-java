package com.notebook.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.notebook.bean.Contact;
import com.notebook.bean.Phone;

public class ContactDAO {
	public static List<Contact> getContactsByUserId(int userId) throws Exception {
		List<Contact> contactList = new ArrayList<Contact>();

		try {
			Connection conn = ConnectionDAO.getConnection();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM contacts WHERE userId = ? AND isDeleted = 0");
			pstm.setInt(1, userId);

			ResultSet rs = pstm.executeQuery();
			System.out.println("getAllContacts::ResultSet");
			while (rs.next()) {
				Contact contact = new Contact();

				contact.setId(rs.getInt("id"));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contact.setIsDeleted(rs.getBoolean("isDeleted"));
				contact.setDateOfBirth(rs.getString("dateOfBirth"));
				contact.setCreatedAt(rs.getDate("createdAt"));
				contact.setUpdatedAt(rs.getDate("updatedAt"));
				contact.setRelativeDegree(rs.getString("relativeDegree"));

				contactList.add(contact);
			}
			System.out.println("getAllContacts::Call::getPhones");

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}

		return contactList;
	}

	public static Contact getContactByContactId(int contactId) throws Exception {
		Contact contact = new Contact();

		try {
			Connection conn = ConnectionDAO.getConnection();

			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM contacts WHERE id = ? and isDeleted = 0");
			pstm.setInt(1, contactId);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				contact.setId(rs.getInt("id"));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contact.setDateOfBirth(rs.getString("dateOfBirth"));
				contact.setIsDeleted(rs.getBoolean("isDeleted"));
				contact.setCreatedAt(rs.getDate("createdAt"));
				contact.setRelativeDegree(rs.getString("relativeDegree"));
				contact.setUserId(rs.getInt("userId"));
				contact.setUpdatedAt(rs.getDate("updatedAt"));
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		return contact;
	}

	public static int createContact(Contact contact) throws Exception {
		System.out.println("createContact::Start");
		int contactId = 0;
		int statusContact = 0;

		try {
			Connection conn = ConnectionDAO.getConnection();
			
			String generatedColumns[] = { "id" };
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO contacts "
					+ "(userId, firstName, lastName, dateOfBirth, relativeDegree, createdAt) " + "VALUES (?,?,?,?,?,?)",
					generatedColumns);

			System.out.println("createContact::Param::userId: " + contact.getUserId());
			System.out.println("createContact::Param::FirstName: " + contact.getFirstName());
			System.out.println("createContact::Param::LastName: " + contact.getLastName());
			System.out.println("createContact::Param::DOD: " + contact.getDateOfBirth());
			System.out.println("createContact::Param::Parent: " + contact.getRelativeDegree());

			pstm.setInt(1, contact.getUserId());
			pstm.setString(2, contact.getFirstName());
			pstm.setString(3, contact.getLastName());
			pstm.setDate(4, Date.valueOf(contact.getDateOfBirth()));
			pstm.setString(5, contact.getRelativeDegree());
			pstm.setDate(6, new Date(new java.util.Date().getTime()));

			statusContact = pstm.executeUpdate();
			System.out.println("createContact::Param::statusContact: " + statusContact);

			// Get id created
			ResultSet rs = pstm.getGeneratedKeys();

			if (rs.next()) {
				contactId = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		System.out.println("createContact::Finish::contactId: " + contactId);
		return contactId;
	}

	public static int updateContact(Contact contact) throws Exception {
		int status = 0;

		System.out.println("updateContact::Started");
		try {
			Connection conn = ConnectionDAO.getConnection();

			PreparedStatement pstm = conn.prepareStatement("UPDATE contacts SET " 
					+ "firstName=?, " 
					+ "lastName=?, "
					+ "dateOfBirth=?, " 
					+ "relativeDegree=?, " 
					+ "updatedAt=? " 
					+ "WHERE id=?");

			pstm.setString(1, contact.getFirstName());
			pstm.setString(2, contact.getLastName());
			pstm.setString(3, contact.getDateOfBirth());
			pstm.setString(4, contact.getRelativeDegree());
			pstm.setDate(5, new Date(new java.util.Date().getTime()));
			pstm.setInt(6, contact.getId());

			status = pstm.executeUpdate();
			System.out.println("updateContact::Param::statusContact: " + status);

		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		System.out.println("updateContact::Param::status: " + status);
		return status;
	}

	public static int removeContact(Contact contact) throws Exception {
		int status = 0;

		try {
			Connection conn = ConnectionDAO.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE contacts SET isDeleted = 1 WHERE id = ?");
			pstm.setInt(1, contact.getId());
			status = pstm.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		return status;
	}

	public static int removeContactById(int contactId) throws Exception {
		int status = 0;

		try {
			Connection conn = ConnectionDAO.getConnection();
			PreparedStatement pstm = conn.prepareStatement("UPDATE contacts SET isDeleted = 1 WHERE id = ?");
			pstm.setInt(1, contactId);
			status = pstm.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			throw new Exception(e);
		}
		return status;
	}

	public static int createPhone(List<Phone> phones) {
		int status = 0;

		System.out.println("createPhone::Start");
		try {
			Connection conn = ConnectionDAO.getConnection();
			String sql = "INSERT INTO phones (contactId, type, phoneNumber, createdAt) VALUES ";

			System.out.println("createPhone::Params");
			for (Phone phone : phones) {
				System.out.println("createPhone::Params::cttId: " + phone.getContactId());
				System.out.println("createPhone::Params::Type: " + phone.getType());
				System.out.println("createPhone::Params::Number: " + phone.getPhoneNumber());
				System.out.println("createPhone::Params::Criado: " + new Date(new java.util.Date().getTime()));
				sql += "(" + phone.getContactId() + ",'" + phone.getType() + "'," + phone.getPhoneNumber() + ",'"
						+ new Date(new java.util.Date().getTime()) + "'),";

				System.out.println("--- --- ---");
			}
			sql = removeLastChar(sql);
			System.out.println("createPhone::Params::SQL: " + sql);
			PreparedStatement pstm = conn.prepareStatement(sql);
			status = pstm.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("createPhone::Finish::status: " + status);
		return status;
	}

	public static int updatePhone(List<Phone> phones, int contactId) {
		System.out.println("UpdatePhone::Started");
		int status = 0;
		int statusDelete = 0;
		int statusInsert = 0;

		try {
			Connection conn = ConnectionDAO.getConnection();

			PreparedStatement pstm = conn.prepareStatement("DELETE FROM phones WHERE contactId = ?");
			pstm.setInt(1, contactId);
			System.out.println("UpdatePhone::Param::contactId: " + contactId);

			statusDelete = pstm.executeUpdate();

			System.out.println("UpdatePhone::Param::statusDelete: " + statusDelete);

			if (statusDelete > 0) {
				Connection conn2 = ConnectionDAO.getConnection();
				String sql = "INSERT INTO phones (contactId, type, phoneNumber, createdAt) VALUES ";

				System.out.println("updatePhone::Params");
				for (Phone phone : phones) {
					System.out.println("updatePhone::Params::cttId: " + phone.getContactId());
					System.out.println("updatePhone::Params::Type: " + phone.getType());
					System.out.println("updatePhone::Params::Number: " + phone.getPhoneNumber());
					System.out.println("updatePhone::Params::Criado: " + new Date(new java.util.Date().getTime()));
					sql += "(" + phone.getContactId() + ",'" + phone.getType() + "'," + phone.getPhoneNumber() + ",'"
							+ new Date(new java.util.Date().getTime()) + "'),";

					System.out.println("--- --- ---");
				}
				sql = removeLastChar(sql);
				System.out.println("updatePhone::Params::SQL: " + sql);
				PreparedStatement pstm2 = conn2.prepareStatement(sql);
				statusInsert = pstm2.executeUpdate();
			}

			if (statusDelete > 0 && statusInsert > 0) {
				status = 1;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("UpdatePhone::Finished::status: " + status);
		return status;
	}

	public static List<Phone> getPhonesByIds(List<Integer> contactIds) {
		List<Phone> phoneList = new ArrayList<Phone>();
		System.out.println("getAllContacts::getPhone::Started");

		try {
			String ids = "";
			Connection conn = ConnectionDAO.getConnection();

			// System.out.println(contactIds.getClass().getName());
			Iterator<Integer> iterate = contactIds.iterator();
			while (iterate.hasNext()) {
				ids += Integer.toString(iterate.next()) + ",";
			}
			ids = removeLastChar(ids);
			System.out.println("phones ids: " + ids);

			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM phones WHERE contactId IN (" + ids + ")");

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				// System.out.println("getAllContacts::getPhones::Create Phone::phoneID " +
				// rs.getInt("id"));
				Phone phone = new Phone();

				phone.setId(rs.getInt("id"));
				phone.setType(rs.getString("type"));
				phone.setPhoneNumber(rs.getLong("phoneNumber"));
				phone.setContactId(rs.getInt("contactId"));
				phone.setCreatedAt(rs.getDate("createdAt"));
				phone.setUpdatedAt(rs.getDate("updatedAt"));

				phoneList.add(phone);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("getAllContacts::getPhones::Finished");

		return phoneList;
	}

	public static String checkUserIdExists(Contact contact) {
		String error = "userId invalido";
		
		try {
			if (contact.getUserId() > 0) {
				Connection conn = ConnectionDAO.getConnection();
				PreparedStatement pstm = conn.prepareStatement("SELECT id FROM users WHERE id = ?");
				pstm.setInt(1, contact.getUserId());
				
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					if (rs.getInt("id") > 0) {
						error = "";
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return error;
	}
	
	public static String checkUserIdExistsById(int userId) {
		String error = "userId invalido";
		
		try {
			if (userId > 0) {
				Connection conn = ConnectionDAO.getConnection();
				PreparedStatement pstm = conn.prepareStatement("SELECT id FROM users WHERE id = ?");
				pstm.setInt(1, userId);

				ResultSet rs = pstm.executeQuery();

				while(rs.next()) {
					if (rs.getInt("id") > 0) {
						error = "";
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return error;
	}
	
	public static String checkContactIdExists(Contact contact) {
		String error = "contactId invalido";
		
		try {
			if (contact.getId() > 0) {
				Connection conn = ConnectionDAO.getConnection();
				PreparedStatement pstm = conn.prepareStatement("SELECT id FROM contacts WHERE id = ? AND isDeleted = 0");
				pstm.setInt(1, contact.getId());
				
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					if (rs.getInt("id") > 0) {
						error = "";
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return error;
	}
	
	public static String checkContactIdExistsById(int contactId) {
		String error = "contactId invalido";
		
		try {
			if (contactId > 0) {
				Connection conn = ConnectionDAO.getConnection();
				PreparedStatement pstm = conn.prepareStatement("SELECT id FROM contacts WHERE id = ? AND isDeleted = 0");
				pstm.setInt(1, contactId);
				
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					if (rs.getInt("id") > 0) {
						error = "";
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return error;
	}
	
	private static String removeLastChar(String str) {
		try {
			if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
				str = str.substring(0, str.length() - 1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}

}
