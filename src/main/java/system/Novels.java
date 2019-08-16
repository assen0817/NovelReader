package system;

import layout.NovelsListLayout;
import windows.MessageWindow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Novels {
    private static String novelNcodeSite = "ncode.syosetu.com";
    private static String novelAPI = "https://api.syosetu.com/novelapi/api/";
    private static NovelsListLayout novelsListLayouts;

//更新する小説リストを登録する
    public static void setNovelList(NovelsListLayout novelsListLayouts){
        Novels.novelsListLayouts = novelsListLayouts;
    }

    //urlから小説を追加
    public static void add(String url){
        MessageWindow mes = new MessageWindow();
        if (!checkURL(url)) {
            mes.Error("このページからはダウンロードできません。");
            return;
        }
        String ncode = getNcode(url);

        if(ncode == null) {
            mes.Error("このページからはダウンロードできません。");
            return;
        }

        if(checkWhetherNovel(ncode)) {
            mes.Error("この小説は既に登録されています。");
            return;
        }

        makeNovelInfo(ncode);
        novelsListLayouts.update();
        mes.finishNotice("小説が正常に追加されました。");

    }

    public static Boolean checkURL(String url){
        return url != null || !url.trim().isEmpty();
    }

    //URLからNcodeを取得
    public static String getNcode(String url){
        String[] urls = url.split("/");
        boolean nextNovel = false;
        for (String u : urls) {
            if(nextNovel){
                if(u.indexOf("n") == 0){
                    return u;
                }
                nextNovel = false;
            }
            if(u.equals(novelNcodeSite)){
                nextNovel = true;
            }
        }
        return null;
    }

//  追加したい小説がもう追加されているかどうかをチェック
    public static Boolean checkWhetherNovel(String ncode){
        List<String> localNcode = Files.getLocalNcode();
        if (localNcode == null) return false;
        for(String str: localNcode) if(str.equals(ncode))return true;
        return false;
    }

    // 追加したい小説（ncodeで判別）の情報をAPIで取得する
    public static void makeNovelInfo(String ncode) {
        try{
//             URLを作成してGET通信を行う
            URL url = new URL( novelAPI + "?ncode=" + ncode);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
//             サーバーからのレスポンスを標準出力へ出す
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            Files.NovelWriter(ncode, reader);
            reader.close();
//            ボタン連打によるDOS攻撃防止（API提供サーバー負荷対策）
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
