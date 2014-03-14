package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Season entity. @author MyEclipse Persistence Tools
 */

public class Season implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer startyear;
	private Set enrollments = new HashSet(0);
	private Set matchs = new HashSet(0);
	private Set playsins = new HashSet(0);

	// Constructors

	/** default constructor */
	public Season() {
	}

	/** full constructor */
	public Season(Integer startyear, Set enrollments, Set matchs, Set playsins) {
		this.startyear = startyear;
		this.enrollments = enrollments;
		this.matchs = matchs;
		this.playsins = playsins;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStartyear() {
		return this.startyear;
	}

	public void setStartyear(Integer startyear) {
		this.startyear = startyear;
	}

	public Set getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(Set enrollments) {
		this.enrollments = enrollments;
	}

	public Set getMatchs() {
		return this.matchs;
	}

	public void setMatchs(Set matchs) {
		this.matchs = matchs;
	}

	public Set getPlaysins() {
		return this.playsins;
	}

	public void setPlaysins(Set playsins) {
		this.playsins = playsins;
	}

}