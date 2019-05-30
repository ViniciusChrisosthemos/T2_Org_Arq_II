
public class Set {
	
	private Line[] lines;
	
	public Set(int sizeSet)
	{
		lines = new Line[sizeSet];
	}
	
	public void resetSet()
	{
		for(int line=0; line<lines.length; line++)
		{
			lines[line].reset();
		}
	}
	
	private class Line
	{
		public int tag;
		public int[] blocks;
		public int accesses;
		
		public Line(int blocksAmount)
		{
			tag = 0;
			blocks = new int[blocksAmount];
			accesses = 0;
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
	
	
}
