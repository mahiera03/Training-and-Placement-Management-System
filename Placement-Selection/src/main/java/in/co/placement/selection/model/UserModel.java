package in.co.placement.selection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.placement.selection.bean.UserBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DatabaseException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.exception.RecordNotFoundException;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.EmailBuilder;
import in.co.placement.selection.util.EmailMessage;
import in.co.placement.selection.util.EmailUtility;
import in.co.placement.selection.util.JDBCDataSource;


public class UserModel {
	private static Logger log = Logger.getLogger(UserModel.class);
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM USER");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		UserBean existbean = findByLogin(bean.getLogin());
		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		System.out.println(DataUtility.GetRamdom());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getLogin());
			pstmt.setString(3, bean.getPassword());
			pstmt.setLong(4, bean.getRoleId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setString(9, bean.getFirstName());
			pstmt.setString(10, bean.getLastName());
			pstmt.setString(11, bean.getConfirmPassword());
			pstmt.setString(12, bean.getMobileNo());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}


	public void delete(UserBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM  USER WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	
	public UserBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM USER WHERE LOGIN=?");
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setRoleId(rs.getLong(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				bean.setFirstName(rs.getString(9));
				bean.setLastName(rs.getString(10));
				bean.setConfirmPassword(rs.getString(11));
				bean.setMobileNo(rs.getString(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}


	public UserBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM USER WHERE ID=?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setRoleId(rs.getLong(4));
				
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				bean.setFirstName(rs.getString(9));
				bean.setLastName(rs.getString(10));
				bean.setConfirmPassword(rs.getString(11));
				bean.setMobileNo(rs.getString(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}


	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		UserBean beanExist = findByLogin(bean.getLogin());
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE USER SET LOGIN=?,PASSWORD=?,ROLEID=?,"
						+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,FIRSTNAME=?,LASTNAME=?,CONFIRMPASSWORD=?,MOBILENO=? WHERE ID=?");
			pstmt.setString(1, bean.getLogin());
			pstmt.setString(2, bean.getPassword());
			pstmt.setLong(3, bean.getRoleId());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setString(8, bean.getFirstName());
			pstmt.setString(9, bean.getLastName());
			pstmt.setString(10, bean.getConfirmPassword());
			pstmt.setString(11, bean.getMobileNo());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}



	public List search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}


	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM USER WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + bean.getRoleId());
			}
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setRoleId(rs.getLong(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				bean.setFirstName(rs.getString(9));
				bean.setLastName(rs.getString(10));
				bean.setConfirmPassword(rs.getString(11));
				bean.setMobileNo(rs.getString(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from USER");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setRoleId(rs.getLong(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				bean.setFirstName(rs.getString(9));
				bean.setLastName(rs.getString(10));
				bean.setConfirmPassword(rs.getString(11));
				bean.setMobileNo(rs.getString(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	

	public UserBean authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
		System.out.println("Login is "+login+" "+"Pass is "+password);
		StringBuffer sql = new StringBuffer("SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD = ?");
		System.out.println("uery string"+sql);
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			System.out.println("uery string"+pstmt.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setRoleId(rs.getLong(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				bean.setFirstName(rs.getString(9));
				bean.setLastName(rs.getString(10));
				bean.setConfirmPassword(rs.getString(11));
				bean.setMobileNo(rs.getString(12));
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		//System.out.println("bean"+bean.getId());
		log.debug("Model authenticate End");
		return bean;
	}
		

	

		public boolean changePassword(Long id, String oldPassword, String newPassword)
				throws RecordNotFoundException, ApplicationException {

			log.debug("model changePassword Started");
			
			boolean flag = false;
			
			UserBean beanExist = null;

			beanExist = findByPK(id);
			
			if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
				beanExist.setPassword(newPassword);
				beanExist.setConfirmPassword(newPassword);
				try {
					update(beanExist);
				} catch (DuplicateRecordException e) {
					log.error(e);
					throw new ApplicationException("LoginId is already exist");
				}
				flag = true;
			} else {
				throw new RecordNotFoundException("Old password is Invalid");
			}

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("login", beanExist.getLogin());
			map.put("password", beanExist.getPassword());
		

			String message = EmailBuilder.getChangePasswordMessage(map);

			EmailMessage msg = new EmailMessage();

			msg.setTo(beanExist.getLogin());
			msg.setSubject("Placement-Selection Password has been Changed.");
			msg.setMessage(message);
			msg.setMessageType(EmailMessage.HTML_MSG);

			try {
				EmailUtility.sendMail(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			log.debug("Model changePassword End");
			return flag;

		}

	public UserBean updateAccess(UserBean bean) throws ApplicationException {
		return null;
	}

	
	public long registerUser(UserBean bean)
			throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for Placement Selection");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		try {
			EmailUtility.sendMail(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public boolean forgetPassword(String login)
			throws ApplicationException, RecordNotFoundException, ApplicationException {
		UserBean userData = findByLogin(login);
		
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");

		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		String message = EmailBuilder.getForgetPasswordMessage1(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("Placement Selection Your Password");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;
		return flag;
	}
}
