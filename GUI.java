package maze;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends HBox {

	private Pane maze;
	private VBox button, combo, menu;
	private Button read, find, findAll, write, quit;
	private Label mazeSelection;
	private ComboBox<String> mazeSelectionBox;
	private String mazesStringArray[];
	private HBox display;
	private String print;
	private FileIO mazeFile;
	private Scanner mazeScanner;
	private ArrayList<Maze> mazes;

	private int count;

	public GUI(Stage s) {

		super();

		mazeFile = new FileIO();

		mazes = new ArrayList<Maze>();

		mazeSelection = new Label("Select Maze");

		mazeSelection.setStyle("-fx-text-fill: black;");
		mazeSelection.setPrefSize(150, 50);

		mazeSelectionBox = new ComboBox<String>();
		mazeSelectionBox.setPrefSize(150, 50);

		mazeSelectionBox.setOnAction(event -> {

			String select = mazeSelectionBox.getValue();
			int mazeNumber = Integer.parseInt(select.substring(4));

			mazes.get(mazeNumber - 1).findPath();
			makeNewBlocks(mazes.get(mazeNumber - 1));
		});

		read = new Button("Read Mazes");
		read.setStyle("-fx-background-color:DEEPSKYBLUE;");
		read.setPrefSize(150, 50);

		read.setOnAction(event -> {

			getFile(s);
			mazeSelectionBox.setValue(mazesStringArray[0]);

		});

		find = new Button("Find Path (This Maze)");
		find.setStyle("-fx-background-color:DEEPSKYBLUE;");
		find.setPrefSize(150, 50);

		find.setOnAction(event -> {

			String selected = mazeSelectionBox.getValue();
			int mazeNumber = Integer.parseInt(selected.substring(4));

			mazes.get(mazeNumber - 1).findPath();

			if (mazes.get(mazeNumber - 1).pathFound() == true) {

				showPath(mazes.get(mazeNumber - 1));

			}

			else {

				JOptionPane.showInputDialog(null, "No path exists through the maze", JOptionPane.OK_OPTION);
			}

			print = mazes.get(mazeNumber - 1).asText();

		});

		findAll = new Button("Find Paths(All Mazes");
		findAll.setStyle("-fx-background-color:DEEPSKYBLUE;");
		findAll.setPrefSize(150, 50);

		findAll.setOnAction(event -> {

			findPathAll();

			for (int i = 0; i < mazes.size(); i++) {

				print += (mazes.get(i).asText());

			}

		});

		write = new Button("Write Mazes");
		write.setStyle("-fx-background-color:DEEPSKYBLUE;");
		write.setPrefSize(150, 50);

		write.setOnAction(event -> {

			mazeFile.writeFile(print);
			print = "";

		});

		EventHandler<ActionEvent> enableWrite = event -> {

			write.setDisable(false);
		};

		findAll.addEventHandler(ActionEvent.ACTION, enableWrite);
		find.addEventHandler(ActionEvent.ACTION, enableWrite);

		quit = new Button("Quit");
		quit.setStyle("-fx-background-color:DEEPSKYBLUE;");
		quit.setPrefSize(150, 50);

		quit.setOnAction(event -> {

			System.exit(0);

		});

		button = new VBox(0, read, find, findAll, write, quit);
		button.setAlignment(Pos.CENTER);

		combo = new VBox(0, mazeSelection, mazeSelectionBox);
		combo.setAlignment(Pos.CENTER);

		maze = new Pane();
		maze.setPrefSize(900, 900);

		menu = new VBox(500, combo, button);
		menu.setPrefSize(250, 200);
		menu.setStyle("-fx-background-color:LIGHTGRAY;");

		display = new HBox(0, maze, menu);

	}

	public void getFile(Stage s) {
		// TODO Auto-generated method stub

		FileChooser fileSelection = new FileChooser();

		File file = fileSelection.showOpenDialog(s);

		mazeScanner = mazeFile.readFile(file);

		getMazes(mazeScanner);

	}

	public void getMazes(Scanner s) {
		// TODO Auto-generated method stub

		while (s.hasNextInt()) {

			mazes.add(new Maze(s));
			count++;

		}
	}

	public HBox getGUIdispplay() {

		return display;

	}

	public void findPathAll() {
		// TODO Auto-generated method stub

		for (int i = 0; i < mazes.size(); i++) {

			mazes.get(i).findPath();

		}
	}

	public void addMazetoList(int n) {

		mazesStringArray = new String[n];

		for (int i = 0; i < n; i++) {

			mazesStringArray[i] = "Maze" + (i + 1);

		}

		mazeSelectionBox.getItems().addAll(mazesStringArray);
	}

	public void makeNewBlocks(Maze m) {
		// TODO Auto-generated method stub
		Cell tempCell = null;

		GridPane mazeBlocks = new GridPane();

		maze.getChildren().clear();
		mazeBlocks.getChildren().clear();

		for (int i = 0; i < m.getH(); i++) {
			for (int j = 0; j < m.getW(); j++) {

				tempCell = m.getCell(i, j);
				StackPane block = new StackPane();
				String tempStyle = "";

				if (tempCell.getNeighbors(0) == true) {

					tempStyle += "hidden ";

				}

				else {

					tempStyle += "hidden ";

				}

				if (tempCell.getNeighbors(2) == true) {

					tempStyle += "hidden ";

				}

				else {

					tempStyle += "solid ";

				}

				if (tempCell.getNeighbors(3) == true) {

					tempStyle += "hidden ";

				}

				else {

					tempStyle += "solid ";

				}

				block.setStyle("-fx-border-style:" + tempStyle + ";");
				block.setPrefSize(30, 30);

				if (tempCell.getWalkable() != true) {

					block.setStyle("-fx-background-color: black");

				}

				if (tempCell.getEnd() == true) {

					Text end = new Text("E");
					block.getChildren().add(end);

				}

				if (tempCell.getStart() == true) {

					Text start = new Text("S");
					block.getChildren().add(start);
				}

				mazeBlocks.add(block, j, i);

			}

		}
	}

	private void showPath(Maze n) {
		// TODO Auto-generated method stub

		Cell tempCell = null;
		
		GridPane mazeBlocks = new GridPane();
		
		maze.getChildren().clear();
		mazeBlocks.getChildren().clear();
		
		for(int i = 0; i < n.getH(); i++) {
			for(int j = 0; j < n.getW(); j++) {
				
				tempCell = n.getCell(i, j);
				
				Circle circle = new Circle(10);
				circle.setFill(Color.RED);
				
				StackPane dot = new StackPane(circle);
				StackPane block = new StackPane();
				
				String tempStyle = "";
				
				if(tempCell.getNeighbors(0) == true) {
					
					tempStyle += "hidden ";
					
				}
				
				else {
					
					tempStyle += "solid ";
					
				}
				
				if(tempCell.getNeighbors(2) == true) {
					
					tempStyle += "hidden ";
					
				}
				
				else {
					
					tempStyle += "solid ";
					
				}
				
				if(tempCell.getNeighbors(1) == true) {
					
					tempStyle += "hidden ";
					
				}
				
				else {
					
					tempStyle += "solid ";
					
				}
				
				block.setStyle("-fx-border-style:" + tempStyle + ";");
				block.setPrefSize(30, 30);
				
				if(tempCell.getWalkable() != true) {
					
					block.setStyle("-fx-background-color: black");
					
				}
				
				if(tempCell.getOnPath() == true) {
					if(tempCell.getEnd() == true) {
						
						Text end = new Text("E");
						
						block.getChildren().addAll(dot, end);
						
					}
					
					else if(tempCell.getStart() == true){
						
						Text start = new Text("S");
						
						block.getChildren().addAll(dot, start);
						
					}
					
					else {
						
						block.getChildren().add(dot);
						
					}
				}
				
				mazeBlocks.add(block, j, i);
				
				tempStyle = "";
			}
		}
		
		maze.getChildren().add(mazeBlocks);
	}

}
