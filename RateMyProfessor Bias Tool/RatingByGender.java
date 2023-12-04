package finalproject;

import java.util.ArrayList;

public class RatingByGender extends DataAnalyzer{

	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Integer> distribution = new MyHashTable<>();
			String[] parts = keyword.split(",");
			String gender_input = parts[0].toUpperCase();
			String type = parts[1].trim().toLowerCase();

		int genderIndex =  parser.fields.get("gender");

			for (String[] entry : parser.data) {

				String gender = entry[genderIndex];

				if (gender.equals(gender_input)){

					if (type.equals("quality")){
						int ratingIndex = parser.fields.get("student_star");
						double rating = Double.parseDouble(entry[ratingIndex]);
						String category = getRatingCategory(rating);

						if (distribution.get(category) == null){
							distribution.put(category, 0);}

						distribution.put(category, distribution.get(category) + 1);
					}
					if (type.equals("difficulty")) {
						int ratingIndex = parser.fields.get("student_difficult");
						double rating = Double.parseDouble(entry[ratingIndex]);
						String category = getRatingCategory(rating);

						if (distribution.get(category) == null){
							distribution.put(category, 0);}

						distribution.put(category, distribution.get(category) + 1);
					}

				}

				}
			return distribution;
		}

		private String getRatingCategory(double rating) {
			if (rating >= 1 && rating < 2) {
				return "1";
			} else if (rating >= 2 && rating < 3) {
				return "2";
			} else if (rating >= 3 && rating < 4) {
				return "3";
			} else if (rating >= 4 && rating < 5) {
				return "4";
			} else {
				return "5";
			}
		}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		for (String[] entry : parser.data) {
			String difficulty = entry[parser.fields.get("student_difficult")];
			String quality = entry[parser.fields.get("student_star")];
			String gender = entry[parser.fields.get("gender")];
		}
		//ADD YOUR CODE ABOVE THIS
	}
}

