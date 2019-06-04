package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryHierarchy {
	Random rand;
	List<MemoryLevel> memoryLevels;
	private boolean setup;

	public MemoryHierarchy() {
		rand = new Random();
		memoryLevels = new LinkedList<>();
		setup = false;
	}

	public MemoryHierarchy(String fileName) {
		this();
		loadMemoryLevels(fileName);
	}

	public void loadMemoryLevels(String fileName) {
		String[] tokens;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			while (reader.ready()) {
				tokens = reader.readLine().split(":");

				memoryLevels.add(new MemoryLevel(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
			}

		} catch (IOException e) {
			System.out.println(e);
		}

		setup = true;
	}

	public int searchAddress() {
		int timeCost = 0;
		int randProb;
		for (MemoryLevel lvlMem : memoryLevels) {
			randProb = rand.nextInt(100);
			timeCost += lvlMem.getMissPenalty();

			if (lvlMem.hasAddress(randProb)) {
				break;
			}
		}

		return timeCost;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("Niveis de memoria:\n");
		for (MemoryLevel memLvl : memoryLevels) {
			string.append("   ").append(memLvl).append("\n");
		}

		return string.toString();
	}

	public void addMemoryLevel(String id, int cost, int prob) {
		memoryLevels.add(new MemoryLevel(id, cost, prob));

		setup = true;
	}

	public List<MemoryLevel> getMemorys() {
		return memoryLevels;
	}

	public boolean setup() {
		return setup;
	}

	public void resetValues() {
		for (MemoryLevel memLvl : memoryLevels) {
			memLvl.resetValues();
		}
	}
}
