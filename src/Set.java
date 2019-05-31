import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Set {
	
	private Map<String, Line> lines;
	private int ways;
	
	public Set(int ways, int blockAmount)
	{
		this.ways = ways;
		lines = new HashMap<>(blockAmount);
	}
	
	public Collection<Line> getLines()
	{
		return lines.values();
	}
	
	public List<String> getTags()
	{
		return new ArrayList<String>(lines.keySet());
	}
	
	public int getWays()
	{
		return ways;
	}
}
