package biz;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.EnrollmentDAO;
import entity.Club;
import entity.Enrollment;
import entity.Player;
import entity.Season;

public class EnrollmentBIZ {
	private EnrollmentDAO enrollmentDAO = null;

	public EnrollmentBIZ(EnrollmentDAO enrollmentDAO) {
		super();
		this.enrollmentDAO = enrollmentDAO;
	}

	public List<Player> findPlayersByClub(Club club, Integer seasonStartYear) {
		List<Player> returnList = new ArrayList<Player>();

		List<Object> resultList = enrollmentDAO.findByProperty("club", club);

		for (Object object : resultList) {
			Enrollment e = (Enrollment) object;
			if (e.getSeason().getStartyear().equals(seasonStartYear)) {
				returnList.add(e.getPlayer());
			}
		}
		return returnList;
	}

	public void addEnrollment(Season season, Player player, Club club,
			Integer number, Long salary) {
		Enrollment newEnrollment = new Enrollment(season, player, club, number,
				salary);
		Session session = enrollmentDAO.getSession();
		Transaction tx = session.beginTransaction();
		this.enrollmentDAO.save(newEnrollment);
		tx.commit();
		session.flush();
		session.close();
	}
	
	public Club findClubByPlayerAndSeason(Player player, Integer seasonStartYear) throws Exception{
		Club club = null;
		
		String hql = "from Enrollment as e where e.player.id=? and e.season.startyear=?";
		
		Session session = this.enrollmentDAO.getSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, player.getId());
		query.setParameter(1, seasonStartYear);
		
		List<Enrollment> resultList = query.list();
		
		if(resultList.size() == 0){
			throw new Exception("Club not found for player "+player.getName()+" at season "+seasonStartYear);
		}
		club = resultList.get(0).getClub();
		return club;
	}
}
