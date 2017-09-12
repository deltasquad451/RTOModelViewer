package com.rituals.main;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3x.jfx.injfx.JmeToJFXApplication;
import com.jme3x.jfx.injfx.JmeToJFXIntegrator;
import com.rituals.main.controllers.MainController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.FlowView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestJmeToJFXImageView extends Application {

    MainController controller;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {

//        final ImageView imageView = new ImageView();
//        final Button button = new Button("BUTTON");
//        final StackPane stackPane = new StackPane(imageView, button);
//        final Scene scene = new Scene(stackPane, 600, 600);
//
//        imageView.fitWidthProperty().bind(stackPane.widthProperty());
//        imageView.fitHeightProperty().bind(stackPane.heightProperty());
//
//        stage.setTitle("Test");
//        stage.setScene(scene);
//        stage.show();
//        stage.setOnCloseRequest(event -> System.exit(0));
//
//
//
//        final JmeToJFXApplication application = makeJmeApplication(stage, 80);
//        JmeToJFXIntegrator.startAndBindMainViewPort(application, imageView, Thread::new);

        Flow flow = new Flow(MainController.class);

        FlowHandler handler = flow.createHandler();
        StackPane peteStacker = handler.start();

        FlowView view = handler.getCurrentView();

        controller = (MainController) view.getViewContext().getController();

        stage.setScene(new Scene(peteStacker));
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(0));

        final JmeToJFXApplication application = makeJmeApplication(stage, 80);
        JmeToJFXIntegrator.startAndBindMainViewPort(application, controller.getJmeImageView() , Thread::new);
    }

    private static JmeToJFXApplication makeJmeApplication(Stage stage, int framerate) {

        final AppSettings settings = JmeToJFXIntegrator.prepareSettings(new AppSettings(true), 60);

        JmeToJFXApplication app = new JmeToJFXApplication() {

            protected Geometry player;
            Boolean isRunning = true;

            @Override
            public void simpleInitApp() {
                super.simpleInitApp();
                Box b = new Box(1, 1, 1);
                player = new Geometry("Player", b);
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setColor("Color", ColorRGBA.Blue);
                player.setMaterial(mat);
                rootNode.attachChild(player);
                initKeys(); // load my custom keybinding

            }

            /** Custom Keybinding: Map named actions to inputs. */
            private void initKeys() {
                /** You can map one or several inputs to one named mapping. */
                inputManager.addMapping("Pause", new KeyTrigger(keyInput.KEY_P));
                inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_J));
                inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_K));
                inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE), // spacebar!
                        new MouseButtonTrigger(MouseInput.BUTTON_LEFT));        // left click!
                /** Add the named mappings to the action listeners. */
                inputManager.addListener(actionListener, "Pause");
                inputManager.addListener(analogListener, "Left", "Right", "Rotate");
            }

            /** Use this listener for KeyDown/KeyUp events */
            private ActionListener actionListener = new ActionListener() {
                public void onAction(String name, boolean keyPressed, float tpf) {
                    if (name.equals("Pause") && !keyPressed) {
                        System.out.println("test");
                        isRunning = !isRunning;
                    }
                }
            };

            /** Use this listener for continuous events */
            private AnalogListener analogListener = new AnalogListener() {
                public void onAnalog(String name, float value, float tpf) {
                    if (isRunning) {
                        if (name.equals("Rotate")) {
                            player.rotate(0, value, 0);
                        }
                        if (name.equals("Right")) {
                            player.move((new Vector3f(value, 0, 0)));
                        }
                        if (name.equals("Left")) {
                            player.move(new Vector3f(-value, 0, 0));
                        }
                    } else {
                        System.out.println("Press P to unpause.");
                    }
                }
            };
        };

        app.setSettings(settings);
        app.setShowSettings(false);
        return app;
    }
}
