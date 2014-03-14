package biz;

import java.util.List;

import org.hibernate.Query;

import dao.SeasonDAO;
import entity.Round;
import entity.Season;

public class SeasonBIZ {
	private SeasonDAO seasonDAO = null;

	public SeasonBIZ(SeasonDAO seasonDAO) {
		super();
		this.seasonDAO = seasonDAO;
	}

	public Season findSeasonByStartYear(Integer startYear) throws Exception {
		// Object resultObj = this.seasonDAO.findByStartyear(startYear).get(0);
		// if (resultObj == null){
		// throw new Exception("season not found: (startYear=" + startYear +
		// ")");
		// }else{
		// return (Season) resultObj;
		// }

		List<Object> resultList = this.seasonDAO.findByStartyear(startYear);
		Object resultObj = null;
		if (resultList.size() == 0) {
			throw new Exception("season not found: (startYear=" + startYear
					+ ")");
		} else {
			resultObj = resultList.get(0);
			return (Season) resultObj;
		}
	}

	public Integer findCurrentSeason() {

		String hql = "select max(startyear) from Season s";

		Query query = seasonDAO.getSession().createQuery(hql);

		return  (Integer) query.list().get(0);

	}
}
