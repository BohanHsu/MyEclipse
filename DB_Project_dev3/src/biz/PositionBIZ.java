package biz;

import java.util.List;

import dao.PositionDAO;
import entity.Position;

public class PositionBIZ {
	private PositionDAO positionDAO = null;

	public PositionBIZ(PositionDAO positionDAO) {
		super();
		this.positionDAO = positionDAO;
	}
	
	public Position findPositionByName(String name) throws Exception{
//		Object resultObj = this.positionDAO.findByName(name).get(0);
//		if(resultObj == null){
//			throw new Exception("position not found");
//		}else{
//			return (Position) resultObj;
//		}
		
		List<Object> resultList = this.positionDAO.findByName(name);
		Object resultObj = null;
		
		if (resultList.size() == 0){
			throw new Exception("position not found : ("+name+")");
		}else{
			resultObj = resultList.get(0);
			return (Position) resultObj;
		}
	}
}
