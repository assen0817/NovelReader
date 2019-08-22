package system;

import data.Novel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//ファイルの操作全般
public class Files {
    public static final String novels = "novels/";
    public static final String datafile = "/.NovelData";
    public static final String novelColumnsFile = "/.NovelColumns";

    //    小説の情報をファイルに書き込む
    public static void NovelWriter(String ncode, BufferedReader data) {
        try {
            String dir = novels + ncode;
            if(!Files.CheckNovelDirectory(ncode)) (new File(dir)).mkdir();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(dir + datafile)));
            String line = "";
            while ((line = data.readLine()) != null)
                pw.println(line);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    ファイルに保存された小説情報を返す
    public static HashMap<String, String> NovelReader(String ncode) {
        HashMap<String, String> map = new HashMap<String, String>();//[1]
        String file =  novels + ncode + datafile;
        if(!Files.CheckNovelDirectory(ncode)) return map;
        if(!Files.CheckNovelFileData(ncode)) return map;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));

            String line ;
            String key = "";
            StringBuilder value = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (line.contains(":")) {
                    map.put(key, value.toString());
                    key = "";
                    value = new StringBuilder();
                    String[] str = line.split(":");
                    int cnt = 0;
                    key = str[0].trim();
                    if(str.length >= 2) {
                        value.append(str[1].trim().replaceAll("|", "").replaceAll(">", ""));
                    }
                    continue;
                }
                if (!key.equals("")) {
                    value.append(line.trim()).append(" ");
                }
            }
            map.put(key, String.valueOf(value));

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
//    小説のディレクトリの有無
    public static Boolean CheckNovelDirectory(String ncode){
        String file = novels + ncode;
        return (new File(file)).exists();
    }
//    小説の情報ファイルの有無
    public static Boolean CheckNovelFileData(String ncode){
        String file = novels + ncode + datafile;
        return (new File(file)).exists();
    }

//    小説の章をを記憶するファイルの有無
    public static Boolean CheckNovelColumnsFileData(String ncode){
        String file = novels + ncode + novelColumnsFile;
        return (new File(file)).exists();
    }


//    小説の章のデータを書き込む
    public static void NovelColumnsWriter(String ncode, Document data){
        Elements elements = data.select(".chapter_title, .subtitle, .long_update, span[title]");
        try {
            String dir = novels + ncode;
            if(!Files.CheckNovelDirectory(ncode)) (new File(dir)).mkdir();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(dir + novelColumnsFile)));
            StringBuilder line = new StringBuilder();
            for (Element element : elements) {
                Elements a = element.select("a");
                Elements div = element.select("div");
                Elements dt = element.select("dt");
                Elements span = element.select("span");
                if(!a.text().isEmpty()){
                    pw.println(line);
                    line = new StringBuilder();
                    line.append("subtitle:").append(a.text());
                }
                if(!dt.text().isEmpty()){
                    line.append(",").append(dt.text());
                    if(!span.attr("title").isEmpty())
                        line.append(",").append(span.attr("title"));
                }
                if(!div.text().isEmpty()){
                    pw.println(line);
                    line = new StringBuilder();
                    line.append("chapter_title:").append(div.text());
                }
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    小説の章を取得
    public static ArrayList<String> NovelColumnsReader(String ncode, int index){
        ArrayList<String> columns = new ArrayList<>();
        if(!Files.CheckNovelDirectory(ncode)) return columns;
        if(!CheckNovelColumnsFileData(ncode)) return columns;
        try {
            File file = new File(novels + ncode + novelColumnsFile);

            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while((str = br.readLine()) != null) columns.add(str);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return columns;
    }

    //    小説の章（本文）をファイルから読み取る
    public static void NovelColumnReader(String ncode, int index){

    }

    public static List<String> getLocalNcode(){
        File file = new File("novels");
        List<String> ncode = new ArrayList<>();
        File[] files = file.listFiles();

        if(files == null) return null;

        for (File value : files) {
            String[] path = value.toString().split("\\\\");
            ncode.add(path[path.length -1]);
        }
        return ncode;
    }
}
