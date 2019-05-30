import java.util.Comparator;
import java.util.Random;

@FunctionalInterface
interface AlgorithmStrategy {
	int getIndex(Set set);
	
	static AlgorithmStrategy leastFrequentUsedAlgortithm()
	{
		return set -> set.getList()
				.stream()
				.min(Comparator.comparing(Line::getAccesses))
				.get()
				.getAccesses();
	}
	
	static AlgorithmStrategy randomAlgorithm()
	{
		return set -> (new Random()).nextInt(set.getList().size());
	}
}
