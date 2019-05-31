import java.util.ArrayList;
import java.util.List;

public class Cache {
	//Constantes utilizadas
	private static final int WORDSIZE = 32;
	
	//Informações da estrutura da Cache
	private final int cacheSize;
	private final int blockAmount;
	private List<Set> associativeSets;
	private PoliticStrategy politicStrategy;
	
	//Divisões do endereço a ser processado
	private int setAddrSize;
	private int blockAddrSize;
	private int tagAddrSize;
	
	
	public Cache(int cacheSize, int blockAmount, int wordSize, int ways)
	{
		this.cacheSize = cacheSize;
		this.blockAmount = blockAmount;
	
		int lines = cacheSize/(blockAmount*wordSize);
		
		associativeSets = new ArrayList<>(lines/ways);
		
		for(int i=0; i<lines; i++)
		{
			associativeSets.add(new Set(ways));
		}
		
		politicStrategy = PoliticStrategy.randomAlgorithm();
		
		setAddrSize = Integer.toBinaryString(associativeSets.size()).length();
		blockAddrSize = Integer.toBinaryString(associativeSets.get(0).getWays()).length();
		tagAddrSize = WORDSIZE - setAddrSize - blockAddrSize;
		
		System.out.println(toString());
	}
	
	public boolean findAddress(int address)
	{
		String binAddress = Util.formatBinaryString(Integer.toBinaryString(address), WORDSIZE);
		
		String tagString = binAddress.substring(0, tagAddrSize);
		String setString = binAddress.substring(tagAddrSize, tagAddrSize+setAddrSize);
		String blockString = binAddress.substring(WORDSIZE - blockAddrSize, WORDSIZE);
		
		System.out.println("Tag = " + Util.formatBinaryString(tagString, tagAddrSize));
		System.out.println("Set = " + Util.formatBinaryString(setString, setAddrSize));
		System.out.println("Block = " + Util.formatBinaryString(blockString, blockAddrSize));
		
		associativeSets.get(0).getLines().get(0).setLine(0, 27);
		System.out.println(associativeSets.get(0).getLines().get(0));
		
		return true;
	}
	
	public void setRandomAlgorithm()
	{
		politicStrategy = PoliticStrategy.randomAlgorithm();
	}
	
	public void setLeastFrequentUsedAlgortithm()
	{
		politicStrategy = PoliticStrategy.leastFrequentUsedAlgortithm();
	}
	
	public int getBlockAmount()
	{
		return associativeSets.get(0).getBlockAmount();
	}
	
	public int getTotalLines()
	{
		return associativeSets.size() * associativeSets.get(0).getWays();
	}
	
	public int getWays()
	{
		return associativeSets.get(0).getWays();
	}
	
	@Override
	public String toString()
	{
		return "Cache info:\n	Cache size = "+cacheSize+" Bytes,\n"+
			   "	Block amount = "+blockAmount+" \n"+
			   "	Word size = "+WORDSIZE+" Bytes\n"+
			   "	Ways = "+getWays()+"\n"+
			   "	Lines = "+getTotalLines()+"\n"+
			   "	Set size = "+associativeSets.size()+
			   "\nAddress info:\n"+
			   "	Tag size = "+tagAddrSize+",\n"+
			   "	Set size = "+setAddrSize+",\n"+
			   "	Block size = "+blockAddrSize;
	}
	
	public static void main(String[] args) {
		Cache c = new Cache(65536, 32, 8, 4);
		c.findAddress(1);
		
	}
}

