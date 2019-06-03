package Logic;

public class Line
{
	private int tag;
	private int data;
	private int accesses;
	private boolean validateBit;
		
	public Line()
	{
		data = 0;
		accesses = 0;
		tag = 0;
		validateBit = false;
	}
	
	public void setLine(int tag, int data)
	{
		this.tag = tag;
		this.data = data;
		accesses = 0;
	}
	
	public int getAccesses()
	{
		return accesses;
	}
	
	public int getTag()
	{
		return tag;
	}
	
	public void setTag(int tag)
	{
		this.tag = tag;
	}

	public void addAccesses()
	{
		accesses++;
	}
	
	public void setValidateBit(boolean value)
	{
		validateBit = value;
	}
	
	public boolean isValid()
	{
		return validateBit;
	}

	public void setAccesses(int access) {
		this.accesses = access;
	}
	
	@Override
	public String toString()
	{
		return "[Bit = "+validateBit+", Tag="+tag+", Data="+data+", Accesses="+accesses+"]";
	}
}