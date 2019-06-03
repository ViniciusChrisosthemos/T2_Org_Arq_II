package Logic;
import java.util.ArrayList;
import java.util.LinkedList;
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
	
	//Controle
	private boolean setup;
	
	public Cache()
	{
		cacheSize = 0;
		blockAmount = 0;
		wordSize = 0;
		ways = 0;
		lines = 0;
		associativeSetSize = 0;
		associativeSets = new LinkedList<Set>();
		politicStrategy = PoliticStrategy.randomAlgorithm();
		setAddrSize = 0;
		blockAddrSize = 0;
		tagAddrSize = 0;
		hits = 0;
		miss = 0;
		setup = false;
	}
	
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
		setAddrSize = (int) (Math.log(associativeSetSize) / Math.log(2));
		blockAddrSize = (int) (Math.log(blockAmount) / Math.log(2));
		blockAddrSize = (blockAddrSize == 0) ? 1:blockAddrSize;
		tagAddrSize = ADDRESSSIZE - setAddrSize - blockAddrSize;
		
		associativeSets = new ArrayList<>(associativeSetSize);
		for(int i=0; i<associativeSetSize; i++)
		{
			associativeSets.add(new Set(ways));
		}
		
		politicStrategy = PoliticStrategy.randomAlgorithm();

		setup = true;
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
		String data = binAddress.substring(binAddress.length()-blockAddrSize, binAddress.length());
		
		int setIndex = Integer.parseInt(setString, 2);
		Set set = associativeSets.get(setIndex);

		System.out.print(address + " = ["+tagString+", "+setString+", "+data+"] -> ");

		if(set.findAddress(tagString))
		{
			hits++;

			System.out.println("HIT");
			System.out.println(associativeSets);
			return true;
		}
		
		int index;
		
		if(set.isFull())
		{
			index = politicStrategy.getIndex(set);
			set.replaceLine(index, tagString, Integer.parseInt(data, 2));
		}else
		{
			index = set.setLine(tagString, Integer.parseInt(data, 2));
		}

		System.out.println("MISS");
		System.out.println(associativeSets);
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
	 * Define a política de substituição pelo menos frequente acessado
	 */
	public void setLeastFrequentUsedAlgortithm()
	{
		politicStrategy = PoliticStrategy.leastFrequentUsedAlgortithm();
	}
	
	/**
	 * Define a política de substituição pelo mais recente acessado
	 */
	public void setLeastRecentUsedAlgortithm()
	{
		politicStrategy = PoliticStrategy.leastRecentUsedAlgortithm();
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

	public int getCacheSize() {
		return cacheSize;
	}

	public int getWordSize() {
		return wordSize;
	}

	public int getLines() {
		return lines;
	}

	public int getAssociativeSetSize() {
		return associativeSetSize;
	}

	public int getSetAddrSize() {
		return setAddrSize;
	}

	public int getBlockAddrSize() {
		return blockAddrSize;
	}

	public int getTagAddrSize() {
		return tagAddrSize;
	}

	public int getAddressSize() {
		return ADDRESSSIZE;
	}

	public boolean setup() {
		return setup;
	}
	
	@Override
	public String toString()
	{
		return "Configuracao da Cache:\n	Tamanho da cache = "+cacheSize+" Bytes\n"+
			   "	Quantidade de blocos = "+blockAmount+" \n"+
			   "	Tamanho da palavra = "+wordSize+" Bytes\n"+
			   "	Numero de vias = "+ways+"\n"+
			   "	Quantidade de linhas = "+lines+"\n"+
			   "	Quantidade de conjuntos = "+associativeSetSize;
	}

	public void resetValues() {
		hits = 0;
		miss = 0;
		
		associativeSets = new ArrayList<>(associativeSetSize+1);
		for(int i=0; i<associativeSetSize+1; i++)
		{
			associativeSets.add(new Set(ways));
		}
	}
	

	
	public static void main(String[] args) {
		Cache c = new Cache(64, 1, 4, 4);
		System.out.println(c);
		for(int i=0; i<31; i++)
		{
			c.findAddress(i);
		}
	}
}

