/*
 * Project: Java 2 Programming exercise 15.33
 * Name: Lauren Smith
 * Date: 1/24/2021 
 * Description: Makes an animated bean machiene 
 */
package pkg1533;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //makes BeanPane 
        BeanPane pane=new BeanPane(); 
        //runs pane animation method
        pane.animation(); 
        
        
        Scene scene = new Scene(pane, 300, 250);
        
        primaryStage.setTitle("Bean Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
} 


     
        
 
