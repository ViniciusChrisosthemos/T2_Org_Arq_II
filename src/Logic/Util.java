package Logic;

public class Util {
	public static String formatBinaryString(String binString, int bits)
	{
		int size = binString.length();
		
		if(size >= bits) return binString;
		
		while(size < bits)
		{
			binString = "0" + binString;
			size++;
		}
		
		return binString;
	}
}
