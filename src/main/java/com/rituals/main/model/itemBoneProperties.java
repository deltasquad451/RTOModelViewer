package com.rituals.main.model;

public class itemBoneProperties {
    public String boneName = "";
    public float rotX = 0f;
    public float rotY = 0f;
    public float rotZ = 0f;

    public float locX = 0f;
    public float locY = 0f;
    public float locZ = 0f;

    public float sizeX = 0f;
    public float sizeY = 0f;
    public float sizeZ = 0f;

    public itemBoneProperties(){}

    public itemBoneProperties(String boneName, float rotX, float rotY, float rotZ, float locX, float locY, float locZ, float sizeX, float sizeY, float sizeZ) {
        this.boneName = boneName;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.locX = locX;
        this.locY = locY;
        this.locZ = locZ;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    @Override
    public String toString() {
        return "itemBoneProperties{" +
                "boneName='" + boneName + '\'' +
                ", rotX=" + rotX +
                ", rotY=" + rotY +
                ", rotZ=" + rotZ +
                ", locX=" + locX +
                ", locY=" + locY +
                ", locZ=" + locZ +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", sizeZ=" + sizeZ +
                '}';
    }
}
