package Logic;
import Logic.Address;

public class Step {
	private Address addr;
	private int index;
	
	public Step(Address addr, int index)
	{
		this.addr = addr;
		this.index = index;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public String toString()
	{
		return "Endereço: "+addr+" Index = "+index+" -> HIT";
	}
}
