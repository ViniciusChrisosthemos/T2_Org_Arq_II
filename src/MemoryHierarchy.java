import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MemoryHierarchy {
	Random rand;
	List<LevelMemory> levelMemorys;
	
	public MemoryHierarchy(String fileName)
	{
		rand = new Random();
		levelMemorys = new LinkedList<>();
		
		LoadMemoryLevels(fileName);
	}
	
	public void LoadMemoryLevels(String fileName)
	{
		String[] tokens;
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			
			while(reader.ready())
			{
				tokens = reader.readLine().split(":");
				
				levelMemorys.add(new LevelMemory(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
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
		for(LevelMemory lvlMem : levelMemorys)
		{
			randProb = rand.nextInt(100);
			timeCost += lvlMem.getMissPenalty();
			
			if(lvlMem.haveAddress(randProb))
			{
				break;
			}
		}
		
		return timeCost;
	}
	
	public static void main(String[] args) {
		MemoryHierarchy mem = new MemoryHierarchy("mem.txt");
		
		for(LevelMemory lvl : mem.levelMemorys)
		{
			System.out.println(lvl);
		}
		
		System.out.println(mem.searchAddress());
	}
}
