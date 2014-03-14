package biz;

import java.util.ArrayList;
import java.util.List;

import dao.HaspositionDAO;
import entity.Club;
import entity.Hasposition;
import entity.Player;
import entity.Position;

public class HaspositionBIZ {
	HaspositionDAO haspositionDAO = null;

	public HaspositionBIZ(HaspositionDAO haspositionDAO) {
		super();
		this.haspositionDAO = haspositionDAO;
	}
	
	public void addHasposition(Player player, Position position){
		Hasposition newHasposition = new Hasposition(position, player);
		this.haspositionDAO.save(newHasposition);
	}
	
	public List<Position> findPositionByPlayer(Player player){
		List<Position> resultList = new ArrayList<Position>();
		List<Object> resultObjList = haspositionDAO.findByProperty("player", player);
		
		for (Object object : resultObjList) {
			Position position = ((Hasposition) object).getPosition();
			resultList.add(position);
		}
		
		return resultList;
	}

}
