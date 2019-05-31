import java.util.ArrayList;
import java.util.List;

public class Line
{
	private int tag;
	private List<Integer> blocks;
	private int accesses;
	private int blocksAmount;
		
	public Line(int blocksAmount)
	{
		tag = 0;
		accesses = 0;
		this.blocksAmount = blocksAmount;
		reset();
	}
	
	public int getAccesses()
	{
		return accesses;
	}
		
	public void setLine(int tag, int value)
	{
		
		this.tag = tag;
		int cont = value - value%blocksAmount;
		
		for(int n=0; n<blocksAmount; n++)
		{
			blocks.set(n, cont);
			cont++;
		}
		
		accesses = 0;
		
	}
		
	public void reset()
	{
		tag = 0;
		blocks = new ArrayList<>(blocksAmount);
		for(int block=0; block<blocksAmount; block++)
		{
			blocks.add(0);
		}	
			
		accesses = 0;
	}
	
	public int getBlocksAmount()
	{
		return blocksAmount;
	}
	
	@Override
	public String toString()
	{
		return "[Tag="+tag+", Blocks="+blocks+", Accesses="+accesses+"]";
	}
}