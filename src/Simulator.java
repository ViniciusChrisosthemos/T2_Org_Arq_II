import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Simulator {
	private Cache cache;
	private MemoryHierarchy memHierarchy;
	private List<Integer> addresses;
	private int simulationTime;
	private int cacheHits;
	private int cacheMiss;
	private boolean setup;
	
	public Simulator()
	{
		cache = null;
		memHierarchy = null;
		simulationTime = 0;
		cacheHits = 0;
		cacheMiss = 0;
		setup = false;
	}
	
	public void startSimulation()
	{
		if(setup) {
			
			for(Integer address : addresses)
			{
				//TODO
			}
		}
		else
		{
			System.out.println("A simulacao deve ser configurada antes de ser iniciada");
		}
	}
	
	public void configSimulation(String addrFile, String cacheConfig, String memConfig)
	{
		loadAddress(addrFile);
		setCacheConfig(cacheConfig);
		setMemoryHierarchy(memConfig);
		
		System.out.println("Simulacao configurada com sucesso!");
	}
	
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
			
			System.out.println("Cache configurada com sucesso!");
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public void setMemoryHierarchy(String fileConfigName)
	{
		memHierarchy = new MemoryHierarchy(fileConfigName);
		System.out.println("Hierarquia de memoria configurada com sucesso!\n");
		System.out.println(memHierarchy);
	}
	
	public void setSimulationResult()
	{
		//TODO
	}
}
