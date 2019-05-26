
public class Main {
	public static void main(String[] args) {
		FileGenerator fileGenerartor = new FileGenerator();
		fileGenerartor.CreateAddressFile("enderecos.txt");
		
		Simulator simulator = new Simulator();
		
		simulator.ConfigSimulation("enderecos.txt", "Cache_config.txt", "Hierarquia_de_memoria.txt");
		
	}
}
