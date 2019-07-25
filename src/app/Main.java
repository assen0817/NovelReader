package app;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import layout.NovelColumnsLayout;
import layout.NovelsListLayout;
import windows.MainWindow;
import windows.WebStage;

public class Main extends Application {
    private Stage stage;
    private FlowPane pane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        pane = new FlowPane();
        Scene scene = new Scene(pane, 1000, 750);

        MainWindow mainWindow = new MainWindow(pane);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
