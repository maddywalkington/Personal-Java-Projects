package finalproject;

import java.util.ArrayList;

public class RatingByKeyword extends DataAnalyzer {
	
    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {

		MyHashTable<String, Integer> result = new MyHashTable<>();

		result.put("1", 0);
		result.put("2", 0);
		result.put("3", 0);
		result.put("4", 0);
		result.put("5", 0);

			for (String[] row : parser.data) {
				String comment = row[parser.fields.get("comments")];
				if (comment != null && comment.trim().toLowerCase().contains(keyword.trim().toLowerCase())) {
					Double quality = Double.parseDouble(row[parser.fields.get("student_star")]);
					if (quality >= 1 && quality < 2) {
						result.put("1", result.get("1") + 1);
					} else if (quality >= 2 && quality < 3) {
						result.put("2", result.get("2") + 1);
					} else if (quality >= 3 && quality < 4) {
						result.put("3", result.get("3") + 1);
					} else if (quality >= 4 && quality < 5) {
						result.put("4", result.get("4") + 1);
					} else if (quality >= 5) {
						result.put("5", result.get("5") + 1);
					}
				}
			}
			return result;
		}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		for (String[] entry : parser.data) {
			String comment = entry[parser.fields.get("comments")];
			String quality = entry[parser.fields.get("student_star")];
		}

		//ADD YOUR CODE ABOVE THIS
	}
}

