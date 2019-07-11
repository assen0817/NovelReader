package app;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import webWindow.WebStage;

public class Main extends Application {

    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        FlowPane pane = new FlowPane();
        Scene scene = new Scene(pane, 280, 200);
        Button b1 = new Button("子ステージ開く");
        //マウスがクリックされたときのイベント
        EventHandler<MouseEvent> mouseClick = this::mouseClick;
        b1.addEventHandler( MouseEvent.MOUSE_CLICKED , mouseClick );
        pane.getChildren().add( b1 );

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    private void mouseClick(MouseEvent event) {
        try {
            WebStage nst2 = new WebStage( stage );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
