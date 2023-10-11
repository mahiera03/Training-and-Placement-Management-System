package in.co.placement.selection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.placement.selection.bean.ApplicantBean;
import in.co.placement.selection.bean.UserBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DatabaseException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.JDBCDataSource;

public class ApplicantModel {

	private static Logger log = Logger.getLogger(ApplicantModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM APPLICANT");
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

	public long add(ApplicantBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		System.out.println("id in app model is "+bean.getId());
		ApplicantBean existbean = findByLogin(bean.getApplicantName());
		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		System.out.println(DataUtility.GetRamdom());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO APPLICANT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getApplicantName());
			pstmt.setString(3, bean.getApplicantEmail());
			pstmt.setString(4, bean.getApplicantMobile());
			pstmt.setString(5, bean.getApplicantCity());
			pstmt.setString(6, bean.getApplicantState());
			pstmt.setString(7, bean.getApplicantCountry());
			pstmt.setString(8, bean.getApplicantAddress());
			pstmt.setDate(9, new java.sql.Date(bean.getApplicantDob().getTime()));
			pstmt.setBlob(10, bean.getApplicantImage());
			pstmt.setString(11, bean.getApplicantQualification());
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
				ex.getCause();
				ex.getStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void delete(ApplicantBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM  APPLICANT WHERE ID=?");
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
	public ApplicantBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM APPLICANT WHERE APPLICANT_NAME=?");
		ApplicantBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ApplicantBean();
				bean.setId(rs.getLong(1));
				bean.setApplicantName(rs.getString(2));
				bean.setApplicantEmail(rs.getString(3));
				bean.setApplicantMobile(rs.getString(4));
				bean.setApplicantCity(rs.getString(5));
				bean.setApplicantState(rs.getString(6));
				bean.setApplicantCountry(rs.getString(7));
				bean.setApplicantAddress(rs.getString(8));
				bean.setApplicantDob(rs.getDate(9));
				bean.setApplicantImage(rs.getBlob(10));
				bean.setApplicantQualification(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
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
	public ApplicantBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM APPLICANT WHERE ID=?");
		ApplicantBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ApplicantBean();
				bean.setId(rs.getLong(1));
				bean.setApplicantName(rs.getString(2));
				bean.setApplicantEmail(rs.getString(3));
				bean.setApplicantMobile(rs.getString(4));
				bean.setApplicantCity(rs.getString(5));
				bean.setApplicantState(rs.getString(6));
				bean.setApplicantCountry(rs.getString(7));
				bean.setApplicantAddress(rs.getString(8));
				bean.setApplicantDob(rs.getDate(9));
				bean.setApplicantImage(rs.getBlob(10));
				bean.setApplicantQualification(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
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
	
	public void update(ApplicantBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		ApplicantBean beanExist = findByLogin(bean.getApplicantName());
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("LoginId is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE APPLICANT SET APPLICANT_NAME=?,APPLICANT_EMAIL=?,APPLICANT_MOBILE=?,APPLICANT_CITY=?,APPLICANT_STATE=?,APPLICANT_COUNTRY,APPLICANT_ADDRESS,APPLICANT_DOB,APPLICANT_IMAGE,APPLICANT_QUALIFICATION"
						+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getApplicantName());
			pstmt.setString(2, bean.getApplicantEmail());
			pstmt.setString(3, bean.getApplicantMobile());
			pstmt.setString(4, bean.getApplicantCity());
			pstmt.setString(5, bean.getApplicantState());
			pstmt.setString(6, bean.getApplicantCountry());
			pstmt.setString(7, bean.getApplicantAddress());
			pstmt.setDate(8, new java.sql.Date(bean.getApplicantDob().getTime()));
			pstmt.setBlob(9, bean.getApplicantImage());
			pstmt.setString(10, bean.getApplicantQualification());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setLong(15, bean.getId());
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
	public List search(ApplicantBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}


	public List search(ApplicantBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM APPLICANT WHERE 1=1");
		System.out.println("APPLICANT NAME IS "+bean.getApplicantName() + "quali"+bean.getApplicantQualification());
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getApplicantName() != null && bean.getApplicantName().length() > 0) {
				sql.append(" AND APPLICANT_NAME like '" + bean.getApplicantName() + "%'");
			}
			if (bean.getApplicantQualification() != null && bean.getApplicantQualification().length() > 0) {
				sql.append(" AND APPLICANT_QUALIFICATION like '" + bean.getApplicantQualification() + "%'");
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
				bean = new ApplicantBean();
				bean.setId(rs.getLong(1));
				bean.setApplicantName(rs.getString(2));
				bean.setApplicantEmail(rs.getString(3));
				bean.setApplicantMobile(rs.getString(4));
				bean.setApplicantCity(rs.getString(5));
				bean.setApplicantState(rs.getString(6));
				bean.setApplicantCountry(rs.getString(7));
				bean.setApplicantAddress(rs.getString(8));
				bean.setApplicantDob(rs.getDate(9));
				bean.setApplicantImage(rs.getBlob(10));
				bean.setApplicantQualification(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e.getCause());
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
		StringBuffer sql = new StringBuffer("select * from APPLICANT");
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
				ApplicantBean bean = new ApplicantBean();
				bean.setId(rs.getLong(1));
				bean.setApplicantName(rs.getString(2));
				bean.setApplicantEmail(rs.getString(3));
				bean.setApplicantMobile(rs.getString(4));
				bean.setApplicantCity(rs.getString(5));
				bean.setApplicantState(rs.getString(6));
				bean.setApplicantCountry(rs.getString(7));
				bean.setApplicantAddress(rs.getString(8));
				bean.setApplicantDob(rs.getDate(9));
				bean.setApplicantImage(rs.getBlob(10));
				bean.setApplicantQualification(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
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
}
