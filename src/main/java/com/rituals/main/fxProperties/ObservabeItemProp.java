package com.rituals.main.fxProperties;

import com.rituals.main.JMEApp;
import com.rituals.main.model.itemBoneProperties;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservabeItemProp {

    public itemBoneProperties properties;

    public final StringProperty boneName = new SimpleStringProperty();

    public final FloatProperty rotX = new SimpleFloatProperty(0f);
    public final FloatProperty rotY = new SimpleFloatProperty(0f);
    public final FloatProperty rotZ = new SimpleFloatProperty(0f);
    public final FloatProperty rotW = new SimpleFloatProperty(0f);

    public final FloatProperty locX = new SimpleFloatProperty(0f);
    public final FloatProperty locY = new SimpleFloatProperty(0f);
    public final FloatProperty locZ = new SimpleFloatProperty(0f);

    public final FloatProperty sizeX = new SimpleFloatProperty(0f);
    public final FloatProperty sizeY = new SimpleFloatProperty(0f);
    public final FloatProperty sizeZ = new SimpleFloatProperty(0f);

    public ObservabeItemProp() {
        JMEApp app = JMEApp.getInstance();
        this.boneName.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.rotX.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.rotY.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.rotZ.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.rotW.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });

        this.locX.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.locY.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.locZ.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });

        this.sizeX.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.sizeY.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
        this.sizeZ.addListener((observable, oldValue, newValue) -> {
            updatePropertyValues();
        });
    }

    public ObservabeItemProp(itemBoneProperties prop){
        this();
        this.properties = prop;
        boneName.setValue(prop.boneName);
        rotX.set(prop.rotX);
        rotY.set(prop.rotY);
        rotZ.set(prop.rotZ);

        locX.set(prop.locX);
        locY.set(prop.locY);
        locZ.set(prop.locZ);

        sizeX.set(prop.sizeX);
        sizeY.set(prop.sizeY);
        sizeZ.set(prop.sizeZ);
    }

    public void updatePropertyValues(){
        JMEApp app = JMEApp.getInstance();
        properties.updateValues(boneName.getValue(),rotX.getValue(),rotY.getValue(),rotZ.getValue(), rotW.getValue(),locX.getValue(),
                locY.getValue(),locZ.getValue(),sizeX.getValue(),sizeY.getValue(),sizeZ.getValue());
        app.setItemLayout(properties);
    }

    public itemBoneProperties toItemProp(){
        return new itemBoneProperties(this.boneName.getValue(), this.rotX.getValue(), this.rotY.getValue(), this.rotZ.getValue(),this.rotW.getValue(),
                this.locX.getValue(), this.locY.getValue(), this.locZ.getValue(),
                this.sizeX.getValue(), this.sizeY.getValue(), this.sizeZ.getValue());
    }

    //-----------------
    //GETTER AND SETTER
    //-----------------
    public String getBoneName() {
        return boneName.get();
    }

    public StringProperty boneNameProperty() {
        return boneName;
    }

    public void setBoneName(String boneName) {
        this.boneName.set(boneName);
    }

    public float getRotX() {
        return rotX.get();
    }

    public FloatProperty rotXProperty() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX.set(rotX);
    }

    public float getRotY() {
        return rotY.get();
    }

    public FloatProperty rotYProperty() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY.set(rotY);
    }

    public float getRotZ() {
        return rotZ.get();
    }

    public FloatProperty rotZProperty() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ.set(rotZ);
    }

    public float getLocX() {
        return locX.get();
    }

    public FloatProperty locXProperty() {
        return locX;
    }

    public void setLocX(float locX) {
        this.locX.set(locX);
    }

    public float getLocY() {
        return locY.get();
    }

    public FloatProperty locYProperty() {
        return locY;
    }

    public void setLocY(float locY) {
        this.locY.set(locY);
    }

    public float getLocZ() {
        return locZ.get();
    }

    public FloatProperty locZProperty() {
        return locZ;
    }

    public void setLocZ(float locZ) {
        this.locZ.set(locZ);
    }

    public float getSizeX() {
        return sizeX.get();
    }

    public FloatProperty sizeXProperty() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX.set(sizeX);
    }

    public float getSizeY() {
        return sizeY.get();
    }

    public FloatProperty sizeYProperty() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY.set(sizeY);
    }

    public float getSizeZ() {
        return sizeZ.get();
    }

    public FloatProperty sizeZProperty() {
        return sizeZ;
    }

    public void setSizeZ(float sizeZ) {
        this.sizeZ.set(sizeZ);
    }
}
