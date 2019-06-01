

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController implements Initializable{

    @FXML
    private TextField txtCacheSize;

    @FXML
    private TextField txtBlockAmount;

    @FXML
    private TextField txtWordSize;

    @FXML
    private TextField txtWays;

    @FXML
    private Label lblLines;

    @FXML
    private Label lblSetAmount;

    @FXML
    private Label lblAddrFormat;

    @FXML
    private Button btnConfigCache;

    @FXML
    private Label lblCacheStatus;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtProb;

    @FXML
    private Button btnAddMemory;

    @FXML
    private ListView<MemoryLevelDAO> listMemHierarchy;

    @FXML
    private Label lblTotalAddress;

    @FXML
    private Label lblCacheHits;

    @FXML
    private Label lblCacheHitRate;

    @FXML
    private Label lblCacheMiss;

    @FXML
    private Label lblCacheMissRate;

    @FXML
    private ListView<?> listMemorysResult;

    @FXML
    private Label lblTimeAverage;

    @FXML
    private Label lblTotalCost;

    @FXML
    private Button btnStartSimulation;

    @FXML
    private ChoiceBox<String> btnSetAlgorithm;

    @FXML
    private Button btnCreateNewProgram;

    @FXML
    private Button btnLoadProgram;

    @FXML
    private Label lblSimulationState;

    @FXML
    private TextArea txtConsole;

    @FXML
    private Label txtAddressFileName;
    
    private Manager manager;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		manager = new Manager();
		Console.setMainController(this);
		btnSetAlgorithm.setItems(FXCollections.observableArrayList("Randômico", "Least Frequent Used (LFU)", "Least Recent Used (LRU)"));
		
		btnConfigCache.setOnAction(action -> setCacheConfig());
		btnAddMemory.setOnAction(action -> addMemory());
		btnLoadProgram.setOnAction(action -> loadProgram());
	}
    
    private void setCacheConfig()
    {
    	try
    	{
    		int cacheSize = Integer.parseInt(txtCacheSize.getText());
    		int blockAmount = Integer.parseInt(txtBlockAmount.getText());
    		int wordSize = Integer.parseInt(txtWordSize.getText());
    		int ways = Integer.parseInt(txtWays.getText());
    		
    		manager.setCacheConfig(cacheSize, blockAmount, wordSize, ways);
    		
    		CacheDAO cacheDAO = manager.getCacheDAO();
    		
    		lblLines.setText(String.valueOf(cacheDAO.getLines()));
    		lblSetAmount.setText(String.valueOf(cacheDAO.getAssociativeSetSize()));
    		String addrFormat = "[ Tag = "+cacheDAO.getTagAddrSize()+
    							", Conjunto = "+cacheDAO.getSetAddrSize()+
    							", Bloco = "+cacheDAO.getBlockAddrSize()+" ]";
    		lblAddrFormat.setText(addrFormat);
    		lblCacheStatus.setText("Configurada!");
    		
    		Console.log("Cache configurada com sucesso.");
    		
    		return;
    		
    	}catch(NumberFormatException ex)
    	{
    		Console.log("Algum campo está preenchido incorretamente.");
    	}catch(RuntimeException ex)
    	{
    		Console.log("Erro ao configurar a cache.\n");
    	}
    }
	
	//Implementar verificação se a memoria a ser inserida possui atributos maiores que o anterior
	private void addMemory()
	{
		try
		{
			
			String id = txtId.getText();
			int cost = Integer.parseInt(txtCost.getText());
			int prob = Integer.parseInt(txtProb.getText());
			
			manager.addMemoryLevel(id, cost, prob);
			listMemHierarchy.setItems(FXCollections.observableArrayList(manager.getMemoryLevelsDAO()));
			
		}catch(NumberFormatException ex)
    	{
    		Console.log("Algum campo está preenchido incorretamente.");
    	}catch(RuntimeException ex)
    	{
    		ex.printStackTrace();
    		Console.log("Erro ao adicionar a memória.\n");
    	}
	}
	
	
	private void loadProgram()
	{
		try {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
            String dir = System.getProperty("user.dir");
            if (file.getName().endsWith(".txt")) {
            	
            	manager.setProgram(file.getName());
            	
            } else {
                Console.log("Arquivo selecionado não suportado!");
            }
            
           

        } catch (Exception ex) {
        	Console.log("Erro ao carregar o programa");
        }
	}

	public void updateConsole(String text) {
		txtConsole.setText(text);
	}
    
}
