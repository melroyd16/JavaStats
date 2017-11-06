package com.asu.ser.render;


import com.asu.ser.MainApp;
import com.asu.ser.Summary;
import com.asu.ser.dto.PackageInfo;
import com.asu.ser.scan.Folder;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class WelcomeController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String osName = System.getProperty("os.name");

        if (osName.equals("Mac OS X")) {
            DirectoryChooser dchooser = new DirectoryChooser();
            File file = dchooser.showDialog(null);
            System.out.print(file.getAbsolutePath());
            beginAnalysis(file, event);
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);


        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            beginAnalysis(file, event);
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }

    private void beginAnalysis(File file, ActionEvent event) throws IOException {
        Summary.linesOfcode = 0;
        Summary.noOfClasses = 0;
        Summary.noOfInterfaces = 0;
        Summary.noOfPackages = 0;
        String folderPath = file.getAbsolutePath();
        Folder f = new Folder();
        List<PackageInfo> packageList = f.scanFolder(folderPath);
        Parent summaryParent = FXMLLoader.load(getClass().getResource("/fxml/ProjectSummary.fxml"));
        Scene summary_scene = new Scene(summaryParent);
        MainApp.sceneStack.push(summary_scene);
        MainApp.mainStage.hide();
        MainApp.currentscene = summary_scene;
        MainApp.mainStage.setScene(summary_scene);
        MainApp.mainStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
}