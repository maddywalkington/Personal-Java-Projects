package assignment3;

import java.awt.Color;

public class PerimeterGoal extends Goal{

	public PerimeterGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {

		Color[][] colors = board.flatten();
		
		int score = 0;
		
		for (int i=0; i<colors.length; i++){
			
			if (colors[i][0] == targetGoal){
				score++;
			}
			if (colors[i][colors.length-1] == targetGoal){
				score++;
			}
			if (colors[0][i] == targetGoal){
				score++;
			}
			if (colors[colors.length-1][i] == targetGoal){
				score++;
			}
			
		}
		return score;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal) 
		+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}
