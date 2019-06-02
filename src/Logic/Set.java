package Logic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Set {
	
	private List<Line> lines;
	private int ways;
	
	public Set(int ways)
	{
		this.ways = ways;
		lines = new ArrayList<>(ways);
		
		for(int i=0; i<ways; i++)
		{
			lines.add(new Line());
		}
	}
	
	public void replaceLine(int index, String tag, int value)
	{
		Line line = lines.get(index);
		line.setLine(tag, value);
		line.setValidateBit(true);
		line.addAccesses();
		lines.set(index, line);
	}
	
	public boolean findAddress(String tag)
	{
		for(Line line : lines)
		{
			if(line.isValid())
			{
				if(tag.equals(line.getTag()))
				{
					line.addAccesses();
					return true;
				}
			}
		}
		
		return false;
	}
	
	public List<Line> getLines()
	{
		return lines;
	}
	
	public int getWays()
	{
		return ways;
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
