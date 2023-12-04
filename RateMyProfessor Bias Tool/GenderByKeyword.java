package finalproject;

import java.util.ArrayList;
import java.util.LinkedList;

public class GenderByKeyword extends DataAnalyzer {
	
	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {

		MyHashTable<String, Integer> distTable = new MyHashTable<>();
		MyHashTable<String, Integer> genderCounts = new MyHashTable<>();

			for (String[] entry : parser.data) {

				String comment = entry[parser.fields.get("comments")];
				String gender = entry[parser.fields.get("gender")];
				if (gender != null && comment != null) {
					gender = gender.trim().toLowerCase();
					comment = comment.trim().toLowerCase().replaceAll("[^a-z']", " ");
					String[] words = comment.split("\\s+");

					if (genderCounts.get(gender) == null){
						genderCounts.put(gender, 0);
					}
					for (String w : words) {
						if (w.equals(keyword.trim().toLowerCase())) {
							genderCounts.put(gender, genderCounts.get(gender) + 1);
							break;
						}
					}
				}
			}

			distTable.put("M", genderCounts.get("m"));
			distTable.put("F", genderCounts.get("f"));
			distTable.put("X", genderCounts.get("x"));

			return distTable;
		}

	@Override
	public void extractInformation() {
		for (String[] entry : parser.data) {
			String gender = entry[parser.fields.get("gender")];
			String comment = entry[parser.fields.get("comments")];
		}
	}

}

