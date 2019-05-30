
public class Cache {

	private final int cacheSize;
	private final int blockAmount;
	private final int wordSize;
	private final int ways;
	private final int lines;
	private Set[] associativeSets;
	
	public Cache(int cacheSize, int blockAmount, int wordSize, int ways)
	{
		this.cacheSize = cacheSize;
		this.blockAmount = blockAmount;
		this.wordSize = wordSize;
		this.ways = ways;
		
		lines = cacheSize/(blockAmount*wordSize);
		
		associativeSets = new Set[lines/ways];
		
		for(int i=0; i<lines; i++)
		{
			associativeSets[i] = new Set(ways);
		}
		
		System.out.println(toString());
	}
	
	@Override
	public String toString()
	{
		return "Cache size = "+cacheSize+" Bytes,\n"+
			   "Block amount = "+blockAmount+" \n"+
			   "Word size = "+wordSize+" Bytes\n"+
			   "Ways = "+ways+"\n"+
			   "Lines = "+lines+"\n"+
			   "Set size = "+associativeSets.length;
	}
	
	public static void main(String[] args) {
		new Cache(65536, 32, 8, 4);
	}
}

