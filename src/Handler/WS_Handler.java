package Handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utility.DButils;

public class WS_Handler {
	public String getReceiptNumbers(String register_mob) {
		DButils DB = new DButils();
		Connection connect = DB.getConncetion();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String rel_mob1="",rel_mob2="",rel_mob3="";
		try {
			System.out.println("handler pwd---"+register_mob);
			stmt = connect
					.prepareStatement("select REL_MOB1,REL_MOB2,REL_MOB3 from ws_register where MOB_NUM=?");
			stmt.setString(1, register_mob);
			rs = stmt.executeQuery();
			if (rs.next()) {
				rel_mob1=rs.getString("REL_MOB1");
				rel_mob2=rs.getString("REL_MOB2");
				rel_mob3=rs.getString("REL_MOB3");
			} 
			rs.close();
			stmt.close();
			System.out.println("rel_mob1------"+rel_mob1+"----rel_mob2---"+rel_mob2+"---rel_mob3---"+rel_mob3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rel_mob1+","+rel_mob2+ "," + rel_mob3;

	}
	public String loginVerifyData(String mob, String pwd) {
		
	DButils DB = new DButils();
	Connection connect = DB.getConncetion();

	PreparedStatement stmt = null;
	ResultSet rs = null;
	String flag = "",DB_mob="",DB_pwd="";
	try {
		System.out.println("handler mob---"+mob);
		System.out.println("handler pwd---"+pwd);
		stmt = connect
				.prepareStatement("select MOB_NUM,PASSWD from ws_register where MOB_NUM=?");
		stmt.setString(1, mob);
		rs = stmt.executeQuery();
		if (rs.next()) {
			DB_mob=rs.getString("MOB_NUM");
			DB_pwd=rs.getString("PASSWD");
		} else {
			flag = "new";
		}
		rs.close();
		stmt.close();
		System.out.println("DB_mob---"+DB_mob);
		System.out.println("DB_pwd---"+DB_pwd);
		if(DB_mob!=""&&DB_pwd!=""){
		if(DB_mob.equals(mob)&&DB_pwd.equals(pwd))
		{
			flag = "registered_user";
		}} else {
			flag = "new";
		}
		System.out.println("flag---"+flag);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	return flag;
}

	
	public String saveData(String name, String mob, String pass, String num1,
			String num2, String num3) {
		DButils DB = new DButils();
		Connection connect = DB.getConncetion();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String flag = "";
		try {
			stmt = connect
					.prepareStatement("select MOB_NUM from ws_register where MOB_NUM=?");
			stmt.setString(1, mob);
			rs = stmt.executeQuery();
			if (rs.next()) {
				flag = "Existing";
			} else {
				flag = "new";
			}
			rs.close();
			stmt.close();
			System.out.println("*****name---"+name);
			System.out.println("mob---"+mob);
			if (flag.equals("new")) {
				System.out.println("flag---"+flag);
				stmt = connect
						.prepareStatement("insert into ws_register(MOB_NUM,NAME_ID,PASSWD,REL_MOB1,REL_MOB2,REL_MOB3) "
								+ "values(?,?,?,?,?,?)");

				// stmt.setInt(1,101);//1 specifies the first parameter in the
				// query
				stmt.setString(1, mob);
				stmt.setString(2, name);
				stmt.setString(3, pass);
				stmt.setString(4, num1);
				stmt.setString(5, num2);
				stmt.setString(6, num3);

				int i = stmt.executeUpdate();
				stmt.close();
				System.out.println(i + " records inserted");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}
}
