package com.rituals.main.controllers;

import com.rituals.main.model.itemBoneProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;

//@ViewController(value = "/fxml/itemPropView.fxml", title = "Item Properties View")
public class ItemPropViewCell extends ListCell<itemBoneProperties> {
    @FXML
    private VBox vboxRoot;

    @FXML
    private TextField boneId;

    @FXML
    private TextField itemRotX;

    @FXML
    private TextField itemRotY;

    @FXML
    private TextField itemRotZ;

    @FXML
    private TextField itemLocX;

    @FXML
    private TextField itemLocY;

    @FXML
    private TextField itemLocZ;

    @FXML
    private TextField itemScaleX;

    @FXML
    private TextField itemScaleY;

    @FXML
    private TextField itemScaleZ;

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(itemBoneProperties item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null){
            setText(null);
            setGraphic(null);
        }else{

            if(mLLoader == null){
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/itemPropView.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            this.boneId.setText(item.boneName);
            itemRotX.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemRotY.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemRotZ.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemLocX.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemLocY.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemLocZ.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemScaleX.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemScaleY.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
            itemScaleZ.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

            itemRotX.setText(String.valueOf(item.rotX));
            itemRotY.setText(String.valueOf(item.rotY));
            itemRotZ.setText(String.valueOf(item.rotZ));

            itemLocX.setText(String.valueOf(item.locX));
            itemLocY.setText(String.valueOf(item.locY));
            itemLocZ.setText(String.valueOf(item.locZ));

            itemScaleX.setText(String.valueOf(item.sizeX));
            itemScaleY.setText(String.valueOf(item.sizeY));
            itemScaleZ.setText(String.valueOf(item.sizeZ));

            setText(null);
            setGraphic(vboxRoot);
        }
    }

}
