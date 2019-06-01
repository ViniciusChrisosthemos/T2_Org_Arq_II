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
	
	/**
	 * Construtor da classe Cache
	 * 
	 * @param cacheSize		Tamanho da cache em BYTES
	 * @param blockAmount	Quantidade de blocos em uma linha da cache
	 * @param wordSize		Tamanho da palavra do bloco
	 * @param ways			Número de linhas em um conjunto associativo
	 */
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
		
		associativeSets = new ArrayList<>(associativeSetSize+1);
		for(int i=0; i<associativeSetSize+1; i++)
		{
			associativeSets.add(new Set(ways, blockAmount, tagAddrSize+1));
		}
		
		politicStrategy = PoliticStrategy.randomAlgorithm();
		
		System.out.println(toString());
	}
	
	/**
	 * Tenta encontrar o endereço informado
	 * 		Se não for encontrado, a o conjunto é atualizado
	 * @param address endereço a ser procurado
	 * @return
	 * 		True se achar
	 * 		False caso contrário
	 */
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
		System.out.println(associativeSets);
		try {
			
			set.findAddress(tagString, address);
			hits++;
			System.out.println("--->> HIT");
			return true;
			
		}catch(DataNotFound e)
		{
			
			int index = politicStrategy.getIndex(set);
			set.replaceLine(index, tagString, address);
			
		}catch(ValidationBitFalse e)
		{
			
			set.setLine(e.getIndex(), address);
			
		}

		System.out.println("--->> MISS");
		miss++;
		return false;
	}
	
	/**
	 * Define a política de subtituição randomica
	 */
	public void setRandomAlgorithm()
	{
		politicStrategy = PoliticStrategy.randomAlgorithm();
	}
	
	/**
	 * Define a política de substituição pelo menos recente acessado
	 */
	public void setLeastFrequentUsedAlgortithm()
	{
		politicStrategy = PoliticStrategy.leastFrequentUsedAlgortithm();
	}
	
	/**
	 * getter blockAmount
	 * @return blockAmount
	 */
	public int getBlockAmount()
	{
		return blockAmount;
	}
	
	/**
	 * getter lines
	 * @return lines
	 */
	public int getTotalLines()
	{
		return lines;
	}
	
	/**
	 * getter ways
	 * @return ways
	 */
	public int getWays()
	{
		return ways;
	}
	
	/**
	 * Exibe no console os conjuntos associativos
	 */
	public void printSets()
	{
		for(Set set : associativeSets)
		{
			System.out.println(set);
		}
	}
	
	/**
	 * getter miss
	 * @return miss
	 */
	public int getMiss()
	{
		return miss;
	}
	
	/**
	 * getter hits
	 * @return hits
	 */
	public int getHits()
	{
		return hits;
	}
	
	/**
	 * Exibe no console o resultado da Cache
	 */
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
			   "	Tag  = "+tagAddrSize+",\n"+
			   "	Set  = "+setAddrSize+",\n"+
			   "	Block = "+blockAddrSize;
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

