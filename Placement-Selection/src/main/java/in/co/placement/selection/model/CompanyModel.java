package in.co.placement.selection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DatabaseException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.exception.RecordNotFoundException;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.EmailBuilder;
import in.co.placement.selection.util.EmailMessage;
import in.co.placement.selection.util.EmailUtility;
import in.co.placement.selection.util.JDBCDataSource;


public class CompanyModel {
	private static Logger log = Logger.getLogger(CompanyModel.class);
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Company");
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

	
	public long add(CompanyBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Company VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getContactNo());
			pstmt.setString(5, bean.getWebSite());
			pstmt.setString(6, bean.getCity());
			pstmt.setString(7, bean.getState());
			pstmt.setString(8, bean.getCountry());
			pstmt.setBlob(9, bean.getLogo());
			pstmt.setString(10, bean.getAddress());
			pstmt.setString(11, bean.getDescription());
			/*pstmt.setLong(12,bean.getUserId());*/
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Company");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		System.out.println("value of pk is "+pk);
		return pk;
	}


	public void delete(CompanyBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			System.out.println("bean.getId()"+bean.getId());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM  Company WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				ex.getCause();
				e.getStackTrace();
				e.printStackTrace();
				e.getCause();
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			
			e.getStackTrace();
			e.printStackTrace();
			e.getCause();
			throw new ApplicationException("Exception : Exception in delete Company");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	
	public CompanyBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Company WHERE name=?");
		CompanyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CompanyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setWebSite(rs.getString(5));
				bean.setCity(rs.getString(6));
				bean.setState(rs.getString(7));
				bean.setCountry(rs.getString(8));
				bean.setLogo(rs.getBlob(9));
				bean.setAddress(rs.getString(10));
				bean.setDescription(rs.getString(11));
				
			/*	bean.setUserId(rs.getLong(12));*/
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Company by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}


	public CompanyBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Company WHERE ID=?");
		CompanyBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CompanyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setWebSite(rs.getString(5));
				bean.setCity(rs.getString(6));
				bean.setState(rs.getString(7));
				bean.setCountry(rs.getString(8));
				bean.setLogo(rs.getBlob(9));
				bean.setAddress(rs.getString(10));
				bean.setDescription(rs.getString(11));
				
				/*bean.setUserId(rs.getLong(12));*/
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Company by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}


	public void update(CompanyBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		/*
		 * CompanyBean beanExist = findByLogin(bean.getLogin()); if (beanExist != null
		 * && !(beanExist.getId() == bean.getId())) { throw new
		 * DuplicateRecordException("LoginId is already exist"); }
		 */
		try {
			System.out.println("bean in logo"+bean.getLogo());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Company SET NAME=?,EMAIL=?,CONTACTNO=?,WEBSITE=?,COMPANYCITY=?,STATE=?,COUNTRY=?,ADDRESS=?,companydescription=?,"
						+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getContactNo());
			pstmt.setString(4, bean.getWebSite());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getState());
			pstmt.setString(7, bean.getCountry());
			//pstmt.setBlob(8, bean.getLogo());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getDescription());
			/*pstmt.setLong(12,bean.getUserId());*/
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());
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
			throw new ApplicationException("Exception in updating Company ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}



	public List search(CompanyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}


	public List search(CompanyBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Company WHERE 1=1");
		System.out.println("bean is"+bean);
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" AND COMPANYCITY like '" + bean.getCity() + "%'");
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
				bean = new CompanyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setWebSite(rs.getString(5));
				bean.setCity(rs.getString(6));
				bean.setState(rs.getString(7));
				bean.setCountry(rs.getString(8));
				bean.setLogo(rs.getBlob(9));
				bean.setAddress(rs.getString(10));
				bean.setDescription(rs.getString(11));
				
				/*bean.setUserId(rs.getLong(12));*/
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.getMessage();
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Company");
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
		StringBuffer sql = new StringBuffer("select * from Company");
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
				CompanyBean bean = new CompanyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setWebSite(rs.getString(5));
				bean.setCity(rs.getString(6));
				bean.setState(rs.getString(7));
				bean.setCountry(rs.getString(8));
				bean.setLogo(rs.getBlob(9));
				bean.setAddress(rs.getString(10));
				bean.setDescription(rs.getString(11));
				
			/*	bean.setUserId(rs.getLong(12));*/
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Companys");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	public CompanyBean authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Company WHERE LOGIN = ? AND PASSWORD = ?");
		System.out.println("uery string"+sql);
		CompanyBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			System.out.println("uery string"+pstmt.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CompanyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setWebSite(rs.getString(5));
				bean.setCity(rs.getString(6));
				bean.setState(rs.getString(7));
				bean.setCountry(rs.getString(8));
				bean.setLogo(rs.getBlob(9));
				bean.setAddress(rs.getString(10));
				bean.setDescription(rs.getString(11));
				
				/*bean.setUserId(rs.getLong(12));*/
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return bean;
	}
}
