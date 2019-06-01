import java.util.ArrayList;
import java.util.List;

public class Line
{
	private String tag;
	private List<Integer> blocks;
	private int accesses;
	private int blocksAmount;
		
	public Line(String tag, int value, int blocksAmount)
	{
		this.tag = tag;
		this.blocksAmount = blocksAmount;
		accesses = 1;
		
		blocks = new ArrayList<>(blocksAmount);
		int cont = value - value%blocksAmount;
		
		for(int n=0; n<blocksAmount; n++)
		{
			blocks.add(cont);
			cont++;
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
		
		accesses = 1;
	}
	
	public boolean hasData(int data)
	{
		return blocks.get(data%blocksAmount) == data;
	}
	
	public int getAccesses()
	{
		return accesses;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public boolean contains(int value)
	{
		return blocks.contains(value);
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public void addAccesses()
	{
		accesses++;
	}
	
	@Override
	public String toString()
	{
		return "[Tag="+tag+", Blocks="+blocks+", Accesses="+accesses+"]";
	}
}