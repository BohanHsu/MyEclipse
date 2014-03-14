package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Enrollment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Enrollment entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see entity.Enrollment
 * @author MyEclipse Persistence Tools
 */

public class EnrollmentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EnrollmentDAO.class);
	// property constants
	public static final String NUMBER = "number";
	public static final String SALARY = "salary";

	public void save(Enrollment transientInstance) {
		log.debug("saving Enrollment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Enrollment persistentInstance) {
		log.debug("deleting Enrollment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Enrollment findById(java.lang.Integer id) {
		log.debug("getting Enrollment instance with id: " + id);
		try {
			Enrollment instance = (Enrollment) getSession().get(
					"entity.Enrollment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Enrollment instance) {
		log.debug("finding Enrollment instance by example");
		try {
			List results = getSession().createCriteria("entity.Enrollment")
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
		log.debug("finding Enrollment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Enrollment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List findBySalary(Object salary) {
		return findByProperty(SALARY, salary);
	}

	public List findAll() {
		log.debug("finding all Enrollment instances");
		try {
			String queryString = "from Enrollment";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Enrollment merge(Enrollment detachedInstance) {
		log.debug("merging Enrollment instance");
		try {
			Enrollment result = (Enrollment) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Enrollment instance) {
		log.debug("attaching dirty Enrollment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Enrollment instance) {
		log.debug("attaching clean Enrollment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}