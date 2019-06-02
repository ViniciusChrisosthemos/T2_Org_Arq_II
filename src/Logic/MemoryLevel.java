package Logic;
public class MemoryLevel {
	private String id;
	private int hit;
	private int miss;
	private int missPenalty;
	private int prob;
	
	public MemoryLevel(String id, int missPenalty, int prob)
	{
		this.id = id;
		this.hit = 0;
		this.miss = 0;
		this.missPenalty = missPenalty;
		this.prob = prob;
	}
	
	public boolean hasAddress(int randProb)
	{
		if(randProb < prob)
		{
			hit++;
			return true;
		}else
		{
			miss++;
			return false;
		}
	}
	
	public int getMissPenalty()
	{
		return missPenalty;
	}
	
	public String getId() {
		return id;
	}

	public int getHits() {
		return hit;
	}

	public int getMiss() {
		return miss;
	}

	public int getProb() {
		return prob;
	}

	@Override
	public String toString()
	{
		if(miss != 0 | hit != 0)
		{
			float querys = hit + miss;
			float hitRate = (hit/querys) * 100.0f;
			float missRate = (miss/querys) * 100.0f;
			return id+ " = [hit="+hit+", hit-rate="+String.format("%.2f", hitRate)+
					"%, miss="+miss+", miss-rate="+String.format("%.2f", missRate)+
					"%, missPenalty="+missPenalty+", prob="+prob+"]";
		}
		
		return id+ " = [hit="+hit+", miss="+miss+", missPenalty="+missPenalty+", prob="+prob+"]";
	}

	public void resetValues() {
		hit = 0;
		miss = 0;
	}
}
