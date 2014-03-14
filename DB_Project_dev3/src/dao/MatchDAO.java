package dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Match;

/**
 * A data access object (DAO) providing persistence and search support for Match
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see entity.Match
 * @author MyEclipse Persistence Tools
 */

public class MatchDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(MatchDAO.class);
	// property constants
	public static final String STADIUM = "stadium";
	public static final String HOME_SCORE = "homeScore";
	public static final String VISIT_SCORE = "visitScore";

	public void save(Match transientInstance) {
		log.debug("saving Match instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Match persistentInstance) {
		log.debug("deleting Match instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Match findById(java.lang.Integer id) {
		log.debug("getting Match instance with id: " + id);
		try {
			Match instance = (Match) getSession().get("entity.Match", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Match instance) {
		log.debug("finding Match instance by example");
		try {
			List results = getSession().createCriteria("entity.Match")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Match instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Match as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStadium(Object stadium) {
		return findByProperty(STADIUM, stadium);
	}

	public List findByHomeScore(Object homeScore) {
		return findByProperty(HOME_SCORE, homeScore);
	}

	public List findByVisitScore(Object visitScore) {
		return findByProperty(VISIT_SCORE, visitScore);
	}

	public List findAll() {
		log.debug("finding all Match instances");
		try {
			String queryString = "from Match";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Match merge(Match detachedInstance) {
		log.debug("merging Match instance");
		try {
			Match result = (Match) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Match instance) {
		log.debug("attaching dirty Match instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Match instance) {
		log.debug("attaching clean Match instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}