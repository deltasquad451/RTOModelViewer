package com.rituals.main.fxProperties;

import com.rituals.main.model.itemBoneProperties;
import javafx.beans.binding.ObjectBinding;

public class ItemPropObjBinding  extends ObjectBinding{

    private final ObservabeItemProp item;


    public ItemPropObjBinding(itemBoneProperties itemProp){
        this.item = new ObservabeItemProp(itemProp);
        bind(item.boneName,item.locX,item.locY,item.locZ,item.rotX,item.rotY,item.rotZ,item.sizeX,item.sizeY,item.sizeZ);

    }

    @Override
    public ObservabeItemProp getValue() {
        return item;
    }

    @Override
    protected ObservabeItemProp computeValue() {
        return item;
    }
}
