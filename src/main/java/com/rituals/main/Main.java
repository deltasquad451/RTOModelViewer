/*
 * $Id$
 * 
 * Copyright (c) 2015, Simsilica, LLC
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the 
 *    distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE 
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rituals.main;


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

/**
 *
 *
 *  @author    themiddleman
 */
public class Main extends Application {

    MainController controller;

    public static void main( String... args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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
        stage.setTitle("RTO Item Editor");
    }

    private static JmeToJFXApplication makeJmeApplication(Stage stage, int framerate) {

        final AppSettings settings = JmeToJFXIntegrator.prepareSettings(new AppSettings(true), framerate);

        JmeToJFXApplication app = JMEApp.getInstance();
        app.setSettings(settings);
        app.setShowSettings(false);
        return app;
    }
}


