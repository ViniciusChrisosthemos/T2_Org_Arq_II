import Logic.MemoryLevel;

public class MemoryLevelDAO {
	private String id;
	private int missPenalty;
	private int prob;
	private int hits;
	private int miss;
	
	public MemoryLevelDAO(MemoryLevel memLevel)
	{
		id = memLevel.getId();
		missPenalty = memLevel.getMissPenalty();
		prob = memLevel.getProb();
		hits = memLevel.getHits();
		miss= memLevel.getMiss();
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

	public int getMiss() {
		return miss;
	}
	
	public float getHitRate()
	{
		return (((float) hits) / ((float)hits + miss)) * 100.f;
	}
	
	public float getMissRate()
	{
		return (((float) miss) / ((float)hits + miss)) * 100.f;
	}
	
	@Override
	public String toString()
	{
		if(miss != 0 | hits != 0)
		{
			float querys = hits + miss;
			float hitRate = (hits/querys) * 100.0f;
			float missRate = (miss/querys) * 100.0f;
			return id+ " = [hit="+hits+", hit-rate="+String.format("%.2f", hitRate)+
					"%, miss="+miss+", miss-rate="+String.format("%.2f", missRate)+
					"%, missPenalty="+missPenalty+", prob="+prob+"]";
		}
		
		return id+ " = [hit="+hits+", miss="+miss+", missPenalty="+missPenalty+", prob="+prob+"]";
	}
}
