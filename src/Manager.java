import java.util.ArrayList;
import java.util.List;

import logic.FileGenerator;
import logic.MemoryLevel;

public class Manager {
	private Simulator simulator;
	private SimulationResult simulationResult;

	public Manager() {
		simulator = new Simulator();
	}

	public void setCacheConfig(int cacheSize, int blockAmount, int wordSize, int ways) {
		simulator.setCacheConfig(cacheSize, blockAmount, wordSize, ways);
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

	public void setProgram(String progName) {
		FileGenerator.getInstance().createAddressFile(progName, "enderecos_" + progName);
		simulator.loadAddress("enderecos_" + progName);
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

	public void loadCacheConfig(String fileConfigName) {
		simulator.setCacheConfig(fileConfigName);
	}

	public void loadMemConfig(String fileConfigName) {
		simulator.setMemoryHierarchy(fileConfigName);
	}

	public void setAlgorithm(String algorithm) {
		switch (algorithm) {
		case "Rand�mico":
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

	public void loadAddresses(String fileName) {
		simulator.loadAddress(fileName);
	}

	public int getSetAmount() {
		return simulator.getCache().getAssociativeSetSize();
	}

	public int getSetWays() {
		return simulator.getCache().getWays();
	}
}
