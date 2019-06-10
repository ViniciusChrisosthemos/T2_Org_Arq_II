import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainController;

public class StartApplication extends Application {

	private static final int WIDTHSCREEN = 1068;
	private static final int HEIGHTSCREEN = 594;

	@Override
	public void start(Stage window) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Simulator.fxml"));
		loader.setController(new MainController());
		Parent root = loader.load();

		Scene scene = new Scene(root, WIDTHSCREEN, HEIGHTSCREEN);

		window.setTitle("Simulador de Memória Cache");
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
