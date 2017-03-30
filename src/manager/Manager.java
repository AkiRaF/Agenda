package manager;

import Dao.Dao;

public class Manager extends Dao{
	public Manager(String nomtable) {
		super(nomtable);
	}
	
	public void Finish(){
		super.Close();
	}
}
