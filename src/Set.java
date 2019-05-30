import java.util.ArrayList;
import java.util.List;

public class Set {
	
	private List<Line> lines;
	
	public Set(int ways)
	{
		lines = new ArrayList<>(ways);
	}
	
	public List<Line> getLines()
	{
		return lines;
	}
	
	public void resetSet()
	{
		lines.forEach(line -> line.reset());
	}
}
