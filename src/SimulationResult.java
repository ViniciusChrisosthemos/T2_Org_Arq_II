import Logic.Cache;
import Logic.Simulator;

public class SimulationResult {
	private float totalTime;
	private float timeAverage;
	private int cacheHits;
	private float cacheHitRate;
	private int cacheMiss;
	private float cacheMissRate;
	private int totalAddressesProcessed;
	
	public SimulationResult(Simulator simulator)
	{
		Cache cache = simulator.getCache();
		totalTime = simulator.getTotalTime();
		timeAverage = simulator.getTimeAverage();
		cacheHits = cache.getHits();
		cacheMiss = cache.getMiss();
		cacheHitRate = ((float)cacheHits) / ((float) cacheMiss + cacheHits);
		cacheMissRate = ((float)cacheMiss) / ((float) cacheMiss + cacheHits);
		totalAddressesProcessed = simulator.getAddressAmount();
	}

	public float getTotalTime() {
		return totalTime;
	}

	public float getTimeAverage() {
		return timeAverage;
	}

	public int getCacheHits() {
		return cacheHits;
	}

	public float getCacheHitRate() {
		return cacheHitRate;
	}

	public int getCacheMiss() {
		return cacheMiss;
	}

	public float getCacheMissRate() {
		return cacheMissRate;
	}

	public int getTotalAddressesProcessed() {
		return totalAddressesProcessed;
	}

	@Override
	public String toString() {
		return "SimulationResult [totalTime=" + totalTime + ", timeAverage=" + timeAverage + ", cacheHits=" + cacheHits
				+ ", cacheHitRate=" + cacheHitRate + ", cacheMiss=" + cacheMiss + ", cacheMissRate=" + cacheMissRate
				+ ", totalAddressesProcessed=" + totalAddressesProcessed + "]";
	}
	
	
}
