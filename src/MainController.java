

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
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
    private Label lblAddressFileName;
    
    @FXML
    private Label lblCacheConfigFile;
    
    @FXML
    private Label lblMemConfigFile;

    @FXML
    private Button btnLoadCacheConfigFile;

    @FXML
    private Button btnLoadMemConfigFile;
    
    @FXML
    private Button btnLoadAddresses;
    
    @FXML
    private TableView<MemoryLevelDAO> tableMemorys;

    @FXML
    private TableColumn<String, String> colId;

    @FXML
    private TableColumn<String, Integer> colHits;

    @FXML
    private TableColumn<String, Float> colHitRate;

    @FXML
    private TableColumn<String, Integer> colMiss;

    @FXML
    private TableColumn<String, Float> colMissRate;
    
    private Manager manager;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		manager = new Manager();
		Console.setMainController(this);
		btnSetAlgorithm.setItems(FXCollections.observableArrayList("Randômico", "Least Frequent Used (LFU)", "Least Recent Used (LRU)"));
		btnSetAlgorithm.getSelectionModel().selectFirst();
		
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colHits.setCellValueFactory(new PropertyValueFactory<>("hits"));
        colHitRate.setCellValueFactory(new PropertyValueFactory<>("hitRate"));
        colMiss.setCellValueFactory(new PropertyValueFactory<>("miss"));
        colMissRate.setCellValueFactory(new PropertyValueFactory<>("missRate"));
		
		btnConfigCache.setOnAction(action -> setCacheConfig());
		btnAddMemory.setOnAction(action -> addMemory());
		btnLoadProgram.setOnAction(action -> loadProgram());
		btnStartSimulation.setOnAction(action -> startSimulation());
		btnLoadCacheConfigFile.setOnAction(action -> loadCacheConfigFile());
		btnLoadMemConfigFile.setOnAction(action -> loadMemConfigFile());
		btnSetAlgorithm.setOnAction(action -> selectAlgorithm());
		btnLoadAddresses.setOnAction(action -> loadAddresses());
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
    		
    		if(manager.simulatorSetup())
			{
				lblSimulationState.setTextFill(Color.GREEN);
				lblSimulationState.setText("Simulação configurada");
			}
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
			
			if(manager.simulatorSetup())
			{
				lblSimulationState.setTextFill(Color.GREEN);
				lblSimulationState.setText("Simulação configurada");
			}
		}catch(NumberFormatException ex)
    	{
    		Console.log("Algum campo está preenchido incorretamente.");
    	}catch(RuntimeException ex)
    	{
    		ex.printStackTrace();
    		Console.log("Erro ao adicionar a memória.\n");
    	}
	}
	
	
	private void startSimulation()
	{
		try{
			if(manager.simulatorSetup())
			{
				manager.startSimulation();
				Console.log("Simulação Realizada com sucesso!");
				
				SimulationResult result = manager.getSimulationResult();
				
				lblTotalAddress.setText(String.valueOf(result.getTotalAddressesProcessed()));
				lblCacheHits.setText(String.valueOf(result.getCacheHits()));
				lblCacheMiss.setText(String.valueOf(result.getCacheMiss()));
				lblCacheHitRate.setText(String.format("%.2f", result.getCacheHitRate()*100.f) + "%");
				lblCacheMissRate.setText(String.format("%.2f", result.getCacheMissRate()*100.f) + "%");
				

				lblTimeAverage.setText(String.format("%.2f", result.getTimeAverage()) + " ut");
				lblTotalCost.setText(String.valueOf(result.getTotalTime()) + " ut");
				
				tableMemorys.setItems(FXCollections.observableArrayList(manager.getMemoryLevelsDAO()));
				
			}else
			{
				Console.log("A simulação não foi completamente configurada para ser iniciada.");
			}
		}catch(RuntimeException ex)
		{
			Console.log("Erro ao começar a simulação!\nERRO = "+ex.getMessage());
		}
	}
	
	private void loadProgram()
	{
		try {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
            if (file.getName().endsWith(".txt")) {
            	
            	manager.setProgram(file.getName());
            	Console.log("arquivo de endereços criado com sucesso!");

            	lblAddressFileName.setTextFill(Color.GREEN);
            	lblAddressFileName.setText("enderecos_"+file.getName());
            	
            	if(manager.simulatorSetup())
    			{
    				lblSimulationState.setTextFill(Color.GREEN);
    				lblSimulationState.setText("Simulação configurada");
    			}
            } else {
                Console.log("Arquivo selecionado não suportado!");
            }

        } catch (Exception ex) {
        	Console.log("Erro ao carregar o programa");
        }
	}
	
	private void loadCacheConfigFile()
	{
		try {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
            if (file.getName().endsWith(".txt")) {
            	
            	manager.loadCacheConfig(file.getName());
            	Console.log("Cache configurada com sucesso!");

            	lblCacheConfigFile.setTextFill(Color.GREEN);
            	lblCacheConfigFile.setText(file.getName());
            	
            	CacheDAO cacheDAO = manager.getCacheDAO();
        		
            	txtCacheSize.setText(String.valueOf(cacheDAO.getCacheSize()));
            	txtBlockAmount.setText(String.valueOf(cacheDAO.getBlockAmount()));
            	txtWordSize.setText(String.valueOf(cacheDAO.getWordSize()));
            	txtWays.setText(String.valueOf(cacheDAO.getWays()));
        		lblLines.setText(String.valueOf(cacheDAO.getLines()));
        		lblSetAmount.setText(String.valueOf(cacheDAO.getAssociativeSetSize()));
        		String addrFormat = "[ Tag = "+cacheDAO.getTagAddrSize()+
        							", Conjunto = "+cacheDAO.getSetAddrSize()+
        							", Bloco = "+cacheDAO.getBlockAddrSize()+" ]";
        		lblAddrFormat.setText(addrFormat);
        		lblCacheStatus.setText("Configurada!");
        		
        		if(manager.simulatorSetup())
    			{
    				lblSimulationState.setTextFill(Color.GREEN);
    				lblSimulationState.setText("Simulação configurada");
    			}
            } else {
                Console.log("Arquivo selecionado não suportado!");
            }

        } catch (Exception ex) {
        	Console.log("Erro ao carregar a configuração!");
        }
	}
	
	private void loadMemConfigFile()
	{
		try {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
            if (file.getName().endsWith(".txt")) {
            	
            	manager.loadMemConfig(file.getName());
            	Console.log("Hierarquia de memória configurada com sucesso!");
            	lblMemConfigFile.setTextFill(Color.GREEN);
            	lblMemConfigFile.setText(file.getName());
            	
    			listMemHierarchy.setItems(FXCollections.observableArrayList(manager.getMemoryLevelsDAO()));
    			
    			if(manager.simulatorSetup())
    			{
    				lblSimulationState.setTextFill(Color.GREEN);
    				lblSimulationState.setText("Simulação configurada");
    			}
            } else {
                Console.log("Arquivo selecionado não suportado!");
            }

        } catch (Exception ex) {
        	Console.log("Erro ao carregar a configuração!");
        }
	}
	
	private void selectAlgorithm()
	{
		String algorithm = btnSetAlgorithm.getSelectionModel().getSelectedItem();
		
		manager.setAlgorithm(algorithm);
		
		Console.log("Algoritmo "+algorithm+" configurado com sucesso!");
	}
	
	private void loadAddresses()
	{
		try {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(new Stage());
            if (file.getName().endsWith(".txt")) {
            	
            	manager.loadAddresses(file.getName());
            	
            	Console.log("Endereços carregados com sucesso!");
            	
            	lblAddressFileName.setTextFill(Color.GREEN);
            	lblAddressFileName.setText(file.getName());
            	
    			if(manager.simulatorSetup())
    			{
    				lblSimulationState.setTextFill(Color.GREEN);
    				lblSimulationState.setText("Simulação configurada");
    			}
            } else {
                Console.log("Arquivo selecionado não suportado!");
            }

        } catch (Exception ex) {
        	Console.log("Erro ao carregar a configuração!");
        }
	}

	public void updateConsole(String text) {
		txtConsole.setText(text);
	}
    
}
