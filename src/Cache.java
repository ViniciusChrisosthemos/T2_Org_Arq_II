
public class Cache {

	private final int cacheSize;
	private final int blockAmount;
	private final int wordSize;
	private final int ways;
	private final int lines;
	private final int setSize;
	
	CacheLine[] associativeSet;
	
	public Cache(int cacheSize, int blockAmount, int wordSize, int ways)
	{
		this.cacheSize = cacheSize;
		this.blockAmount = blockAmount;
		this.wordSize = wordSize;
		this.ways = ways;
		
		lines = cacheSize/(blockAmount*wordSize);
		setSize = lines/ways;
		
		associativeSet = new CacheLine[lines];
		for(int i=0; i<lines; i++)
		{
			associativeSet[i] = new CacheLine(blockAmount);
		}
		
		System.out.println(toString());
	}
	
	private class CacheLine
	{
		public final int bitValidity;
		public final int[] block;
		
		public CacheLine(int blockAmount)
		{
			bitValidity = 0;
			block = new int[blockAmount];
		}
	}
	
	@Override
	public String toString()
	{
		return "Cache size = "+cacheSize+" Bytes,\n"+
			   "Block amount = "+blockAmount+" \n"+
			   "Word size = "+wordSize+" Bytes\n"+
			   "Ways = "+ways+"\n"+
			   "Lines = "+lines+"\n"+
			   "Set size = "+setSize;
	}
	
	public static void main(String[] args) {
		new Cache(65536, 32, 8, 4);
	}
}

