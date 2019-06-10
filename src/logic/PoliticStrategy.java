package logic;

import java.util.Comparator;
import java.util.Random;

@FunctionalInterface
interface PoliticStrategy {
	int getIndex(Set set);

	static PoliticStrategy leastFrequentUsedAlgorithm() {
		return set -> set.getLines()
				.indexOf(set.getLines().stream().min(Comparator.comparing(Line::getAccesses)).get());
	}

	static PoliticStrategy leastRecentUsedAlgorithm() {
		return set -> set.getLines()
				.indexOf(set.getLines().stream().min(Comparator.comparing(Line::getLastAccess)).get());
	}

	static PoliticStrategy randomAlgorithm() {
		return set -> (new Random()).nextInt(set.getWays());
	}
}
