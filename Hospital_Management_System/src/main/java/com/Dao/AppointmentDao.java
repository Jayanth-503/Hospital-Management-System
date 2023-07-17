package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Appointment;

public class AppointmentDao {
	private Connection conn ;

	public AppointmentDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addAppointment(Appointment app) {
		boolean status = false;
		try {
			
			String sql = "insert into appointments(user_id,fullname,gender,age,appointment_date,email,mobile_no,diseases,doctor_id,address,status) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, app.getUserId());
			ps.setString(2, app.getFullName());
			ps.setString(3, app.getGender());
			ps.setString(4, app.getAge());
			ps.setString(5, app.getAppointmentDate());
			ps.setString(6, app.getEmail());
			ps.setString(7, app.getMobileNo());
			
			ps.setString(8, app.getDiseases());
			ps.setInt(9, app.getDoctorId());
			ps.setString(10, app.getAddress());
			
			ps.setString(11, app.getStatus());
			int res = ps.executeUpdate();
			if(res==1)	status = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}
	
	public List<Appointment> getAllAppointmentByLoginUser(int userId){
		List<Appointment> list = new ArrayList<Appointment>();
		Appointment app = null;
		try {
			String sql = "select * from appointments where user_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				app = new Appointment();
				app.setId(res.getInt(1));
				app.setUserId(res.getInt(2));
				app.setFullName(res.getString(3));
				app.setGender(res.getString(4));
				app.setAge(res.getString(5));
				app.setAppointmentDate(res.getString(6));
				app.setEmail(res.getString(7));
				app.setMobileNo(res.getString(8));
				app.setDiseases(res.getString(9));
				app.setDoctorId(res.getInt(10));
				app.setAddress(res.getString(11));
				app.setStatus(res.getString(12));
				list.add(app);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Appointment> getAllAppointmentByDoctorLogin(int doctorId){
		List<Appointment> list = new ArrayList<Appointment>();
		Appointment app = null;
		try {
			String sql = "select * from appointments where doctor_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				app = new Appointment();
				app.setId(res.getInt(1));
				app.setUserId(res.getInt(2));
				app.setFullName(res.getString(3));
				app.setGender(res.getString(4));
				app.setAge(res.getString(5));
				app.setAppointmentDate(res.getString(6));
				app.setEmail(res.getString(7));
				app.setMobileNo(res.getString(8));
				app.setDiseases(res.getString(9));
				app.setDoctorId(res.getInt(10));
				app.setAddress(res.getString(11));
				app.setStatus(res.getString(12));
				list.add(app);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public Appointment getAppointmentById(int id) {
		Appointment app = null;
		try {
			String sql = "select * from appointments where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				app = new Appointment();
				app.setId(res.getInt(1));
				app.setUserId(res.getInt(2));
				app.setFullName(res.getString(3));
				app.setGender(res.getString(4));
				app.setAge(res.getString(5));
				app.setAppointmentDate(res.getString(6));
				app.setEmail(res.getString(7));
				app.setMobileNo(res.getString(8));
				app.setDiseases(res.getString(9));
				app.setDoctorId(res.getInt(10));
				app.setAddress(res.getString(11));
				app.setStatus(res.getString(12));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return app;
	}
	
	public boolean updateCommentStatus(int id,int doctorId,String comm) {
		boolean status = false;
		try {
			String sql = "update appointments set status=? where id=? and doctor_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,comm);
			ps.setInt(2, id);
			ps.setInt(3,doctorId);
			int res = ps.executeUpdate();
			if(res==1)	status = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	public List<Appointment> getAllAppointment(){
		List<Appointment> list = new ArrayList<Appointment>();
		Appointment app = null;
		try {
			String sql = "select * from appointments order by id ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				app = new Appointment();
				app.setId(res.getInt(1));
				app.setUserId(res.getInt(2));
				app.setFullName(res.getString(3));
				app.setGender(res.getString(4));
				app.setAge(res.getString(5));
				app.setAppointmentDate(res.getString(6));
				app.setEmail(res.getString(7));
				app.setMobileNo(res.getString(8));
				app.setDiseases(res.getString(9));
				app.setDoctorId(res.getInt(10));
				app.setAddress(res.getString(11));
				app.setStatus(res.getString(12));
				list.add(app);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
}
