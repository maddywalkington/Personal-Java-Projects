package finalproject;

import java.util.ArrayList;

public class RatingDistributionBySchool extends DataAnalyzer {

	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {

		MyHashTable<String, Double> profRatingSum = new MyHashTable<>();
		MyHashTable<String, Integer> profRatingCount = new MyHashTable<>();

		int nameIdx = parser.fields.get("professor_name");
		int schoolIdx = parser.fields.get("school_name");
		int qualityIdx = parser.fields.get("student_star");

		for (String[] entry : parser.data) {
			String profName = entry[nameIdx].trim().toLowerCase();
			String entrySchoolName = entry[schoolIdx].trim().toLowerCase();

			if (!entrySchoolName.equals(keyword.trim().toLowerCase())) {
				continue; // skip entries not from the specified school
			}

			if (profRatingSum.get(profName) == null){
				profRatingSum.put(profName, 0.0);
			}
			if (profRatingCount.get(profName) == null){
				profRatingCount.put(profName, 0);
			}

			double qualityRating = Double.parseDouble(entry[qualityIdx]);
			profRatingSum.put(profName, profRatingSum.get(profName) + qualityRating);
			profRatingCount.put(profName, profRatingCount.get(profName) + 1);
		}

		MyHashTable<String, Integer> result = new MyHashTable<>();
		for (MyPair<String, Double> entry : profRatingSum.getEntries()) {
			String profName = entry.getKey();
			double ratingSum = entry.getValue();
			int ratingCount = profRatingCount.get(profName);
			double averageRating = ratingSum / ratingCount;
			double roundedNum = Math.round(averageRating * 100.0) / 100.0;

			String profRating = (profName + "\n" + roundedNum);
			result.put(profRating, ratingCount);
		}

		return result;

	}

	@Override
	public void extractInformation() {
		for (String[] entry : parser.data) {
			// Extract relevant information from entry
			String school = entry[parser.fields.get("school_name")];
			String name = entry[parser.fields.get("professor_name")];
			String quality = entry[parser.fields.get("student_star")];
		}

		}
	}
