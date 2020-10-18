//package tesr;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.*;

public class SelectQuestion {
	//本命データの格納
	static ArrayList<String> _selectShowWord = new ArrayList<String>();
	static ArrayList<String> _selectAnsWord = new ArrayList<String>();
	static int _genreData;
	SelectQuestion(int _selGenreData) {
		_genreData = _selGenreData;
	}
	SelectQuestion() {
	}
	static void selQuestion() {
		try {
			//ArrayListを空に
			_selectShowWord.clear();
			_selectAnsWord.clear();
			//mondaiDB読み込み
			FileInputStream in = new FileInputStream("./mondaiDB.csv");//opening mondaiDB
			InputStreamReader sr = new InputStreamReader(in, "Shift_JIS");
			BufferedReader br = new BufferedReader(sr);
			//読み込んだファイルを１行ずつ処理する
			String line;
			StringTokenizer token;
			//↓でCSVデータを一個ずつ読み込む。
			while ((line = br.readLine()) != null) {
				//読み込んだデータを区切り文字","で分割する
				token = new StringTokenizer(line, ",");
				while (token.hasMoreTokens()) {
					//選択したジャンルデータとCSV上のジャンルデータが一致した場合、if分を回す。
					if(Integer.parseInt(token.nextToken()) == _genreData){
						//問題番号はいらないと思うので、スキップしちゃいます
						token.nextToken();
						_selectShowWord.add(token.nextToken());
						_selectAnsWord.add(token.nextToken());
					} else {
						//欲しいジャンルのデータじゃない場合、ブレイクする。
						break;
					}
				}
			}
			br.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	//public static void main(String[] args) {
	//selQuestion();
	//}
}

