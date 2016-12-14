package com.asu.ser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    
    public static Stage mainStage;
    public static Scene currentscene;
    public static Stack sceneStack = new Stack();
    //public static List<Scene> appSceneList = new ArrayList<Scene>();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/WelcomeScene.fxml"));
        
        Scene scene = new Scene(root);
        currentscene = scene;
        //appSceneList.add(currentscene);
        sceneStack.push(currentscene);
        mainStage = stage;
        scene.getStylesheets().add("/styles/Styles.css");
        
        mainStage.setTitle("Java Stats");
        mainStage.setScene(currentscene);
        mainStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}