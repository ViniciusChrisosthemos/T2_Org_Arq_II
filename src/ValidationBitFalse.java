
public class ValidationBitFalse extends Exception {
	private int index;
	
	public ValidationBitFalse(int index)
	{
		super();
		this.index = index;
	}
	
	public int getIndex()
	{
		return index;
	}
}
