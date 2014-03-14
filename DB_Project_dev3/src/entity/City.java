package entity;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private Nation nation;
	private String name;
	private String imageUrl;
	private Set clubs = new HashSet(0);

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(Nation nation, String name, String imageUrl, Set clubs) {
		this.nation = nation;
		this.name = name;
		this.imageUrl = imageUrl;
		this.clubs = clubs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Nation getNation() {
		return this.nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
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

	public Set getClubs() {
		return this.clubs;
	}

	public void setClubs(Set clubs) {
		this.clubs = clubs;
	}
	
	public Map<String, Object> toMap(){
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("nation", this.nation);
		map.put("name", this.name);
		map.put("imageUrl", this.imageUrl);
		map.put("clubs", this.clubs);

		
		return map;
	}

}