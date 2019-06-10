package view;

import logic.MemoryLevel;

public class MemoryLevelDAO {
	private String id;
	private int missPenalty;
	private int prob;
	private int hits;
	private int misses;

	public MemoryLevelDAO(MemoryLevel memLevel) {
		id = memLevel.getId();
		missPenalty = memLevel.getMissPenalty();
		prob = memLevel.getProb();
		hits = memLevel.getHits();
		misses = memLevel.getMisses();
	}

	public String getId() {
		return id;
	}

	public int getMissPenalt() {
		return missPenalty;
	}

	public int getProb() {
		return prob;
	}

	public int getHits() {
		return hits;
	}

	public int getMisses() {
		return misses;
	}

	public float getHitRate() {
		return (((float) hits) / ((float) hits + misses)) * 100.f;
	}

	public float getMissRate() {
		return (((float) misses) / ((float) hits + misses)) * 100.f;
	}

	@Override
	public String toString() {
		if (misses != 0 | hits != 0) {
			float querys = hits + misses;
			float hitRate = (hits / querys) * 100.0f;
			float missRate = (misses / querys) * 100.0f;
			return id + " = [hit=" + hits + ", hit-rate=" + String.format("%.2f", hitRate) + "%, misses=" + misses
					+ ", miss-rate=" + String.format("%.2f", missRate) + "%, missPenalty=" + missPenalty + ", prob="
					+ prob + "]";
		}

		return id + " = [hit=" + hits + ", misses=" + misses + ", missPenalty=" + missPenalty + ", prob=" + prob + "]";
	}
}
