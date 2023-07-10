package maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Project3 extends Application{
	
	private GUI genMazes;
	
	private Scene scene;

	public static void main(String[] args) {
		
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		genMazes = new GUI(primaryStage);
		
		scene = new Scene(genMazes.getGUIdispplay());
		
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Mazes");
		primaryStage.setResizable(true);
		primaryStage.show();
	}

}
