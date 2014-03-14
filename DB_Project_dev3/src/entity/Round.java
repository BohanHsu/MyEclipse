package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Round entity. @author MyEclipse Persistence Tools
 */

public class Round implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer number;
	private Set matchs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Round() {
	}

	/** full constructor */
	public Round(Integer number, Set matchs) {
		this.number = number;
		this.matchs = matchs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Set getMatchs() {
		return this.matchs;
	}

	public void setMatchs(Set matchs) {
		this.matchs = matchs;
	}

}