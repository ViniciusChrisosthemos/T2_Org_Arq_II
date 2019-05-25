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
	Map<Integer,Jump> jumps = new HashMap<>();
	int maxEnd;
	int endProg;
	
	public static void main(String[] args) {
		FileGenerator util = new FileGenerator();
		util.formatProgram("prog1.txt");
		util.CreateAddressFile();
	}

	public void CreateAddressFile()
	{
		try
		{
			File file = new File("enderecos.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
			Random rand = new Random();
			Jump jump;
			int address = 0;
			int addressAmout = 0;
			
			while(address <= endProg && addressAmout < maxEnd)
			{
				writer.write(address + "\n");
				jump = jumps.get(address);
				if(jump != null)
				{
					if(jump.conditional)
					{
						if(jump.prob < rand.nextInt(100)) address = jump.destiny;
					}
					else
					{
						address = jump.destiny;
					}
				}

				address++;
				addressAmout++;
			}
			
			writer.close();
		}catch(IOException e)
		{
			System.out.println(e);
		}
	}
	
	public void formatProgram(String fileName)
	{
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(fileName))))
		{
			String[] tokens;
			maxEnd = 0;
			endProg = 0;
			int number;
			
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
			
			System.out.println("Max = " + maxEnd + " EP = " + endProg);
			System.out.println(jumps);
			
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
