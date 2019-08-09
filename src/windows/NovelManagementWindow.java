package windows;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import layout.NovelColumnsLayout;
import layout.NovelsListLayout;
import system.Novels;

public class NovelManagementWindow {

    private HBox hbox;

    public NovelManagementWindow(BorderPane pane){
        hbox = new HBox(5);
//        小説の章
        NovelColumnsLayout novelColumn = new NovelColumnsLayout();
//        小説
        NovelsListLayout novel = new NovelsListLayout(novelColumn);
        Novels.setNovelList(novel);

        HBox.setHgrow(novel.getLayout(), Priority.ALWAYS);
        HBox.setHgrow(novelColumn.getLayout(), Priority.ALWAYS);
        hbox.getChildren().addAll(novel.getLayout(), novelColumn.getLayout());

        pane.setCenter( hbox );
        setMenu(pane);
    }

    //メニューバーの設定
    private void setMenu(BorderPane pane){
        MenuBar menu = new MenuBar();
        Menu menu1 = new Menu("小説追加");
        MenuItem menuItem11 = new MenuItem("サイト");
        MenuItem menuItem12 = new MenuItem("URL");
        menuItem11.addEventHandler( ActionEvent.ANY , e -> mouseClick());
        menu1.getItems().add(menuItem11);
        menu1.getItems().add(menuItem12);

        Menu menu2 = new Menu("更新");
        Menu menu3 = new Menu("設定");
        menu.getMenus().add(menu1);
        menu.getMenus().add(menu2);
        menu.getMenus().add(menu3);
        pane.setTop(menu);
    }


    private void mouseClick() {
        try {
            WebStage nst2 = new WebStage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
