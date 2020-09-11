/**
 * Copyright 2019 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.mycompany.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class App extends Application {

    private MapView mapView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        // set the title and size of the stage and show it
        stage.setTitle("My Map App");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();

        //Creating file menu
        Menu file = new Menu("File");
        //Creating file menu items
        MenuItem item1 = new MenuItem("Add Files");
        MenuItem item2 = new MenuItem("Start Converting");
        MenuItem item3 = new MenuItem("Stop Converting");
        MenuItem item4 = new MenuItem("Remove File");
        MenuItem item5 = new MenuItem("Exit");
        //Adding all the menu items to the file menu
        file.getItems().addAll(item1, item2, item3, item4, item5);

        MenuBar menuBar = new MenuBar();
        //Adding all the menus to the menu bar
        menuBar.getMenus().addAll(file);

        // Status bar
        Label status = new Label("Ready");
        HBox statusBar = new HBox();
        statusBar.getChildren().add(status);


        // create a border pane as the root node
        StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(stackPane);
        borderPane.setBottom(statusBar);

        // Create the scene
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);
        setupMap();
        // create an ArcGISMap with the default imagery basemap
        //ArcGISMap map = new ArcGISMap(Basemap.createImagery());

        // display the map by setting the map on the map view
        //mapView.setMap(map);
    }

    private void setupMap() {
        if (mapView != null) {
            //Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
            Basemap.Type basemapType = Basemap.Type.DARK_GRAY_CANVAS_VECTOR;
            double latitude = 34.02700;
            double longitude = -118.80543;
            int levelOfDetail = 12;
            ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
            mapView.setMap(map);
        }
    }

    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {

        if (mapView != null) {
            mapView.dispose();
        }
    }
}
