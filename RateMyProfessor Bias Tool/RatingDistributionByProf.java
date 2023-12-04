package finalproject;

public class RatingDistributionByProf extends DataAnalyzer {

	public RatingDistributionByProf(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {

		MyHashTable<String, Integer> dist = new MyHashTable<>();
		dist.put("1", 0);
		dist.put("2", 0);
		dist.put("3", 0);
		dist.put("4", 0);
		dist.put("5", 0);

		for (String[] entry : parser.data) {
			String name = entry[parser.fields.get("professor_name")].trim().toLowerCase();
			if (name.equals(keyword.trim().toLowerCase())) {
				double quality = Double.parseDouble(entry[parser.fields.get("student_star")]);
				if (quality >= 1 && quality < 2) {
					dist.put("1", dist.get("1") + 1);
				} else if (quality >= 2 && quality < 3) {
					dist.put("2", dist.get("2") + 1);
				} else if (quality >= 3 && quality < 4) {
					dist.put("3", dist.get("3") + 1);
				} else if (quality >= 4 && quality < 5) {
					dist.put("4", dist.get("4") + 1);
				} else if (quality >= 5) {
					dist.put("5", dist.get("5") + 1);
				}
			}
		}

		return dist;
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
			for (String[] entry : parser.data) {
				// Extract relevant information from entry
				String name = entry[parser.fields.get("professor_name")];
				String quality = entry[parser.fields.get("student_star")];
			}
		//ADD YOUR CODE ABOVE THIS
	}

}

