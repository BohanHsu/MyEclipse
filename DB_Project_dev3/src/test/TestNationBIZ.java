package test;

import dao.NationDAO;
import biz.NationBIZ;

public class TestNationBIZ {
	public static void main(String[] args) throws Exception {
		NationDAO nationDAO = new NationDAO();
		NationBIZ nationBIZ = new NationBIZ(nationDAO );
		Integer id = nationBIZ.findNationByName("Netherlands").getId();
		System.out.println(id);
		
	}
}
