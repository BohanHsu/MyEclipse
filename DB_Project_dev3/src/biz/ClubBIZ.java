package biz;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CityDAO;
import dao.ClubDAO;
import entity.City;
import entity.Club;

public class ClubBIZ {
	private ClubDAO clubDAO = null;
	private CityDAO cityDAO = null;

	public ClubBIZ(ClubDAO clubDAO, CityDAO cityDAO) {
		super();
		this.clubDAO = clubDAO;
		this.cityDAO = cityDAO;
	}

	public Club findClubByName(String clubName) throws Exception {
		@SuppressWarnings("unchecked")
		List<Object> resultList = this.clubDAO.findByName(clubName);
		Object clubObj = null;
		if (resultList.size() == 0) {
			throw new Exception("club not found : " + clubName);
		} else {
			clubObj = resultList.get(0);
		}
		return (Club) clubObj;
	}

	public Club findClubById(Integer Id) throws Exception {
		Club club = clubDAO.findById(Id);
		if (club == null) {
			throw new Exception("club not found : (Id=" + Id + ")");
		}
		return club;
	}
	
//	public List<Club> findClubsByLeague(){
//		this.clubDAO.findByProperty(propertyName, value)
//	}

	public Club addClub(String cityName, String name, String nickname,
			Date start, String home, String owner, String coach)
			throws Exception {

		City city = new CityBIZ(this.cityDAO).findCityByName(cityName);

		Club newClub = new Club(city, name, nickname, start, home, owner,
				coach, null, null, null, null,null);
		
		Club oldClub = null;
		
		try {
			oldClub = findClubByName(name);			
		} catch (Exception e) {
	
		}
		
		if (oldClub != null){
			throw new Exception("club already exist : ("+name+")");
		}
		
		Session session = this.clubDAO.getSession();
		Transaction tx = session.beginTransaction();
		this.clubDAO.save(newClub);
		tx.commit();
		session.flush();
		session.close();
		return findClubByName(name);

	}
	
	public List<Club> getAllClubs(){
		return this.clubDAO.findAll();
	}

}
