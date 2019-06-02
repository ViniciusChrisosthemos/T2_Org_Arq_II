package Logic;

public class Line
{
	private int data;
	private int accesses;
	private String tag;
	private boolean validateBit;
		
	public Line()
	{
		data = 0;
		accesses = 0;
		tag = null;
		validateBit = false;
	}
	
	public void setLine(String tag, int data)
	{
		this.tag = tag;
		this.data = data;
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
	
	public void setTag(String tag)
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