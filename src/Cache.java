
public class Cache {

	private int cacheSize;
	private int blockAmont;
	private int wordSize;
	private int ways;
	private int lines;
	private int setSize;
	
	CacheLine[] associativeSet;
	
	public Cache(int cacheSize, int blockAmont, int wordSize, int ways)
	{
		this.cacheSize = cacheSize;
		this.blockAmont = blockAmont;
		this.wordSize = wordSize;
		this.ways = ways;
		
		lines = cacheSize/(blockAmont*wordSize);
		setSize = lines/ways;
		
		associativeSet = new CacheLine[lines];
		for(int i=0; i<lines; i++)
		{
			associativeSet[i] = new CacheLine(blockAmont);
		}
		
		System.out.println(toString());
	}
	
	private class CacheLine
	{
		public final int bitValidity;
		public final int[] block;
		
		public CacheLine(int blockAmout)
		{
			bitValidity = 0;
			block = new int[blockAmout];
		}
	}
	
	@Override
	public String toString()
	{
		return "Cache size = "+cacheSize+" Bytes,\n"+
			   "Block amount = "+blockAmont+" \n"+
			   "Word size = "+wordSize+" Bytes\n"+
			   "Ways = "+ways+"\n"+
			   "Lines = "+lines+"\n"+
			   "Set size = "+setSize;
	}
	
	public static void main(String[] args) {
		Cache c = new Cache(65536, 32, 8, 4);
		System.out.println();
	}
}

