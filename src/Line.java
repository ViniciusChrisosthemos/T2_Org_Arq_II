import java.util.ArrayList;
import java.util.List;

public class Line
{
	private String tag;
	private List<Integer> blocks;
	private int accesses;
	private int blocksAmount;
	private boolean validationBit;
		
	public Line(int blocksAmount, int tagSize)
	{
		tag = Util.formatBinaryString("0", tagSize);
		accesses = 0;
		validationBit = false;
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
	
	public void setValidationBit(boolean bit)
	{
		validationBit = bit;
	}
	
	public boolean isValid()
	{
		return validationBit;
	}
	
	public void addAccesses()
	{
		accesses++;
	}
	
	@Override
	public String toString()
	{
		return "[Tag="+tag+", Blocks="+blocks+", Accesses="+accesses+", Bit="+validationBit+"]";
	}
}