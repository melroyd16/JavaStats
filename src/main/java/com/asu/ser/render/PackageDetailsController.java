/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.render;

import com.asu.ser.MainApp;
import com.asu.ser.dto.ClassInfo;
import com.asu.ser.dto.InterfaceInfo;
import com.asu.ser.dto.PackageInfo;
import com.asu.ser.scan.Folder;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
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
public class PackageDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label packageName;

    @FXML
    private TableView classTable;
    @FXML
    private TableColumn<ClassInfo, String> className;
    @FXML
    private TableColumn<ClassInfo, String> visibility;
    @FXML
    private TableColumn<ClassInfo, String> inherits;
    //@FXML private TableColumn<ClassInfo, String> interfaces;
    @FXML
    private TableColumn<ClassInfo, Integer> cLinesOfCode;

    public ObservableList<ClassInfo> classes = FXCollections.observableArrayList(Folder.navigationPackage.getClassList());

    @FXML
    private TableView interfaceTable;
    @FXML
    private TableColumn<InterfaceInfo, String> interfaceName;
    @FXML
    private TableColumn<InterfaceInfo, String> iVisibility;
    @FXML
    private TableColumn<InterfaceInfo, Integer> iLinesOfCode;

    public ObservableList<InterfaceInfo> interfaces = FXCollections.observableArrayList(Folder.navigationPackage.getInterfaceList());

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
        packageName.setText(Folder.navigationPackage.getPackageName());

        className.setCellValueFactory(new PropertyValueFactory<ClassInfo, String>("className"));
        visibility.setCellValueFactory(new PropertyValueFactory<ClassInfo, String>("accessModifier"));
        inherits.setCellValueFactory(new PropertyValueFactory<ClassInfo, String>("inheritsFrom"));
        //interfaces.setCellValueFactory(new PropertyValueFactory<ClassInfo, List<String>>("implementsInterface"));
        cLinesOfCode.setCellValueFactory(new PropertyValueFactory<ClassInfo, Integer>("linesOfCode"));
        classTable.setItems(classes);
//        classTable.setFixedCellSize(25);
//        classTable.prefHeightProperty().bind(classTable.fixedCellSizeProperty().multiply(Bindings.size(classTable.getItems()).add(1.3)));
        classTable.setRowFactory(tv -> {
            TableRow<ClassInfo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    ClassInfo clickedRow = row.getItem();
                    try {
                        viewClassDetails(clickedRow, event);
                    } catch (IOException ex) {
                        Logger.getLogger(PackageDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });

        interfaceName.setCellValueFactory(new PropertyValueFactory<InterfaceInfo, String>("interfaceName"));
        iVisibility.setCellValueFactory(new PropertyValueFactory<InterfaceInfo, String>("accessModifier"));
        iLinesOfCode.setCellValueFactory(new PropertyValueFactory<InterfaceInfo, Integer>("linesOfCode"));
        interfaceTable.setItems(interfaces);
//        interfaceTable.setFixedCellSize(25);
//        interfaceTable.prefHeightProperty().bind(classTable.fixedCellSizeProperty().multiply(Bindings.size(classTable.getItems()).add(1.3)));
    }

    private void viewClassDetails(ClassInfo item, MouseEvent event) throws IOException {
        for (ClassInfo c : Folder.navigationPackage.getClassList()) {
            if (c.getClassName().equals(item.getClassName())) {
                Folder.navigationClass = c;
                break;
            }
        }
        Parent summaryParent = FXMLLoader.load(getClass().getResource("/fxml/ClassDetails.fxml"));
        Scene classDetailsScene = new Scene(summaryParent);
        //MainApp.appSceneList.add(classDetailsScene);
        MainApp.sceneStack.push(classDetailsScene);
        MainApp.mainStage.hide();
        MainApp.currentscene = classDetailsScene;
        MainApp.mainStage.setScene(classDetailsScene);
        MainApp.mainStage.show();
    }

}
