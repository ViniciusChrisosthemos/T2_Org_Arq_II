public class LevelMemory {
	private String id;
	private int hit;
	private int miss;
	private int missPenalty;
	private int prob;
	
	public LevelMemory(String id, int missPenalty, int prob)
	{
		this.id = id;
		this.hit = 0;
		this.miss = 0;
		this.missPenalty = missPenalty;
		this.prob = prob;
	}
	
	public boolean haveAddress(int randProb)
	{
		if(prob < randProb)
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
		return "["+id+","+hit+","+miss+","+missPenalty+","+prob+"]";
	}
}
