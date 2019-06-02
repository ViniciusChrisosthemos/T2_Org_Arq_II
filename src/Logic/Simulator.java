package Logic;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Simulator {
	private Cache cache;
	private MemoryHierarchy memHierarchy;
	private List<Integer> addresses;
	private float totalCost;
	
	public Simulator()
	{
		cache = new Cache();
		memHierarchy = new MemoryHierarchy();
		addresses = new LinkedList<>();
		totalCost = 0;
	}
	
	/**
	 * Começa a simulação, caso estaja configurada, gerando os resultados no console
	 * 
	 */
	public void startSimulation()
	{
		if(setup()) {
			resetValues();
			
			for(Integer address : addresses)
			{
				if(!cache.findAddress(address))
				{
					totalCost += memHierarchy.searchAddress();
				}
				
				totalCost++;
			}
		}
	}
	
	private void resetValues() {
		cache.resetValues();
		memHierarchy.resetValues();
		totalCost = 0.0f;
	}

	public void setCacheConfig(int cacheSize, int blockAmount, int wordSize, int ways)
	{
		cache = new Cache(cacheSize, blockAmount, wordSize, ways);
	}
	
	/**
	 * Configura a simulação, informando as caracteristicas da cache dos níveis de memória
	 * 
	 * @param addrFile	nome do arquivo de endereços a ser processado
	 * @param cacheConfig	nome do arquivo de configuração da cache
	 * @param memConfig		mome do arquivo de configuração dos níveis de memória
	 */
	public void configSimulation(String addrFile, String cacheConfig, String memConfig)
	{
		loadAddress(addrFile);
		setCacheConfig(cacheConfig);
		setMemoryHierarchy(memConfig);
	}
	
	/**
	 * Inicia uma lista de endereços a partir de um arquivo
	 * @param fileName	nome do arquivo com os endereços
	 */
	public void loadAddress(String fileName)
	{
		addresses = new LinkedList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			while(reader.ready())
			{
				addresses.add(Integer.parseInt(reader.readLine()));
			}
			
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	/**
	 * Configura a cache com os dados do arquivo informado
	 * 
	 * @param fileConfigName	nome do arquivo contendo os dados da cache
	 */
	public void setCacheConfig(String fileConfigName)
	{
		int cacheSize = 0;
		int blockAmount = 0;
		int wordSize = 0;
		int ways = 0;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileConfigName)))
		{
			String[] tokens;
			while(reader.ready())
			{
				tokens = reader.readLine().replaceAll(" ", "").split(":");
				switch(tokens[0])
				{
					case "Cache_size":
						cacheSize = Integer.parseInt(tokens[1]);
						break;
					case "Block_amount":
						blockAmount = Integer.parseInt(tokens[1]);
						break;
					case "Word_size":
						wordSize = Integer.parseInt(tokens[1]);
						break;
					case "Ways":
						ways = Integer.parseInt(tokens[1]);
						break;
				}
			}
			
			cache = new Cache(cacheSize, blockAmount, wordSize, ways);
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	/**
	 * Configura a hierarquia de memória do simulado a partir do arquivo informado
	 * 
	 * @param fileConfigName	nome do arquivo com os dados da hierarquia de memória
	 */
	public void setMemoryHierarchy(String fileConfigName)
	{
		memHierarchy = new MemoryHierarchy(fileConfigName);
	}

	public Cache getCache() {
		return cache;
	}

	public void addMemoryLevel(String id, int cost, int prob) {
		memHierarchy.addMemoryLevel(id, cost, prob);
	}
	
	public List<MemoryLevel> getMemoryLevels()
	{
		return memHierarchy.getMemorys();
	}

	public boolean setup() 
	{
		return cache.setup() & memHierarchy.setup() & !addresses.isEmpty();
	}

	public float getTotalTime() {
		return totalCost;
	}

	public float getTimeAverage() {
		return totalCost / addresses.size();
	}

	public int getAddressAmount() {
		return addresses.size();
	}

	public void setRandomAlgorithm() {
		cache.setRandomAlgorithm();
	}

	public void setLFUAlgorithm() {
		cache.setLeastFrequentUsedAlgortithm();
	}

	public void setLRUAlgorithm() {
		cache.setLeastRecentUsedAlgortithm();
	}
}
