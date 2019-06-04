package logic;

public class MissStep extends Step {
	private boolean validData;

	public MissStep(Address addr, int index, boolean validData) {
		super(addr, index);
		this.validData = validData;
	}

	public boolean isValidData() {
		return validData;
	}

	public void setValidData(boolean validData) {
		this.validData = validData;
	}

	@Override
	public String toString() {
		return "Endere�o: " + getAddr() + ", Index = " + getIndex() + " -> MISS, Bit = " + validData;
	}
}
