package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Nation entity. @author MyEclipse Persistence Tools
 */

public class Nation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String imageUrl;
	private Set players = new HashSet(0);
	private Set leagues = new HashSet(0);
	private Set cities = new HashSet(0);

	// Constructors

	/** default constructor */
	public Nation() {
	}

	/** full constructor */
	public Nation(String name, String imageUrl, Set players, Set leagues,
			Set cities) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.players = players;
		this.leagues = leagues;
		this.cities = cities;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set getPlayers() {
		return this.players;
	}

	public void setPlayers(Set players) {
		this.players = players;
	}

	public Set getLeagues() {
		return this.leagues;
	}

	public void setLeagues(Set leagues) {
		this.leagues = leagues;
	}

	public Set getCities() {
		return this.cities;
	}

	public void setCities(Set cities) {
		this.cities = cities;
	}

}