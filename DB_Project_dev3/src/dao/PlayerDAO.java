package dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Player;

/**
 * A data access object (DAO) providing persistence and search support for
 * Player entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.Player
 * @author MyEclipse Persistence Tools
 */

public class PlayerDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PlayerDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String IMAGE_URL = "imageUrl";

	public void save(Player transientInstance) {
		log.debug("saving Player instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Player persistentInstance) {
		log.debug("deleting Player instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Player findById(java.lang.Integer id) {
		log.debug("getting Player instance with id: " + id);
		try {
			Player instance = (Player) getSession().get("entity.Player", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Player instance) {
		log.debug("finding Player instance by example");
		try {
			List results = getSession().createCriteria("entity.Player")
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
		log.debug("finding Player instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Player as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByImageUrl(Object imageUrl) {
		return findByProperty(IMAGE_URL, imageUrl);
	}

	public List findAll() {
		log.debug("finding all Player instances");
		try {
			String queryString = "from Player";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Player merge(Player detachedInstance) {
		log.debug("merging Player instance");
		try {
			Player result = (Player) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Player instance) {
		log.debug("attaching dirty Player instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Player instance) {
		log.debug("attaching clean Player instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}