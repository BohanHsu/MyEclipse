package biz;

import java.util.List;

import dao.NationDAO;
import entity.Nation;

public class NationBIZ {
	private NationDAO nationDAO = null;

	public NationBIZ(NationDAO nationDAO) {
		super();
		this.nationDAO = nationDAO;
	}
	
	public Nation findNationByName(String name) throws Exception{
//		Object resultObj = this.nationDAO.findByName(name).get(0);
//		if(resultObj == null){
//			throw new Exception("nation not found : " + name);
//		}else{
//			return (Nation) resultObj;
//		}
		
		List<Object> resultList = this.nationDAO.findByName(name);
		Object resultObj = null;
		if (resultList.size() == 0){
			throw new Exception("nation not found: ("+name+")");
		}else{
			resultObj = resultList.get(0);
			return (Nation) resultObj;
		}
	}
	
}
