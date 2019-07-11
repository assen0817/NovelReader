package webWindow;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebStage extends Stage {
    public WebStage(Stage primaryStage) throws Exception{
        WebView root = new WebView();
        WebEngine engine = root.getEngine();
        Scene scene = new Scene(root,1200,800);
        setScene(scene);
        show();

        engine.load("https://yomou.syosetu.com");
    }
}
