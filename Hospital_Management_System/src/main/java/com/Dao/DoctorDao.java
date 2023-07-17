package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Doctor;

public class DoctorDao {
	private Connection conn;

	public DoctorDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean registerDoctor(Doctor doc) {
		boolean status = false;
		
		try {
			
			String sql = "insert into doctor_details(fullname,dob,qualification,specialist,email,mobile_no,password) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,doc.getFullName() );
			ps.setString(2,doc.getDob());
			ps.setString(3,doc.getQualification() );
			ps.setString(5,doc.getEmail() );
			ps.setString(4,doc.getSpecialist() );
			ps.setString(6,doc.getMobile_no() );
			ps.setString(7,doc.getPassword());
			
			int res = ps.executeUpdate();
			if(res==1) {
				status = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return status;
	}
	
	public List<Doctor> getAllDoctors(){
		List<Doctor> list = new ArrayList<Doctor>();
		Doctor doc = null;
		try {
			
			String sql = "select * from doctor_details order by id ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				doc = new Doctor();
				doc.setId(res.getInt(1));
				doc.setFullName(res.getString(2));
				doc.setDob(res.getString(3));
				doc.setQualification(res.getString(4));
				doc.setSpecialist(res.getString(5));
				doc.setEmail(res.getString(6));
				doc.setMobile_no(res.getString(7));
				doc.setPassword(res.getString(8));
				list.add(doc);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public Doctor getDoctorById(int id){
		
		Doctor doc = null;
		try {
			
			String sql = "select * from doctor_details where id=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				doc = new Doctor();
				doc.setId(res.getInt(1));
				doc.setFullName(res.getString(2));
				doc.setDob(res.getString(3));
				doc.setQualification(res.getString(4));
				doc.setSpecialist(res.getString(5));
				doc.setEmail(res.getString(6));
				doc.setMobile_no(res.getString(7));
				doc.setPassword(res.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return doc;
	}
	
	public boolean updateDoctor(Doctor doc) {
		boolean status = false;
		
		try {
			
			String sql = "update doctor_details set fullname=?,dob=?,qualification=?,specialist=?,email=?,mobile_no=?,password=? where id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,doc.getFullName() );
			ps.setString(2,doc.getDob());
			ps.setString(3,doc.getQualification() );
			ps.setString(5,doc.getEmail() );
			ps.setString(4,doc.getSpecialist() );
			ps.setString(6,doc.getMobile_no() );
			ps.setString(7,doc.getPassword());
			ps.setInt(8, doc.getId());
			int res = ps.executeUpdate();
			if(res==1) {
				status = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean deleteDoctor(int id) {
		boolean status = false;
		
		try {
			
			String sql = "delete from doctor_details where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if(res==1) {
				status = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
	public Doctor login(String email,String pwd) {
		Doctor doc = null;
		try {
			String sql = "select * from doctor_details where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				doc = new Doctor();
				doc.setId(res.getInt(1));	
				doc.setFullName(res.getString(2));
				doc.setDob(res.getString(3));
				doc.setQualification(res.getString(4));
				doc.setSpecialist(res.getString(5));
				doc.setEmail(res.getString(6));
				doc.setMobile_no(res.getString(7));
				doc.setPassword(res.getString(8));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return doc;
	}
	
	public int countDoctor() {
		int ct=0;
		try {
			
			String sql = "select * from doctor_details ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				ct++;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	public int countAppointment() {
		int ct=0;
		try {
			
			String sql = "select * from appointments ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				ct++;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	public int countUsers() {
		int ct=0;
		try {
			
			String sql = "select * from user_details ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				ct++;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	public int countSpecialist() {
		int ct=0;
		try {
			
			String sql = "select * from specialist_details ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				ct++;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	public int countAppointmentByDoctorId(int did) {
		int ct=0;
		try {
			
			String sql = "select * from appointments where doctor_id=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, did);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				ct++;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ct;
	}
	
	public boolean checkOldPassword(int userid,String oldPwd) {
		boolean status = false;
		try {
			
			String sql = "select * from doctor_details where id=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.setString(2, oldPwd);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				status =true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean changePassword(int userid,String newPwd) {
		boolean status = false;
		try {
			
			String sql = "update doctor_details set password = ? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(2, userid);
			ps.setString(1, newPwd);
			int res = ps.executeUpdate();
			if(res==1)	status = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean editDoctorProfile(Doctor doc) {
		boolean status = false;
		
		try {
			
			String sql = "update doctor_details set fullname=?,dob=?,qualification=?,specialist=?,email=?,mobile_no=? where id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,doc.getFullName() );
			ps.setString(2,doc.getDob());
			ps.setString(3,doc.getQualification() );
			ps.setString(5,doc.getEmail() );
			ps.setString(4,doc.getSpecialist() );
			ps.setString(6,doc.getMobile_no() );
			
			ps.setInt(7, doc.getId());
			int res = ps.executeUpdate();
			if(res==1) {
				status = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return status;
	}
	

}
