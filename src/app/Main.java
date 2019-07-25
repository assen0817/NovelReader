package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import windowUi.NovelsListLayout;

public class Main extends Application {

    private Stage stage;
    private FlowPane pane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        pane = new FlowPane();
        Scene scene = new Scene(pane, 1000, 750);

        NovelsListLayout novel = new NovelsListLayout(this);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    public Stage getStage() {return stage;}
    public FlowPane getFlowPane() {return pane;}

    public static void main(String[] args) {
        launch(args);
    }
}
