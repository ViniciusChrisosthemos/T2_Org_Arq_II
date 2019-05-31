import java.util.ArrayList;
import java.util.List;

public class Line
{
	private String tag;
	private List<Integer> blocks;
	private int accesses;
	private int blocksAmount;
	private int validationBit;
		
	public Line(String tag, int blocksAmount)
	{
		this.tag = tag;
		accesses = 0;
		validationBit = 0;
		this.blocksAmount = blocksAmount;
		
		blocks = new ArrayList<>(blocksAmount);
		for(int block=0; block<blocksAmount; block++)
		{
			blocks.add(0);
		}
	}
		
	public void setLine(int value)
	{
		int cont = value - value%blocksAmount;
		
		for(int n=0; n<blocksAmount; n++)
		{
			blocks.set(n, cont);
			cont++;
		}
		
		accesses = 0;
	}
	
	public int getAccesses()
	{
		return accesses;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	@Override
	public String toString()
	{
		return "[Tag="+tag+", Blocks="+blocks+", Accesses="+accesses+"]";
	}
}