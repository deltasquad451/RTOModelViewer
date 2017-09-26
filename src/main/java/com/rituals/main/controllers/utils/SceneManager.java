package com.rituals.main.controllers.utils;

import com.google.gson.Gson;
import com.rituals.main.model.itemBaseModelProperties;
import com.rituals.main.model.itemBoneProperties;
import com.rituals.main.model.itemProperties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    public itemProperties properties;
    public String itemName = "";
    public File baseModelFile;
    public File itemModelFile;

    public SceneManager() {
        properties = new itemProperties();
        baseModelFile = new File("");
        itemModelFile = new File("");
    }

    public SceneManager(itemProperties properties, String itemName, File baseModelFile, File itemModelFile) {
        this.properties = properties;
        this.itemName = itemName;
        this.baseModelFile = baseModelFile;
        this.itemModelFile = itemModelFile;
    }

    public void saveLayout(List<itemBoneProperties> list){
        properties.ItemName = itemModelFile.getAbsolutePath();
        properties.layouts = new ArrayList<>();
        itemBaseModelProperties baseModelProperties = new itemBaseModelProperties();
        baseModelProperties.baseModel = baseModelFile.getAbsolutePath();
        baseModelProperties.positions = new ArrayList<>();

        for (itemBoneProperties e: list){
            baseModelProperties.positions.add(e);
        }

        String itemPath = itemModelFile.getParent()+File.pathSeparator+itemName;
        Gson gson = new Gson();
        String jsonValue = gson.toJson(baseModelProperties);
        saveFile(itemPath,jsonValue);
    }

    private void saveFile(String path, String jsonValue){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try{
            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);
            bw.write(jsonValue);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

}
