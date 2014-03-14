package biz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.PlaysinDAO;
import entity.Club;
import entity.Playsin;

public class PlaysinBIZ {
	PlaysinDAO playsinDAO = null;
	
	
	
	public PlaysinBIZ(PlaysinDAO playsinDAO) {
		super();
		this.playsinDAO = playsinDAO;
	}



	public List<Club> findClubsByLeagueAndSeason(String leagueName, Integer seasonStartYear){
		List<Club> resultList = new ArrayList<Club>();
		
		String hql = "from Playsin as psi where psi.league.name =? and psi.season.startyear=?";
		
		Session session = this.playsinDAO.getSession();
//		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setString(0, leagueName);
		query.setInteger(1, seasonStartYear);
		
		List<Object> resultObjList = query.list();
		
		for (Object object : resultObjList) {
			Playsin psi = (Playsin) object;
			resultList.add(psi.getClub());
		}
		
		
		return resultList;
	}
}
