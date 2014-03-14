package test;

import java.util.List;

import dao.LeagueDAO;
import dao.NationDAO;
import entity.League;
import biz.LeagueBIZ;

public class TestFindAllLeague {
	public static void main(String[] args) {
		LeagueDAO leagueDAO = new LeagueDAO();
		NationDAO nationDAO = new NationDAO();
		LeagueBIZ leagueBIZ = new LeagueBIZ(leagueDAO, nationDAO);		
		
		List<League> resultList = leagueBIZ.findAllLeagues();
		
		for (League league : resultList) {
			System.out.println(league.getName());
		}
	}
}	
