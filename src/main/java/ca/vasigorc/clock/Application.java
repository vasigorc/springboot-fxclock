/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.vasigorc.clock;

import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title}")
    private String windowTitle;

    @Autowired
    private ClockPane clockPane;

    @Override
    public void start(Stage stage) throws Exception {
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        String timeString = clockPane.getHour()+":"+clockPane.getMinute()+":"+clockPane.getSecond();
        Label lblCurrentTime = new Label(timeString);
        //Place clock and label in Border Plane
        BorderPane pane = new BorderPane();
        pane.setCenter(clockPane);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
        
        Scene scene = new Scene(pane, 250, 250);
        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
