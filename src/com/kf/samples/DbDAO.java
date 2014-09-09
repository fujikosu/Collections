package com.kf.samples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class for Database (Mysql)
 * @author TEST
 *
 */
public class DbDAO implements DAO{

	private String driver;
	private String url;
	private String user;
	private String password;
	
	public DbDAO(){
		init();
	}
	
	/**
	 * set the connection data from properties
	 */
	private void init(){
		GetConnecticonPropertyValues prop = new GetConnecticonPropertyValues();
		String[] propDatas = prop.getPropValues();
		driver = propDatas[0];
		url = propDatas[1];
		user = propDatas[2];
		password = propDatas[3];
	}
	
	/**
	 * Access to mySql and read, write data
	 */
	Connection con = null;
	PreparedStatement ps = null;
	
	/**
	 * 
	 * @param sql
	 * @param judge
	 * @param person
	 * @return
	 */
	public ArrayList<Student> judgeProcess(String sql,int judge,Student person){
		ArrayList<Student> personList = new ArrayList<Student>();
		try {
			con = connect(con);
			ps = con.prepareStatement(sql);
			switch (judge) {
			//Insert
			case 0:
				ps.setString(1, person.getName());
				ps.setInt(2, person.getAge());
				ps.executeUpdate();
				//want to show a window
				System.out.println("Written");
				break;
			//Select
			case 1:
				ResultSet rs = ps.executeQuery(sql);
				while (rs.next()) {
					Student readPerson = new Student();
				    readPerson.setName(rs.getString("name"));   
				    readPerson.setAge(Integer.parseInt(rs.getString("age")));   
				    personList.add(readPerson);   
				    
				} 
				break;
			//Delete
			case 2:
				ps.executeUpdate();
				System.out.println("Deleted");
				break;
			case 4:
				ps.executeUpdate();
				System.out.println("Update");
			default:
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personList;
	}
	
	/**
	 * connect to mysql
	 * This is always called when you read and write
	 */
	private Connection connect(Connection con) throws ClassNotFoundException,
			SQLException {

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		return con;
	}

	/**
	 * Write data into person_table in mySql
	 */
	@Override
	public void write(Student person) {

		judgeProcess("insert into person.person_table(name,age) values(?,?);", 0, person);
	}
	
	/**
	 * Read all data of person_tabla from mySql
	 */
	@Override
	public ArrayList<Student> read() {

		//not new OK?
		ArrayList<Student> personList = judgeProcess("select name,age from person_table", 1, null);
		return personList;
	}

	/**
	 * Delete all data of person_table in mySql
	 */
	@Override
	public void delete() {

		judgeProcess("DELETE FROM person_table;", 2, null);
		
	}

	@Override
	public void search(Student student) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * update one student data to another one which user input
	 */
	//TODO change list view when update is done         AND is used 
	@Override
	public void update(Student before, Student after) {
		System.out.println("UPDATE person_table set name='" + after.getName() + "', age='" + after.getAge() + "' where name='" + before.getName() + "'AND age='" + before.getAge() + "';");
		judgeProcess("UPDATE person_table set name='" + after.getName() + "', age='" + after.getAge() + "' where name='" + before.getName() + "'AND age='" + before.getAge() + "';", 4, null);
	}
}
