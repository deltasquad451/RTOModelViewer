package com.rituals.main;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.debug.SkeletonDebugger;
import com.jme3.scene.plugins.gltf.GltfLoader;
import com.jme3.scene.shape.Sphere;
import com.jme3x.jfx.injfx.JmeToJFXApplication;
import com.rituals.main.model.itemBoneProperties;

import java.util.ArrayList;
import java.util.List;

public class JMEApp extends JmeToJFXApplication implements AnimEventListener {
    private static JMEApp ourInstance = new JMEApp();

    public static JMEApp getInstance() {
        return ourInstance;
    }

    public Node baseModel;
    public Node itemModel;
    Node animControlNodeContainer;
    public Geometry bonePointer;
    public SkeletonDebugger skeletonDebugger;
    public AnimControl animControl = null;
    public SkeletonControl skeletonControl = null;
    List<AnimControl> controls = new ArrayList<>();
    List<SkeletonControl> skeletonControls = new ArrayList<>();

    private JMEApp() {
    }

    @Override
    public void simpleInitApp(){
        super.simpleInitApp();

        Sphere b = new Sphere(10,10,0.03f);
        bonePointer = new Geometry("Player", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        mat.getAdditionalRenderState().setDepthTest(false);
        bonePointer.setMaterial(mat);
        rootNode.attachChild(bonePointer);
        this.flyCam.setDragToRotate(true);

        this.flyCam.setMoveSpeed(10f);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);



    }

    public void addBaseModel (String path, String rootPath){
        System.err.println(path);
        if(this.baseModel != null){
            this.rootNode.detachChild(this.baseModel);
        }
        this.assetManager.registerLoader(GltfLoader.class);
        this.assetManager.registerLocator(rootPath, FileLocator.class);
        this.baseModel = (Node)this.assetManager.loadModel(path);




        this.baseModel.depthFirstTraversal(spatial -> {
            if(spatial.getControl(AnimControl.class) != null){
                animControlNodeContainer = (Node) spatial;
                controls.add(spatial.getControl(AnimControl.class));
                skeletonControls.add(spatial.getControl(SkeletonControl.class));
            }
        });

        if(controls.size() == 1){
            this.animControl = controls.get(0);
            this.skeletonControl = skeletonControls.get(0);
        }

        if(animControl != null){
            for(String s : animControl.getAnimationNames()){
                System.out.println(s+" --");
            }
            this.skeletonDebugger = new SkeletonDebugger("skeletonDebugger", animControl.getSkeleton());
            Material material = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
            material.setColor("Color",ColorRGBA.Green);
            material.getAdditionalRenderState().setDepthTest(false);
            skeletonDebugger.setMaterial(material);
            this.animControlNodeContainer.attachChild(skeletonDebugger);

        }
        this.rootNode.attachChild(baseModel);
    }

    public List<String> getBones(){
        List<String> bones = new ArrayList<>();
        if(this.animControl != null){
            int boneCount = this.animControl.getSkeleton().getBoneCount();
            for(int i = 0; i < boneCount; i++){
                System.out.println(this.animControl.getSkeleton().getBone(i).getName());
                bones.add(this.animControl.getSkeleton().getBone(i).getName());
            }
        }
        return bones;
    }

    public void changeBonePonter(String boneName){

        Node boneNode = this.skeletonControl.getAttachmentsNode(boneName);
        if(boneNode != null){
            this.bonePointer.setLocalTranslation(boneNode.getWorldTranslation());
        }
    }

    public void loadItemModel(String path, String rootPath){
        System.out.println(path+" "+rootPath);
        if(this.itemModel != null){
            Node parent = this.itemModel.getParent();
            parent.detachChild(this.itemModel);
        }
        this.assetManager.registerLocator(rootPath, FileLocator.class);
        this.itemModel = (Node)this.assetManager.loadModel(path);
    }

    public void setItemLayout(itemBoneProperties layout){
        Node attch = this.skeletonControl.getAttachmentsNode(layout.boneName);

        if(itemModel.getParent() != null){
            Node parentAttchNode = this.itemModel.getParent();
            parentAttchNode.detachChild(this.itemModel);
        }
        attch.attachChild(itemModel);
        itemModel.setLocalTranslation(layout.locX,layout.locY,layout.locZ);
        itemModel.setLocalScale(layout.sizeX,layout.sizeY,layout.sizeZ);
        itemModel.setLocalRotation(new Quaternion(layout.rotX,layout.rotY,layout.locZ,layout.rotW));
    }

    @Override
    public void onAnimCycleDone(AnimControl animControl, AnimChannel animChannel, String s) {

    }

    @Override
    public void onAnimChange(AnimControl animControl, AnimChannel animChannel, String s) {

    }
}
