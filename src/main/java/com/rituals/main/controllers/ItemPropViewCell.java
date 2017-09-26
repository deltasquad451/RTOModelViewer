package com.rituals.main.controllers;

import com.rituals.main.fxProperties.ObservabeItemProp;
import com.rituals.main.model.itemBoneProperties;
import io.datafx.controller.ViewController;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@ViewController(value = "/fxml/itemPropView.fxml", title = "Item Properties View")
public class ItemPropViewCell extends ListCell<itemBoneProperties> implements Initializable {
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
    private TextField itemRotW;

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

    private itemBoneProperties item;
    private ObservabeItemProp itemObservable;

    Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(item);
    }

    private void setUpConnections(){
        this.boneId.setText(item.boneName);
        itemRotX.setText(String.valueOf(item.rotX));
        itemRotY.setText(String.valueOf(item.rotY));
        itemRotZ.setText(String.valueOf(item.rotZ));
        itemRotW.setText(String.valueOf(item.rotW));

        itemLocX.setText(String.valueOf(item.locX));
        itemLocY.setText(String.valueOf(item.locY));
        itemLocZ.setText(String.valueOf(item.locZ));

        itemScaleX.setText(String.valueOf(item.sizeX));
        itemScaleY.setText(String.valueOf(item.sizeY));
        itemScaleZ.setText(String.valueOf(item.sizeZ));

        itemObservable = new ObservabeItemProp(item);

        itemObservable.boneName.bindBidirectional(this.boneId.textProperty());

        Bindings.bindBidirectional(itemRotX.textProperty(),itemObservable.rotX, new NumberStringConverter());
        Bindings.bindBidirectional(itemRotY.textProperty(),itemObservable.rotY, new NumberStringConverter());
        Bindings.bindBidirectional(itemRotZ.textProperty(),itemObservable.rotZ, new NumberStringConverter());
        Bindings.bindBidirectional(itemRotW.textProperty(), itemObservable.rotW, new NumberStringConverter());

        Bindings.bindBidirectional(itemLocX.textProperty(),itemObservable.locX, new NumberStringConverter());
        Bindings.bindBidirectional(itemLocY.textProperty(),itemObservable.locY, new NumberStringConverter());
        Bindings.bindBidirectional(itemLocZ.textProperty(),itemObservable.locZ, new NumberStringConverter());

        Bindings.bindBidirectional(itemScaleX.textProperty(),itemObservable.sizeX, new NumberStringConverter());
        Bindings.bindBidirectional(itemScaleY.textProperty(),itemObservable.sizeY, new NumberStringConverter());
        Bindings.bindBidirectional(itemScaleZ.textProperty(),itemObservable.sizeZ, new NumberStringConverter());



        itemRotX.setTextFormatter(floatTextFormatter(item.rotX));
        itemRotY.setTextFormatter(floatTextFormatter(item.rotY));
        itemRotZ.setTextFormatter(floatTextFormatter(item.rotZ));
        itemRotW.setTextFormatter(floatTextFormatter(item.rotW));

        itemLocX.setTextFormatter(floatTextFormatter(item.locX));
        itemLocY.setTextFormatter(floatTextFormatter(item.locY));
        itemLocZ.setTextFormatter(floatTextFormatter(item.locZ));

        itemScaleX.setTextFormatter(floatTextFormatter(item.sizeX));
        itemScaleY.setTextFormatter(floatTextFormatter(item.sizeY));
        itemScaleZ.setTextFormatter(floatTextFormatter(item.sizeZ));
    }

    private TextFormatter<Float> floatTextFormatter(float defaultValue){
        TextFormatter<Float> textFormatter = new TextFormatter<Float>(new FloatStringConverter(), defaultValue,
                change -> {
                    String newText = change.getControlNewText() ;
                    if (validDoubleText.matcher(newText).matches()) {
                        return change ;
                    } else return null ;
                });
        return textFormatter;
    }



    @Override
    protected void updateItem(itemBoneProperties item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null){
            setText(null);
            setGraphic(null);
        }else{
            if(mLLoader == null){
                System.out.println("Called");
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/itemPropView.fxml"));
                mLLoader.setController(this);

                try {

                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            this.item = item;
            setUpConnections();
            setText(null);
            setGraphic(vboxRoot);
        }
    }

}
