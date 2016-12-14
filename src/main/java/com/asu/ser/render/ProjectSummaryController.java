/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.render;

import com.asu.ser.MainApp;
import com.asu.ser.Summary;
import com.asu.ser.dto.PackageInfo;
import com.asu.ser.scan.Folder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author melroy
 */
public class ProjectSummaryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label noOfPackages;

    @FXML
    private Label noOfClasses;

    @FXML
    private Label noOfInterfaces;

    @FXML
    private Label linesOfCode;

    @FXML
    private TableView table;
    @FXML
    private TableColumn<PackageInfo, String> packageName;
    @FXML
    private TableColumn<PackageInfo, Integer> tNoOfClasses;
    @FXML
    private TableColumn<PackageInfo, Integer> tNoOfInterfaces;
    @FXML
    private TableColumn<PackageInfo, Integer> tLinesOfCode;

    public ObservableList<PackageInfo> packages = FXCollections.observableArrayList(Folder.packageList);

    @FXML
    private void navigateBack(ActionEvent event) throws IOException {
        //Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainApp.mainStage.hide();
        MainApp.sceneStack.pop();
        //MainApp.currentscene = MainApp.appSceneList.get(0);
        MainApp.currentscene = (Scene)MainApp.sceneStack.peek();
        MainApp.mainStage.setScene(MainApp.currentscene);
        MainApp.mainStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        noOfPackages.setText(Summary.noOfPackages + "");
        noOfClasses.setText(Summary.noOfClasses + "");
        noOfInterfaces.setText(Summary.noOfInterfaces + "");
        linesOfCode.setText(Summary.linesOfcode + "");

        packageName.setCellValueFactory(new PropertyValueFactory<PackageInfo, String>("packageName"));
        tNoOfClasses.setCellValueFactory(new PropertyValueFactory<PackageInfo, Integer>("noOfClasses"));
        tNoOfInterfaces.setCellValueFactory(new PropertyValueFactory<PackageInfo, Integer>("noOfInterfaces"));
        tLinesOfCode.setCellValueFactory(new PropertyValueFactory<PackageInfo, Integer>("linesOfCode"));
        table.setItems(packages);
//        table.setFixedCellSize(25);
//        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.25)));
        table.setRowFactory(tv -> {
            TableRow<PackageInfo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    PackageInfo clickedRow = row.getItem();
                    try {
                        viewPackageDetails(clickedRow, event);
                    } catch (IOException ex) {
                        Logger.getLogger(ProjectSummaryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
    }

    private void viewPackageDetails(PackageInfo item, MouseEvent event) throws IOException {
        for (PackageInfo p : Folder.packageList) {
            if (p.getPackageName().equals(item.getPackageName())) {
                Folder.navigationPackage = p;
                break;
            }
        }
        Parent summaryParent = FXMLLoader.load(getClass().getResource("/fxml/PackageDetails.fxml"));
        Scene packageDetailsScene = new Scene(summaryParent);
        //MainApp.appSceneList.add(packageDetailsScene);
        MainApp.sceneStack.push(packageDetailsScene);
        MainApp.mainStage.hide();
        MainApp.currentscene = packageDetailsScene;
        MainApp.mainStage.setScene(packageDetailsScene);
        MainApp.mainStage.show();
    }

}
