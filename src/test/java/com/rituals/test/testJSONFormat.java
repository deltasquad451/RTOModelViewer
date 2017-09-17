package com.rituals.test;

import com.google.gson.Gson;
import com.rituals.main.model.itemBaseModelProperties;
import com.rituals.main.model.itemBoneProperties;
import com.rituals.main.model.itemProperties;

import java.util.Arrays;

public class testJSONFormat {
    public static void main(String ...args){
        itemBoneProperties boneProperties = new itemBoneProperties("Bone1",1,1,1,2,2,2,3,3,3);
        Gson gson = new Gson();
        System.out.println(gson.toJson(boneProperties));

        itemBoneProperties boneProperties1 = new itemBoneProperties("Bone2",1,1,1,2,2,2,3,3,3);
        itemBoneProperties boneProperties2 = new itemBoneProperties("Bone3",1,1,1,2,2,2,3,3,3);

        itemBaseModelProperties baseModelProperties = new itemBaseModelProperties("BaseModel1",
                Arrays.asList(boneProperties,boneProperties1,boneProperties2));

        itemProperties itemProperties = new itemProperties("ItemNameTest",Arrays.asList(baseModelProperties));

        System.out.println(gson.toJson(baseModelProperties));
        System.out.println(gson.toJson(itemProperties));
    }
}
