package in.co.placement.selection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.placement.selection.bean.CompanyBean;
import in.co.placement.selection.bean.JobBean;
import in.co.placement.selection.bean.StudentBean;
import in.co.placement.selection.bean.UserBean;
import in.co.placement.selection.exception.ApplicationException;
import in.co.placement.selection.exception.DatabaseException;
import in.co.placement.selection.exception.DuplicateRecordException;
import in.co.placement.selection.util.DataUtility;
import in.co.placement.selection.util.JDBCDataSource;

public class JobModel {

	private static Logger log = Logger.getLogger(JobModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM JOB");
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

	public long add(JobBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		System.out.println(DataUtility.GetRamdom());
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO JOB VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getJobTitle());
			pstmt.setString(3, bean.getCompanyName());
			pstmt.setString(4, bean.getJobType());
			pstmt.setDate(5, new java.sql.Date(bean.getJobPostDate().getTime()));
			pstmt.setString(6, bean.getSkills());
			pstmt.setString(7, bean.getCity());
			pstmt.setString(8, bean.getDescription());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setInt(13, bean.getCompanyId());
			
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

	public JobBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("select job.id,jobtitle,name,jobtype,posteddate,skills,city,description,logo from job inner join company on job.company_id=company.id where job.ID=?");
		JobBean bean = null;
		Connection conn = null;
		CompanyBean bean2=new CompanyBean();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				bean = new JobBean();
				bean2.setLogo(rs.getBlob(9));
				bean.setId(rs.getLong(1));
				bean.setJobTitle(rs.getString(2));
				bean.setCompanyName(rs.getString(3));
				bean.setJobType(rs.getString(4));
				bean.setJobPostDate(rs.getDate(5));
				bean.setSkills(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				bean.setCompanyBean(bean2);
				/*bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));*/
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

	public List search(JobBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public void delete(JobBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM  JOB WHERE ID=?");
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

	public List search(JobBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		System.out.println("Job title"+bean.getJobTitle()+" Job skill"+bean.getSkills());
		//StringBuffer sql = new StringBuffer("SELECT * FROM JOB WHERE 1=1");
			StringBuffer sql = new StringBuffer("select job.id,jobtitle,name,jobtype,posteddate,skills,city,description from job inner join company on job.company_id=company.id");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND job.id = " + bean.getId());
			}
			if (bean.getJobTitle() != null && bean.getJobTitle().length() > 0) {
				sql.append(" AND JOBTITLE like '" + bean.getJobTitle() + "%'");
			}
			if (bean.getSkills() != null && bean.getSkills().length() > 0) {
				sql.append(" AND SKILLS like '" + bean.getSkills() + "%'");
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			//String sqlquery="select job.id,jobtitle,name,jobtype,posteddate,skills,city,description from job inner join company on job.company_id=company.id";
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setJobTitle(rs.getString(2));
				bean.setCompanyName(rs.getString(3));
				bean.setJobType(rs.getString(4));
				bean.setJobPostDate(rs.getDate(5));
				bean.setSkills(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				/*bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));*/
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
	
	public void update(JobBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		/*
		 * StudentBean beanExist = findByLogin(bean.getLogin()); if (beanExist != null
		 * && !(beanExist.getId() == bean.getId())) { throw new
		 * DuplicateRecordException("LoginId is already exist"); }
		 */
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE JOB SET JOBTITLE=?,COMPANYNAME=?,JOBTYPE=?,POSTEDDATE=?,SKILLS=?,CITY=?,DESCRIPTION=?,"
						+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getJobTitle());
			pstmt.setString(2, bean.getCompanyName());
			pstmt.setString(3, bean.getJobType());
			pstmt.setDate(4, new java.sql.Date(bean.getJobPostDate().getTime()));
			pstmt.setString(5, bean.getSkills());
			pstmt.setString(6, bean.getCity());
			pstmt.setString(7, bean.getDescription());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select job.id,jobtitle,name,jobtype,posteddate,skills,city,description from job inner join company on job.company_id=company.id");
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
				System.out.println("rs.getSTring "+rs.getString(3));
				JobBean bean = new JobBean();
				bean.setId(rs.getLong(1));
				bean.setJobTitle(rs.getString(2));
				bean.setCompanyName(rs.getString(3));
				bean.setJobType(rs.getString(4));
				bean.setJobPostDate(rs.getDate(5));
				bean.setSkills(rs.getString(6));
				bean.setCity(rs.getString(7));
				bean.setDescription(rs.getString(8));
				/*bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));*/
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
