/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asu.ser.render;

import com.asu.ser.MainApp;
import com.asu.ser.dto.ClassInfo;
import com.asu.ser.dto.FunctionInfo;
import com.asu.ser.dto.InterfaceInfo;
import com.asu.ser.scan.Folder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author melroy
 */
public class ClassDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label className;
    
    @FXML
    private TableView functionTable;
    @FXML
    private TableColumn<FunctionInfo, String> functionName;
    @FXML
    private TableColumn<FunctionInfo, String> visibility;
    @FXML
    private TableColumn<FunctionInfo, Integer> linesOfCode;
    @FXML
    private TableColumn<FunctionInfo, String> returnType;

    public ObservableList<FunctionInfo> functions = FXCollections.observableArrayList(Folder.navigationClass.getFunctionList());
    
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
        className.setText(Folder.navigationClass.getClassName());
        
        functionName.setCellValueFactory(new PropertyValueFactory<FunctionInfo, String>("functionName"));
        visibility.setCellValueFactory(new PropertyValueFactory<FunctionInfo, String>("accessModifier"));
        linesOfCode.setCellValueFactory(new PropertyValueFactory<FunctionInfo, Integer>("linesOfCode"));
        returnType.setCellValueFactory(new PropertyValueFactory<FunctionInfo, String>("returnType"));
        functionTable.setItems(functions);
        functionTable.setFixedCellSize(25);
        functionTable.prefHeightProperty().bind(functionTable.fixedCellSizeProperty().multiply(Bindings.size(functionTable.getItems()).add(1.3)));
    }    
    
}
