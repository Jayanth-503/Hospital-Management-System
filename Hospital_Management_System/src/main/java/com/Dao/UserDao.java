package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDao {
	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean register(User u) {
		boolean status = false;
		try {
			String sql = "insert into user_details(full_name,email,password) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,u.getFullName());
			ps.setString(2,u.getEmail());
			ps.setString(3,u.getPassword());
			
			int res= ps.executeUpdate();
			if(res==1)	status = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public User login(String em,String pwd) {
		User user = null;
		try {
			String sql = "select * from user_details where email=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, em);
			ps.setString(2, pwd);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				user = new User();
				user.setId(res.getInt(1));
				user.setFullName(res.getString(2));
				user.setEmail(res.getString(3));
				user.setPassword(res.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean checkOldPassword(int userid,String oldPwd) {
		boolean status = false;
		try {
			
			String sql = "select * from user_details where id=? and password=?";
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
			
			String sql = "update user_details set password = ? where id=?";
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
	
	
}
