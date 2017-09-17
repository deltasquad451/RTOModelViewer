package com.rituals.main.model;

import java.util.List;

public class itemProperties {
    public String ItemName = "";
    public List<itemBaseModelProperties> layouts;

    public itemProperties(){}

    public itemProperties(String itemName, List<itemBaseModelProperties> positions) {
        ItemName = itemName;
        this.layouts = positions;
    }
}
