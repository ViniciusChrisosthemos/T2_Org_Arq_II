import java.util.ArrayList;
import java.util.List;

public class Cache {
	//Constantes utilizadas
	private static final int ADDRESSSIZE = 32;
	
	//Informações da estrutura da Cache
	private int cacheSize;
	private int blockAmount;
	private int wordSize;
	private int ways;
	private int lines;
	private int associativeSetSize;
	private List<Set> associativeSets;
	private PoliticStrategy politicStrategy;
	
	//Divisões do endereço a ser processado
	private int setAddrSize;
	private int blockAddrSize;
	private int tagAddrSize;
	
	//Estatísticas
	private int hits;
	private int miss;
	
	
	public Cache(int cacheSize, int blockAmount, int wordSize, int ways)
	{
		this.cacheSize = cacheSize;
		this.blockAmount = blockAmount;
		this.wordSize = wordSize;
		this.ways = ways;
		
		lines = cacheSize/(blockAmount*wordSize);
		associativeSetSize = lines/ways;
		setAddrSize = Integer.toBinaryString(associativeSetSize).length()-1;
		blockAddrSize = Integer.toBinaryString(blockAmount).length()-1;
		tagAddrSize = ADDRESSSIZE - setAddrSize - blockAddrSize;
		
		associativeSets = new ArrayList<>(associativeSetSize);
		for(int i=0; i<associativeSetSize; i++)
		{
			associativeSets.add(new Set(ways, blockAmount, tagAddrSize));
		}
		
		politicStrategy = PoliticStrategy.randomAlgorithm();
		
		System.out.println(toString());
	}
	
	public boolean findAddress(int address)
	{
		String binAddress = Util.formatBinaryString(Integer.toBinaryString(address), ADDRESSSIZE);
		
		String tagString = binAddress.substring(0, tagAddrSize);
		String setString = binAddress.substring(tagAddrSize, tagAddrSize+setAddrSize);
		String blockString = binAddress.substring(ADDRESSSIZE - blockAddrSize, ADDRESSSIZE);
		System.out.println("Address = " + address);
		System.out.println("Tag = " + Util.formatBinaryString(tagString, tagAddrSize));
		System.out.println("Set = " + Util.formatBinaryString(setString, setAddrSize));
		System.out.println("Block = " + Util.formatBinaryString(blockString, blockAddrSize));
		
		Set set = associativeSets.get(Integer.parseInt(setString, 2));
		try {
			
			set.findAddress(tagString, address);
			return true;
			
		}catch(DataNotFound e)
		{
			
			int index = politicStrategy.getIndex(set);
			set.replaceLine(index, tagString, address);
			
		}catch(ValidationBitFalse e)
		{
			
			set.setLine(e.getIndex(), address);
			
		}
		
		return false;
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
		return blockAmount;
	}
	
	public int getTotalLines()
	{
		return lines;
	}
	
	public int getWays()
	{
		return ways;
	}
	
	public void printSets()
	{
		for(Set set : associativeSets)
		{
			System.out.println(set);
		}
	}
	
	public int getMiss()
	{
		return miss;
	}
	
	public int getHits()
	{
		return hits;
	}
	
	public void printCacheResult()
	{
		System.out.println("Cache = [Hits= "+hits+", Miss="+miss+"]");
	}
	
	@Override
	public String toString()
	{
		return "Cache info:\n	Cache size = "+cacheSize+" Bytes,\n"+
			   "	Block amount = "+blockAmount+" \n"+
			   "	Word size = "+wordSize+" Bytes\n"+
			   "	Ways = "+ways+"\n"+
			   "	Lines = "+lines+"\n"+
			   "	Set size = "+associativeSetSize+
			   "\nAddress info:\n"+
			   "	Tag size = "+tagAddrSize+",\n"+
			   "	Set size = "+setAddrSize+",\n"+
			   "	Block size = "+blockAddrSize;
	}
	
	public static void main(String[] args) {
		Cache c = new Cache(32, 2, 4, 2);
		for(int i=0; i<31; i++)
		{
			System.out.println(c.findAddress(i));
			c.printSets();
		}
	}
}

