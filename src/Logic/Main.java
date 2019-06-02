package Logic;

public class Main {
	public static void main(String[] args) {
		FileGenerator fileGenerator = FileGenerator.getInstance();
		fileGenerator.createAddressFile("Programa_1.txt", "enderecos_1.txt");
		fileGenerator.createAddressFile("Programa_2.txt", "enderecos_2.txt");

		Simulator simulator = new Simulator();		
		simulator.configSimulation("enderecos_1.txt", "Cache_config.txt", "Hierarquia_de_memoria.txt");
		simulator.startSimulation();
	}
}
