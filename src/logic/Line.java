package logic;

public class Line {
	private int tag;
	private int accesses;
	private int lastAccess;
	private boolean validateBit;

	public Line() {
		accesses = 0;
		lastAccess = 0;
		tag = 0;
		validateBit = false;
	}

	public void setLine(int tag) {
		this.tag = tag;
		accesses = 0;
		lastAccess = 0;
	}

	public int getAccesses() {
		return accesses;
	}

	public int getLastAccess() {
		return lastAccess;
	}

	public int getTag() {
		return tag;
	}

	public void setLastAccess(int id) {
		this.lastAccess = id;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public void addAccesses() {
		accesses++;
	}

	public void setValidateBit(boolean value) {
		validateBit = value;
	}

	public boolean isValid() {
		return validateBit;
	}

	public void setAccesses(int access) {
		this.accesses = access;
	}

	@Override
	public String toString() {
		return "[Bit = " + validateBit + ", Tag=" + tag + ", Accesses=" + accesses + "]";
	}
}