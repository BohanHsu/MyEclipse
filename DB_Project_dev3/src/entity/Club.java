package entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Club entity. @author MyEclipse Persistence Tools
 */

public class Club implements java.io.Serializable {

	// Fields

	private Integer id;
	private City city;
	private String name;
	private String nickname;
	private Date start;
	private String home;
	private String owner;
	private String coach;
	private String imageUrl;
	private Set enrollments = new HashSet(0);
	private Set playsins = new HashSet(0);
	private Set matchsForHomeTeamId = new HashSet(0);
	private Set matchsForVisitTeamId = new HashSet(0);

	// Constructors

	/** default constructor */
	public Club() {
	}

	/** full constructor */
	public Club(City city, String name, String nickname, Date start,
			String home, String owner, String coach, String imageUrl,
			Set enrollments, Set playsins, Set matchsForHomeTeamId,
			Set matchsForVisitTeamId) {
		this.city = city;
		this.name = name;
		this.nickname = nickname;
		this.start = start;
		this.home = home;
		this.owner = owner;
		this.coach = coach;
		this.imageUrl = imageUrl;
		this.enrollments = enrollments;
		this.playsins = playsins;
		this.matchsForHomeTeamId = matchsForHomeTeamId;
		this.matchsForVisitTeamId = matchsForVisitTeamId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getStart() {
		return this.start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCoach() {
		return this.coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(Set enrollments) {
		this.enrollments = enrollments;
	}

	public Set getPlaysins() {
		return this.playsins;
	}

	public void setPlaysins(Set playsins) {
		this.playsins = playsins;
	}

	public Set getMatchsForHomeTeamId() {
		return this.matchsForHomeTeamId;
	}

	public void setMatchsForHomeTeamId(Set matchsForHomeTeamId) {
		this.matchsForHomeTeamId = matchsForHomeTeamId;
	}

	public Set getMatchsForVisitTeamId() {
		return this.matchsForVisitTeamId;
	}

	public void setMatchsForVisitTeamId(Set matchsForVisitTeamId) {
		this.matchsForVisitTeamId = matchsForVisitTeamId;
	}

	public Map<String, Object> toMap(){
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("id", this.id);
		map.put("name", this.name);
		map.put("nickname", this.nickname);
		map.put("city", this.city.getName());
		map.put("start", this.start.toString());
		map.put("home", this.home);
		map.put("owner", this.owner);
		map.put("coach", this.coach);
		map.put("imageUrl", this.imageUrl);
		
		return map;
	}
	
}