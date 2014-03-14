package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Playing;

/**
 * A data access object (DAO) providing persistence and search support for
 * Playing entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.Playing
 * @author MyEclipse Persistence Tools
 */

public class PlayingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PlayingDAO.class);
	// property constants
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String SCORE = "score";
	public static final String YELLOW_CARD = "yellowCard";
	public static final String RED_CARD = "redCard";
	public static final String RATE = "rate";

	public void save(Playing transientInstance) {
		log.debug("saving Playing instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Playing persistentInstance) {
		log.debug("deleting Playing instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Playing findById(java.lang.Integer id) {
		log.debug("getting Playing instance with id: " + id);
		try {
			Playing instance = (Playing) getSession().get("entity.Playing", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Playing instance) {
		log.debug("finding Playing instance by example");
		try {
			List results = getSession().createCriteria("entity.Playing")
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
		log.debug("finding Playing instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Playing as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStartTime(Object startTime) {
		return findByProperty(START_TIME, startTime);
	}

	public List findByEndTime(Object endTime) {
		return findByProperty(END_TIME, endTime);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findByYellowCard(Object yellowCard) {
		return findByProperty(YELLOW_CARD, yellowCard);
	}

	public List findByRedCard(Object redCard) {
		return findByProperty(RED_CARD, redCard);
	}

	public List findByRate(Object rate) {
		return findByProperty(RATE, rate);
	}

	public List findAll() {
		log.debug("finding all Playing instances");
		try {
			String queryString = "from Playing";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Playing merge(Playing detachedInstance) {
		log.debug("merging Playing instance");
		try {
			Playing result = (Playing) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Playing instance) {
		log.debug("attaching dirty Playing instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Playing instance) {
		log.debug("attaching clean Playing instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}