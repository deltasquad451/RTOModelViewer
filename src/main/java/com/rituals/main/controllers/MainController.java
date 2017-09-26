package com.rituals.main.controllers;


import com.rituals.main.JMEApp;
import com.rituals.main.controllers.utils.DragResizerXY;
import com.rituals.main.controllers.utils.SceneManager;
import com.rituals.main.model.itemBoneProperties;
import com.rituals.main.model.itemProperties;
import io.datafx.controller.ViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


@ViewController(value = "/fxml/main.fxml", title = "Main Controller")
public class MainController implements Initializable{


    @FXML
    private BorderPane rootNode;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem newItem;

    @FXML
    private MenuItem loadItem;

    @FXML
    private MenuItem saveItem;

    @FXML
    private MenuItem baseModelSelect;

    @FXML
    private MenuItem closeApplication;

    @FXML
    private StackPane imageViewStackPane;

    @FXML
    private ImageView jmeImageView;

    @FXML
    private AnchorPane sideAnchorPane;

    @FXML
    private ListView<String> boneList;

    @FXML
    private ListView<?> animationsList;

    @FXML
    private ListView<itemBoneProperties> itemLayoutList;

    @FXML
    private Button addNewItemLayout;

    static ObservableList<itemBoneProperties> itemObservableList = FXCollections.observableArrayList();

//    static {
//        itemObservableList.addAll(new itemBoneProperties("Bone1",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone2",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone3",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone4",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone5",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone6",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone7",1,1,1,2,2,2,3,3,3),
//                new itemBoneProperties("Bone8",1,1,1,2,2,2,3,3,3));
//    }

    private File itemModel = null;
    public TextField itemPath;
    public TextField baseModelPath;
    public itemProperties itemProperties;
    public SceneManager manager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jmeImageView.fitHeightProperty().bind(imageViewStackPane.heightProperty());
        jmeImageView.fitWidthProperty().bind(imageViewStackPane.widthProperty());
        DragResizerXY.makeResizable(sideAnchorPane);

        newItem.setOnAction(this::newItemModel);
        boneList.setOnMouseClicked(this::boneListElementOnSelect);
        jmeImageView.setOnMouseEntered(this::imageViewMouseEntered);

        this.itemLayoutList.setEditable(true);
        this.itemLayoutList.setItems(itemObservableList);
        this.itemLayoutList.setCellFactory(e -> new ItemPropViewCell());

        this.itemLayoutList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(observable+" "+oldValue+" "+newValue);
        });

        this.addNewItemLayout.setOnAction(this::createNewLayoutItem);
        this.addNewItemLayout.setDisable(true);

        this.saveItem.setOnAction(this::saveItem);

    }

    public ImageView getJmeImageView() {
        return jmeImageView;
    }

    public void imageViewMouseEntered(MouseEvent event){
        JMEApp app = JMEApp.getInstance();
        jmeImageView.requestFocus();
    }

    public void boneListElementOnSelect(MouseEvent event){
        JMEApp app = JMEApp.getInstance();
        String value = this.boneList.getSelectionModel().getSelectedItem();
        if(value!=null){
            app.changeBonePonter(value);
        }

    }

    @FXML
    public void loadBaseModel(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Base Skeleton Model");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("j3o","*.j3o"));
        File baseModelo = chooser.showOpenDialog(imageViewStackPane.getScene().getWindow());
        if(baseModelo != null){
            this.manager.baseModelFile = baseModelo;
            baseModelPath.setText(baseModelo.toString());
            JMEApp app = JMEApp.getInstance();
            app.addBaseModel(baseModelo.getName(),baseModelo.getParent());
            this.boneList.getItems().addAll(app.getBones());
        }
    }


    public void loadModel(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Item Model");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("j3o","*.j3o"));
        File itemModelFile = chooser.showOpenDialog(imageViewStackPane.getScene().getWindow());
        if(itemModelFile != null){
            System.out.println(itemModelFile);
            this.itemModel = itemModelFile;
            this.itemPath.setText(this.itemModel.toString());
        }
    }

    @FXML
    public void newItemModel(ActionEvent event){
        Stage owner = (Stage)imageViewStackPane.getScene().getWindow();

        Wizard wizard = new Wizard(owner);
        wizard.setTitle("New Item");

        this.manager = new SceneManager();
        this.manager.itemModelFile = itemModel;

        // Page 1
        WizardPane page1 = new WizardPane() {
            ValidationSupport vs = new ValidationSupport();
            {
                vs.initInitialDecoration();

                int row = 0;

                GridPane page1Grid = new GridPane();
                page1Grid.setVgap(10);
                page1Grid.setHgap(10);

                page1Grid.add(new Label("Item Name:"), 0, row);
                TextField itemName = createTextField("ItemName");
//                vs.registerValidator(itemName, Validator.createEmptyValidator("Item name is required"));
                page1Grid.add(itemName, 1, row++);

                page1Grid.add(new Label("Item Model Path:"), 0, row);
                MainController.this.itemPath = createTextField("itemModelLoc");
//                vs.registerValidator(itemPath,true,Validator.createEmptyValidator("Item path is required"));
                page1Grid.add(itemPath , 1, row++);

                Button itemBrowse = createBut("itemBrowse","Browse");
                itemBrowse.setOnAction(MainController.this::loadModel);
                page1Grid.add(itemBrowse,0,row);

                setContent(page1Grid);
                Platform.runLater(() -> {
                    vs.registerValidator(itemName, Validator.createEmptyValidator("Item name is required"));
                    vs.registerValidator(itemPath,true,Validator.createEmptyValidator("Item path is required"));
                });
            }
            @Override
            public void onEnteringPage(Wizard wizard) {
                wizard.invalidProperty().unbind();
                wizard.invalidProperty().bind(vs.invalidProperty());
            }

        };

        // --- page 2
        final WizardPane page2 = new WizardPane() {
            ValidationSupport vs = new ValidationSupport();
            {
                String itemName = (String) wizard.getSettings().get("ItemName");
                String itemLoc = (String) wizard.getSettings().get("itemModelLoc");

                System.out.println(itemName+" "+itemLoc);

                int row = 0;

                GridPane page2Grid = new GridPane();
                page2Grid.add(new Label("Base Model Path: "), 0, row);
                MainController.this.baseModelPath = createTextField("baseModelLoc");
                page2Grid.add(baseModelPath,1,row++);

                Button baseModel = createBut("baseModBrowse", "Browse");
                baseModel.setOnAction(MainController.this::loadBaseModel);
                page2Grid.add(baseModel,0,row++);

                setContent(page2Grid);
                Platform.runLater(() -> {
                    vs.registerValidator(baseModelPath,true,Validator.createEmptyValidator("Base Model Path is required"));
                });
            }

            @Override
            public void onEnteringPage(Wizard wizard) {
                wizard.invalidProperty().unbind();
                wizard.invalidProperty().bind(vs.invalidProperty());

            }
        };
        page2.setHeaderText("New Item Details:");

        // create wizard
        wizard.setFlow(new Wizard.LinearFlow(page1,page2));

        // show wizard and wait for response
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                System.out.println("Wizard finished, settings: " + wizard.getSettings().keySet());
                this.manager.itemName = (String) wizard.getSettings().get("ItemName");
                addNewItemLayout.setDisable(false);
                JMEApp app = JMEApp.getInstance();
                app.loadItemModel(itemModel.getName(),itemModel.getParent());
                this.saveItem.setDisable(false);
            }
        });
    }



    public void createNewLayoutItem(ActionEvent event){
        itemBoneProperties p = new itemBoneProperties();
        itemObservableList.add(p);

    }

    public void saveItem(ActionEvent event){
        this.manager.saveLayout(itemObservableList);
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setId(id);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        return textField;
    }
    private Button createBut(String id, String value){
        Button button = new Button();
        button.setText(value);
        button.setId(id);
        GridPane.setHgrow(button,Priority.ALWAYS);
        return button;
    }
}
