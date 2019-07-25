package windows;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebStage extends Stage {
    WebView webView;
    public WebStage(Stage primaryStage) throws Exception{
        webView = new WebView();
        WebEngine engine = webView.getEngine();
        Scene scene = new Scene(webView,1200,800);
        setScene(scene);
        show();

        engine.load("https://yomou.syosetu.com");
    }

}
