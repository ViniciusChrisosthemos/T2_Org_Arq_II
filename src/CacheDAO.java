import Logic.Cache;

public class CacheDAO {
	private int cacheSize;
	private int blockAmount;
	private int wordSize;
	private int ways;
	private int lines;
	private int associativeSetSize;
		
	private int setAddrSize;
	private int blockAddrSize;
	private int tagAddrSize;
	
	private int addressSize;

	public CacheDAO(Cache cache)
	{
		cacheSize = cache.getCacheSize();
		blockAmount = cache.getBlockAmount();
		wordSize = cache.getWordSize();
		ways = cache.getWays();
		lines = cache.getLines();
		associativeSetSize = cache.getAssociativeSetSize();
		setAddrSize = cache.getSetAddrSize();
		blockAddrSize = cache.getBlockAddrSize();
		tagAddrSize = cache.getTagAddrSize();
		addressSize = cache.getAddressSize();
	}
	
	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getBlockAmount() {
		return blockAmount;
	}

	public void setBlockAmount(int blockAmount) {
		this.blockAmount = blockAmount;
	}

	public int getWordSize() {
		return wordSize;
	}

	public void setWordSize(int wordSize) {
		this.wordSize = wordSize;
	}

	public int getWays() {
		return ways;
	}

	public void setWays(int ways) {
		this.ways = ways;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getAssociativeSetSize() {
		return associativeSetSize;
	}

	public void setAssociativeSetSize(int associativeSetSize) {
		this.associativeSetSize = associativeSetSize;
	}

	public int getSetAddrSize() {
		return setAddrSize;
	}

	public void setSetAddrSize(int setAddrSize) {
		this.setAddrSize = setAddrSize;
	}

	public int getBlockAddrSize() {
		return blockAddrSize;
	}

	public void setBlockAddrSize(int blockAddrSize) {
		this.blockAddrSize = blockAddrSize;
	}

	public int getTagAddrSize() {
		return tagAddrSize;
	}

	public void setTagAddrSize(int tagAddrSize) {
		this.tagAddrSize = tagAddrSize;
	}

	public int getAddressSize() {
		return addressSize;
	}
}
