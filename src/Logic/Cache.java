package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cache {
	// Constantes utilizadas

	// Informações da estrutura da Cache
	private int cacheSize;
	private int wordAmount;
	private int wordSize;
	private int ways;
	private int lines;
	private int associativeSetSize;
	private List<Set> associativeSets;
	private PoliticStrategy politicStrategy;

	// Divisões do endereço a ser processado
	private int setAddrSize;
	private int blockAddrSize;
	private int tagAddrSize;

	// Estatísticas
	private int hits;
	private int miss;

	// Controle
	private boolean setup;

	public Cache() {
		cacheSize = 0;
		wordAmount = 0;
		wordSize = 0;
		ways = 0;
		lines = 0;
		associativeSetSize = 0;
		associativeSets = new LinkedList<Set>();
		politicStrategy = PoliticStrategy.randomAlgorithm();
		setAddrSize = 0;
		blockAddrSize = 0;
		tagAddrSize = 0;
		hits = 0;
		miss = 0;
		setup = false;
	}

	/**
	 * Construtor da classe Cache
	 *
	 * @param cacheSize  Tamanho da cache em BYTES
	 * @param wordAmount Quantidade de blocos em uma linha da cache
	 * @param wordSize   Tamanho da palavra do bloco
	 * @param ways       Número de linhas em um conjunto associativo
	 */
	public Cache(int cacheSize, int wordAmount, int wordSize, int ways) {
		this.cacheSize = cacheSize;
		this.wordAmount = wordAmount;
		this.wordSize = wordSize;
		this.ways = ways;

		lines = cacheSize / (wordAmount * wordSize);
		associativeSetSize = lines / ways;
		setAddrSize = (int) (Math.log(associativeSetSize) / Math.log(2));
		blockAddrSize = (int) (Math.log(wordAmount) / Math.log(2));
		tagAddrSize = Address.ADDRESSSIZE - setAddrSize - blockAddrSize;

		associativeSets = new ArrayList<>(associativeSetSize);
		for (int i = 0; i < associativeSetSize; i++) {
			associativeSets.add(new Set(ways));
		}

		politicStrategy = PoliticStrategy.randomAlgorithm();

		setup = true;
	}

	/**
	 * Tenta encontrar o endereço informado Se não for encontrado, a o conjunto é
	 * atualizado
	 *
	 * @param address endereço a ser procurado
	 * @return True se achar False caso contrário
	 */
	public Step findAddress(int address) {
		Address addr = Address.getFormattedAddress(address, tagAddrSize, setAddrSize, blockAddrSize);

		Set set = associativeSets.get(addr.getSet());

		if (set.findAddress(addr.getTag(), hits + 1)) {
			hits++;
			return new Step(addr, set.getIndex(addr.getTag()));
		}

		int index;

		if (set.isFull()) {
			index = politicStrategy.getIndex(set);
			set.replaceLine(index, addr.getTag());
		} else {
			index = set.setLine(addr.getTag());
		}

		miss++;
		return new MissStep(addr, index, set.isFull());
	}

	/**
	 * Define a política de subtituição randomica
	 */
	public void setRandomAlgorithm() {
		politicStrategy = PoliticStrategy.randomAlgorithm();
	}

	/**
	 * Define a política de substituição pelo menos frequente acessado
	 */
	public void setLeastFrequentUsedAlgorithm() {
		politicStrategy = PoliticStrategy.leastFrequentUsedAlgorithm();
	}

	/**
	 * Define a política de substituição pelo mais recente acessado
	 */
	public void setLeastRecentUsedAlgorithm() {
		politicStrategy = PoliticStrategy.leastRecentUsedAlgorithm();
	}

	/**
	 * getter wordAmount
	 *
	 * @return wordAmount
	 */
	public int getWordAmount() {
		return wordAmount;
	}

	/**
	 * getter lines
	 *
	 * @return lines
	 */
	public int getTotalLines() {
		return lines;
	}

	/**
	 * getter ways
	 *
	 * @return ways
	 */
	public int getWays() {
		return ways;
	}

	/**
	 * Exibe no console os conjuntos associativos
	 */
	public void printSets() {
		for (Set set : associativeSets) {
			System.out.println(set);
		}
	}

	/**
	 * getter miss
	 *
	 * @return miss
	 */
	public int getMiss() {
		return miss;
	}

	/**
	 * getter hits
	 *
	 * @return hits
	 */
	public int getHits() {
		return hits;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public int getWordSize() {
		return wordSize;
	}

	public int getLines() {
		return lines;
	}

	public int getAssociativeSetSize() {
		return associativeSetSize;
	}

	public int getSetAddrSize() {
		return setAddrSize;
	}

	public int getBlockAddrSize() {
		return blockAddrSize;
	}

	public int getTagAddrSize() {
		return tagAddrSize;
	}

	public int getAddressSize() {
		return Address.ADDRESSSIZE;
	}

	public boolean setup() {
		return setup;
	}

	public List<Set> getAssociativeSets() {
		return associativeSets;
	}

	@Override
	public String toString() {
		return "Configuracao da Cache:\n	Tamanho da cache = " + cacheSize + " Bytes\n" + "	Quantidade de blocos = "
				+ wordAmount + " \n" + "	Tamanho da palavra = " + wordSize + " Bytes\n" + "	Numero de vias = "
				+ ways + "\n" + "	Quantidade de linhas = " + lines + "\n" + "	Quantidade de conjuntos = "
				+ associativeSetSize;
	}

	public void resetValues() {
		hits = 0;
		miss = 0;

		associativeSets = new ArrayList<>(associativeSetSize + 1);
		for (int i = 0; i < associativeSetSize; i++) {
			associativeSets.add(new Set(ways));
		}
	}
}
