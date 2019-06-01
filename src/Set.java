import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Set {
	
	private List<Line> lines;
	private int ways;
	
	public Set(int ways, int blockAmount, int tagSize)
	{
		this.ways = ways;
		lines = new ArrayList<>(ways);
		
		for(int line=0; line<ways; line++)
		{
			lines.add(new Line(blockAmount, tagSize));
		}
	}
	
	public void setLine(int index, int value)
	{
		Line line = lines.get(index);
		line.setLine(value);
		line.setValidationBit(true);
		line.addAccesses();
		lines.set(index, line);
	}
	
	public void replaceLine(int index, String tag, int value)
	{
		Line line = lines.get(index);
		line.setLine(value);
		line.setTag(tag);
		line.addAccesses();
		lines.set(index, line);
	}
	
	public void findAddress(String tag, int address) throws ValidationBitFalse, DataNotFound
	{
		int index = 0;
		for(Line line : lines)
		{
			if(line.isValid()) 
			{
				if(line.getTag().equals(tag))
				{
					if(line.hasData(address))
					{
						line.addAccesses();
						return;
					}
					else throw new DataNotFound();
				}
			}else
			{
				throw new ValidationBitFalse(index);
			}
			index++;
		}
		
		throw new DataNotFound();
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
		
		return string;
	}
}
