package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal {

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
		public int score(Block board) {
		Color[][] colors = board.flatten();
		int size = colors.length;

			int maxBlobSize = 0;
			boolean[][] visited = new boolean[size][size];

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (colors[i][j] == targetGoal && !visited[i][j]) {
						int blobSize = undiscoveredBlobSize(i, j, colors, visited);
						if (blobSize > maxBlobSize) {
							maxBlobSize = blobSize;
						}
					}
				}
			}

			return maxBlobSize;
		}
	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal)
				+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		Color targetColor = unitCells[i][j];
		if (visited[i][j] || !targetColor.equals(targetGoal)) {
			return 0;
		}
		visited[i][j] = true;
		int size = 1;
		if (i > 0) { // check top neighbour
			size += undiscoveredBlobSize(i - 1, j, unitCells, visited);
		}
		if (i < unitCells.length - 1) { // check bottom neighbour
			size += undiscoveredBlobSize(i + 1, j, unitCells, visited);
		}
		if (j > 0) { // check left neighbour
			size += undiscoveredBlobSize(i, j - 1, unitCells, visited);
		}
		if (j < unitCells[0].length - 1) { // check right neighbour
			size += undiscoveredBlobSize(i, j + 1, unitCells, visited);
		}
		return size;
	}
}


