package biz;

import java.util.List;

import dao.CityDAO;
import entity.City;

public class CityBIZ {
	private CityDAO cityDAO = null;

	public CityBIZ(CityDAO cityDAO) {
		super();
		this.cityDAO = cityDAO;
	}

	public City findCityByName(String cityName) throws Exception {

		List<Object> resultList = cityDAO.findByName(cityName);
		Object resultObj = null;
		if(resultList.size() == 0){
			throw new Exception("city not found : " + cityName);
		}else{			
			resultObj = resultList.get(0);
		}
		return (City) resultObj;
		
	}
}
