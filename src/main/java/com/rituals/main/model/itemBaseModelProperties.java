package com.rituals.main.model;

import java.util.List;

public class itemBaseModelProperties {
    public String baseModel = "";
    public List<itemBoneProperties> positions;

    public itemBaseModelProperties(){}

    public itemBaseModelProperties(String baseModel, List<itemBoneProperties> positions) {
        this.baseModel = baseModel;
        this.positions = positions;
    }
}
