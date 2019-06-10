package view;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import logic.FileGenerator;
import logic.MemoryLevel;

public class Manager {
	private Simulator simulator;
	private SimulationResult simulationResult;

	public Manager() {
		simulator = new Simulator();
	}

	public boolean setCacheConfig(int cacheSize, int blockAmount, int wordSize, int ways) {
		return simulator.setCacheConfig(cacheSize, blockAmount, wordSize, ways);
	}

	public CacheDAO getCacheDAO() {
		return new CacheDAO(simulator.getCache());
	}

	public void addMemoryLevel(String id, int cost, int prob) {
		simulator.addMemoryLevel(id, cost, prob);
	}

	public List<MemoryLevelDAO> getMemoryLevelsDAO() {
		List<MemoryLevelDAO> list = new ArrayList<>();
		for (MemoryLevel mem : simulator.getMemoryLevels()) {
			list.add(new MemoryLevelDAO(mem));
		}

		return list;
	}

	public void setProgram(File progFile) {
		File addressFile = new File("enderecos_" + progFile.getName());
		FileGenerator.getInstance().createAddressFile(progFile, addressFile);
		simulator.loadAddress(addressFile);
	}

	public boolean simulatorSetup() {
		return simulator.setup();
	}

	public void startSimulation() {
		simulator.startSimulation();
		simulationResult = new SimulationResult(simulator);
	}

	public SimulationResult getSimulationResult() {
		return simulationResult;
	}

	public boolean loadCacheConfig(File configFile) {
		return simulator.setCacheConfig(configFile);
	}

	public void loadMemConfig(File configFile) {
		simulator.setMemoryHierarchy(configFile);
	}

	public void setAlgorithm(String algorithm) {
		switch (algorithm) {
		case "Randomico":
			simulator.setRandomAlgorithm();
			break;
		case "Least Frequent Used (LFU)":
			simulator.setLFUAlgorithm();
			break;
		case "Least Recent Used (LRU)":
			simulator.setLRUAlgorithm();
			break;
		}
	}

	public void loadAddresses(File file) {
		simulator.loadAddress(file);
	}

	public int getSetAmount() {
		return simulator.getCache().getAssociativeSetSize();
	}

	public int getSetWays() {
		return simulator.getCache().getWays();
	}
}
