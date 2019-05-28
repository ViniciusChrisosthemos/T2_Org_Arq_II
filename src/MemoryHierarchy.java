import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryHierarchy {
	Random rand;
	List<MemoryLevel> memoryLevels;
	
	public MemoryHierarchy(String fileName)
	{
		rand = new Random();
		memoryLevels = new LinkedList<>();
		
		loadMemoryLevels(fileName);
	}
	
	public void loadMemoryLevels(String fileName)
	{
		String[] tokens;
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			
			while(reader.ready())
			{
				tokens = reader.readLine().split(":");
				
				memoryLevels.add(new MemoryLevel(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
			}
			
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public int searchAddress()
	{
		int timeCost = 0;
		int randProb;
		for(MemoryLevel lvlMem : memoryLevels)
		{
			randProb = rand.nextInt(100);
			timeCost += lvlMem.getMissPenalty();
			
			if(lvlMem.hasAddress(randProb))
			{
				break;
			}
		}
		
		return timeCost;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("Niveis de memoria:\n");
		for(MemoryLevel memLvl : memoryLevels)
		{
			string.append("   ").append(memLvl).append("\n");
		}
		
		return string.toString();
	}
}
