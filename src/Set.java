import java.util.LinkedList;
import java.util.List;

public class Set {
	
	private Line[] lines;
	private List<Line> teste;
	
	public Set(int sizeSet)
	{
		lines = new Line[sizeSet];
		teste = new LinkedList<>();
	}
	
	public List<Line> getList()
	{
		return teste;
	}
	
	public void resetSet()
	{
		for(int line=0; line<lines.length; line++)
		{
			lines[line].reset();
		}
	}
}
