package com.kf.samples;

/**
 * Make and return storage, which is Db or File as DAO variable
 * @author TEST
 *
 */
public class DAOFactory {
	public static DAO getDAO(String DAOType){
		
		if(DAOType == null){
			System.out.println("You have to put Db or File as argument");
		}
		if(DAOType.equals("Db")){
			System.out.println("Db");
			return new DbDAO();
		}
		else if(DAOType.equals("File")){
			System.out.println("File");
			return new FileIODAO();
		}
		return null;
	}
}
