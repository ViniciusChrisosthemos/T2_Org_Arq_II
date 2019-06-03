package Logic;

public class Address {
	public static int ADDRESSSIZE = 32;
	
	private int address;
	private int tag;
	private int set;
	private int block;
	
	private Address(int address, int tag, int set, int block)
	{
		this.address = address;
		this.tag = tag;
		this.set = set;
		this.block = block;
	}
	
	public static Address getFormattedAddress(int address, int tagSize, int setSize, int blockSize)
	{
		int mask = 0x7FFFFFFF;
		return new Address(address, 
						  address & (mask << setSize+blockSize),
						  (mask >> tagSize+blockSize-1) & (address >> blockSize),
						  (mask >> tagSize + setSize-1) & address);
	}
	
	
	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getSet() {
		return set;
	}

	public void setSet(int set) {
		this.set = set;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	@Override
	public String toString()
	{
		return address+" = [ Tag="+tag+", Conjunto="+set+", bloco="+block+"]";
	}
}
