package windows;

import javafx.scene.control.Alert;

public class MessageWindow {
    Alert alrt;
    public MessageWindow(){
        alrt = new Alert(Alert.AlertType.INFORMATION);
    }
    public void Error(String message){
        alrt.setContentText(message);
        alrt.setTitle("Error");
        alrt.setHeaderText(null);
        alrt.showAndWait();
    }
    public void finishNotice(String message){
        alrt.setContentText(message);
        alrt.setTitle("完了");
        alrt.setHeaderText(null);
        alrt.showAndWait();
    }
}
