package com.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Specialist;
public class SpecialistDao {
	private Connection conn;

	public SpecialistDao(Connection conn) {
		super();
		this.conn = conn;
	}
	public boolean addSpecialist(String specName) {
		boolean status = false;
		try {
			
			String sql = "insert into specialist_details(specName) values(?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, specName);
			int res = ps.executeUpdate();
			if(res == 1) {
				status = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public List<Specialist> getAllSpecialist(){
		List<Specialist> list = new ArrayList<Specialist>();
		Specialist sp = null;
		try {
			
			String sql = "select * from specialist_details ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				sp = new Specialist();
				sp.setId(res.getInt(1));
				sp.setSpecialistName(res.getString(2));
				list.add(sp);				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
