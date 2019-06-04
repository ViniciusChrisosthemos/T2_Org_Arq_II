package Logic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FileGenerator {
	private Map<Integer,Jump> jumps;
	private int maxEnd;
	private int endProg;
	private static FileGenerator instance;

	private FileGenerator(){
		jumps = new HashMap<>();
	}

	public static FileGenerator getInstance(){
		return (instance != null) ? instance : (instance = new FileGenerator());
	}

	public void createAddressFile(String programFile, String addressFile)
	{		
		formatProgram(programFile);
		try
		{
			File file = new File(addressFile);
			
			if(!file.exists()) file.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
			Random rand = new Random();
			Jump jump;
			int address = 0;
			int addressAmout = 0;
			
			while(address != endProg && addressAmout < maxEnd)
			{
				writer.write(address + "\n");
				jump = jumps.get(address);
				if(jump != null)
				{
					if(jump.conditional)
					{
						if(rand.nextInt(100) <= jump.prob) address = jump.destiny;
						else address++;
					}
					else
					{
						address = jump.destiny;
					}	
				}else
				{
					address++;
				}

				addressAmout++;
			}
			
			writer.close();
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	private void formatProgram(String addressFile)
	{		
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(addressFile))))
		{
			String[] tokens;
			maxEnd = 0;
			endProg = 0;
			int number;			
			jumps = new HashMap<>();
			
			while(reader.ready())
			{
				tokens = reader.readLine().split(":");
				
				switch(tokens[0])
				{
					case "max":
						maxEnd = Integer.parseInt(tokens[1]);
						break;
					case "ep":
						endProg = Integer.parseInt(tokens[1]);
						break;
					case "ji":
						number = Integer.parseInt(tokens[1]);
						jumps.put(number, new Jump(Integer.parseInt(tokens[2]), false, 0));
						break;
					case "bi":
						number = Integer.parseInt(tokens[1]);
						jumps.put(number ,new Jump(Integer.parseInt(tokens[2]), true, Integer.parseInt(tokens[3])));
						break;
				}
			}
			
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	private class Jump
	{
		public final int destiny;
		public final boolean conditional;
		public final int prob;
		
		public Jump(int destiny, boolean conditional, int prob)
		{
			this.destiny = destiny;
			this.conditional = conditional;
			this.prob = prob;
		}
		
		@Override
		public String toString()
		{
			return "[Destiny: "+destiny+", Conditional: "+conditional+", Probability: "+prob+"]";
		}
	}
}
