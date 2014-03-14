package utility;

import java.util.Comparator;

import entity.derived.StandingRow;

public class StandingRowComparator implements Comparator<StandingRow>{

	public int compare(StandingRow o1, StandingRow o2) {

		if (o1.getPoints() != o2.getPoints()){
			return o1.getPoints() - o2.getPoints();
		}else{
			if(o1.getGoalsDifference() != o2.getGoalsDifference()){
				return o1.getGoalsDifference() - o2.getGoalsDifference();
			}else{
				if(o1.getGoalsFor() != o2.getGoalsFor()){
					return o1.getGoalsFor() - o2.getGoalsFor();
				}else{
					return 1;
				}
			}
		}
	}
}
