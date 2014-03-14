package test;

import dao.CityDAO;
import entity.City;
import biz.CityBIZ;

public class TestCityBiz {
	public static void main(String[] args) {
		CityDAO cityDAO = new CityDAO();
		CityBIZ cityBIZ = new CityBIZ(cityDAO);
		City city = null;
		try {
			city = cityBIZ.findCityByName("Manchesterr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (city != null) {
			System.out.println(city.getNation().getName());
		}
	}
}
