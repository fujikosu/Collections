package com.kf.samples;

public class FactoryDAODemo {
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		
		FactoryDAODemo demo  = new FactoryDAODemo();
		//check if there is an argument for choosing data storage or not
		if(args.length == 0){
			System.out.println("You have to put Db or File as argument");
		}
		else{
			demo.testDao(args[0]);
		}
	}

	/**
	 * Make Model and View and pass them to new controller
	 * @param str Db (mysql) or File (textfile) (Choose database which you want to use )
	 */
	private void testDao(String str){
		/*example calling DB impl of personDAO*/
		DAO storage = DAOFactory.getDAO(str);
		View mainView = new View();
		new Controller(storage,mainView);
	}

//	for debugging
//	
//	private void testDBDao(){
//		/*example calling DB impl of personDAO*/
//		DAO storage = DAOFactory.getDAO("Db");
//		View mainView = new View();
//		new Controller(storage,mainView);
//	}
//	
//	private void testFileDao(){
//		/*example callling file impl of personDAO*/
//		DAO storage = DAOFactory.getDAO("File");
//		System.out.println("File");
//		View mainView = new View();
//		new Controller(storage,mainView);		
//	}
//	
}
