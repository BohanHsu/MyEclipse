package biz;

import java.util.List;

import org.hibernate.Query;

import dao.RoundDAO;
import entity.Round;

public class RoundBIZ {
	RoundDAO roundDAO = null;

	public RoundBIZ(RoundDAO roundDAO) {
		super();
		this.roundDAO = roundDAO;
	}
	
	public Round findRoundByRound(Integer roundNumber) throws Exception{
		List<Object> resultList = this.roundDAO.findByNumber(roundNumber);
		
		if(resultList.size() == 0){
			throw new Exception("round not found : " + roundNumber);
		}else{
			return (Round) resultList.get(0);
		}
	}

	
}
