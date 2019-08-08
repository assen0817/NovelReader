package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import windows.MainWindow;

public class Main extends Application {
    private Stage stage;
    private BorderPane pane;
    //トップWindowの生成
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        pane = new BorderPane();
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
