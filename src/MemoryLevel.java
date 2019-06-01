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
	
	@Override
	public String toString()
	{
		return id+ " = [hit="+hit+", miss="+miss+", missPenalty="+missPenalty+", prob="+prob+"]";
	}
}
