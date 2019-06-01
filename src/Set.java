import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Set {
	
	private List<Line> lines;
	private int ways;
	private int blockAmount;
	private int tagSize;
	
	public Set(int ways, int blockAmount, int tagSize)
	{
		this.ways = ways;
		this.blockAmount = blockAmount;
		this.tagSize = tagSize;
		lines = new ArrayList<>(ways);
	}
	
	public void addLine(String tag, int value)
	{
		Line line = new Line(tag, value,blockAmount);
		lines.add(line);
	}
	
	public void replaceLine(int index, String tag, int value)
	{
		Line line = lines.get(index);
		line.setLine(value);
		line.setTag(tag);
		lines.set(index, line);
	}
	
	public SearchState findAddress(String tag, int address)
	{
		if(isFull())
		{
			for(Line line : lines)
			{
				if(line.getTag().equals(tag))
				{
					if(line.contains(address))
					{
						line.addAccesses();
						return SearchState.FOUND;
					}
				}
			}
			
			return SearchState.NOT_FOUND;
		}
		
		for(Line line : lines)
		{
			if(line.getTag().equals(tag))
			{
				if(line.contains(address))
				{
					line.addAccesses();
					return SearchState.FOUND;
				}
			}
		}
		
		return SearchState.INVALID;
	}
	
	public List<Line> getLines()
	{
		return lines;
	}
	
	public int getWays()
	{
		return ways;
	}
	
	public boolean isFull()
	{
		return lines.size() == ways;
	}
	
	@Override
	public String toString()
	{
		String string = "";
		for(Line line : lines)
		{
			string += "\n   " + line.toString();
		}
		
		return string + "\n";
	}
}
