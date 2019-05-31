import java.util.Comparator;
import java.util.Random;

@FunctionalInterface
interface PoliticStrategy {
	int getIndex(Set set);
	
	static PoliticStrategy leastFrequentUsedAlgortithm()
	{
		return set -> set.getLines()
				.stream()
				.min(Comparator.comparing(Line::getAccesses))
				.get()
				.getAccesses();
	}
	
	static PoliticStrategy randomAlgorithm()
	{
		return set -> (new Random()).nextInt(set.getLines().size());
	}
}
