import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

@FunctionalInterface
interface PoliticStrategy {
	String getIndex(Set set);
	
	static PoliticStrategy leastFrequentUsedAlgortithm()
	{
		return set -> set.getLines()
				.stream()
				.min(Comparator.comparing(Line::getAccesses))
				.get()
				.getTag();
	}
	
	static PoliticStrategy randomAlgorithm()
	{
		return set -> {
			List<String> tags = set.getTags();
			Collections.shuffle(tags);
			return tags.get(0);
		};
	}
}
