package Lsg;

import Lsg.graphics.CSSFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class LearningSoulsGameApplication extends Application{

	private Scene scene;
	private AnchorPane root;
	private int longueur = 1200;
	private int largeur = 800; 
	
	public void start(Stage stage) throws Exception {
		stage.setTitle("Learning Souls Game");
		root = new AnchorPane();
		scene = new Scene(root, longueur, largeur);
		stage.setScene(scene);
		stage.setResizable(false);
		buildUI();
		stage.show();
	}
	
	public void buildUI() {
		scene.getStylesheets().setAll(CSSFactory.getStyleSheet("../css/LSG.css"));
	}
	
	public static void main(String args) throws Exception {
		LearningSoulsGameApplication gameUI = new LearningSoulsGameApplication();
		Stage stage = new Stage();
		gameUI.start(stage);
	}
}
