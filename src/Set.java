import java.util.ArrayList;
import java.util.List;

public class Set {
	
	private List<Line> lines;
	private int ways;
	
	public Set(int ways)
	{
		this.ways = ways;
		resetSet();
	}
	
	public List<Line> getLines()
	{
		return lines;
	}
	
	public void resetSet()
	{
		lines = new ArrayList<>(ways);
		for(int line=0; line<ways; line++)
		{
			lines.add(new Line(ways));
		}
	}
	
	public int getWays()
	{
		return ways;
	}
	
	public int getBlockAmount()
	{
		return lines.get(0).getBlocksAmount();
	}
}
