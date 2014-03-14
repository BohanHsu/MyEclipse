package dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Nation;

/**
 * A data access object (DAO) providing persistence and search support for
 * Nation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.Nation
 * @author MyEclipse Persistence Tools
 */

public class NationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(NationDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String IMAGE_URL = "imageUrl";

	public void save(Nation transientInstance) {
		log.debug("saving Nation instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Nation persistentInstance) {
		log.debug("deleting Nation instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Nation findById(java.lang.Integer id) {
		log.debug("getting Nation instance with id: " + id);
		try {
			Nation instance = (Nation) getSession().get("entity.Nation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Nation instance) {
		log.debug("finding Nation instance by example");
		try {
			List results = getSession().createCriteria("entity.Nation")
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
		log.debug("finding Nation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Nation as model where model."
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
		log.debug("finding all Nation instances");
		try {
			String queryString = "from Nation";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Nation merge(Nation detachedInstance) {
		log.debug("merging Nation instance");
		try {
			Nation result = (Nation) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Nation instance) {
		log.debug("attaching dirty Nation instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Nation instance) {
		log.debug("attaching clean Nation instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}