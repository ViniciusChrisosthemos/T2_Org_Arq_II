public class Line
{
	private int tag;
	private int[] blocks;
	private int accesses;
		
	public Line(int blocksAmount)
	{
		tag = 0;
		blocks = new int[blocksAmount];
		accesses = 0;
	}
	
	public int getAccesses()
	{
		return accesses;
	}
		
	public void setLine(int tag, int[] blocks)
	{
		this.tag = tag;
		this.blocks = blocks;
		accesses = 0;
	}
		
	public void reset()
	{
		tag = 0;
		blocks = new int[blocks.length];
		accesses = 0;
	}
}