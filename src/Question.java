import java.util.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

class Qcsv {
	int _genreNum;
	int _qesNum;
	String _priStr;
	String _inpStr;

	Qcsv(int g,int q,String p,String i){
		this._genreNum = g;
		this._qesNum = q;
		this._priStr = p;
		this._inpStr = i;
	}
}

public class Question extends JFrame{
	JFrame frame = new JFrame();
	JFrame Rframe = new JFrame();
	JSplitPane splitpane = new JSplitPane();
	int _genreData;
	int Qsize=0;

	Question(int _tmpGenreData){
		_genreData = _tmpGenreData-3;
	}
	// 4.1問題追加処理
	public void addQuestion(String _inputString, String _outputString, int _quesNum){
		String  _showWord, _inputWord;
		int _genreNum;
		boolean _addError;

		// 4.3入力確認処理からエラー情報の受け取り
		_addError = checkQesAdd(_inputString, _outputString);
		if ( _addError == true ){
			return ;
		} else {
			// 4.4問題一覧表示処理からジャンル番号取得
			//_genreData = 1; //printQestion();

			// データの格納
			_genreNum = this._genreData;
			_quesNum += 1;
			_inputWord = _inputString;
			_showWord = _outputString;

			// csvファイル読み込み
			// http://www.atmarkit.co.jp/fjava/javatips/063java003.html
			try {

				// ファイルのオープン
				PrintWriter  pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./mondaiDB.csv"),true),"Shift_JIS")));

				// データの追加
				pw.println( _genreNum + "," + _quesNum + "," + _inputWord + "," + _showWord);
				pw.close();

			} catch (FileNotFoundException e) {
				// Fileオブジェクト生成時の例外補足
				e.printStackTrace();
			} catch (IOException e) {
				// BufferedWriterオブジェクトのクローズ時の例外補足
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "追加しました！","Add Success",JOptionPane.PLAIN_MESSAGE );
		}
	}

	// 4.2問題削除処理
	public void deleteQuestion(int _clickData, int _checkQNum){

		ArrayList<String> _elements = new ArrayList<String>();
		ArrayList<String> _otherElements = new ArrayList<String>();
		int _set = 0;
		boolean _delError;

		// 4.3入力確認処理からエラー情報の受け取り
		_delError = checkQesAdd(_checkQNum);
		if ( _delError == true ){
			return;
		} else {

			try {
				// ファイルを読み込む
				FileReader fr = new FileReader("./mondaiDB.csv");
				BufferedReader br = new BufferedReader(fr);

				// 読み込んだファイルを１行ずつ処理する
				String line;
				StringTokenizer token;
				while ((line = br.readLine()) != null) {
					// 区切り文字","で分割する
					token = new StringTokenizer(line, ",");

					// 分割した文字を_elementsに格納する
					while (token.hasMoreTokens()) {
						int _genreTaihi = Integer.parseInt(token.nextToken());
						if ( this._genreData == _genreTaihi ){
							// 通し番号
							_elements.add(token.nextToken());
							// 入力文字
							_elements.add(token.nextToken());
							// 表示文字
							_elements.add(token.nextToken());
						} else {
							// ジャンル番号
							_otherElements.add(_genreTaihi + "");
							// 通し番号
							_otherElements.add(token.nextToken());
							// 入力文字
							_otherElements.add(token.nextToken());
							// 表示文字
							_otherElements.add(token.nextToken());
						} 
					}
				}
				// 終了処理
				br.close();

			} catch (IOException ex) {
				// 例外発生時処理
				ex.printStackTrace();
			}
			try {
				// ファイルのオープン
				PrintWriter  pw1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./mondaiDB.csv"),false),"Shift_JIS")));

				// 以下クリックされた問題以外のデータの追加
				for (int _i = 0; _i < _elements.size(); _i ++){
					if ( _i % 3 == 0 && ( _clickData - 1 ) * 3 != _i ){
						// 問題削除後、通し番号から1を引き、削除前は0を引く
						int _newQuesNumI = Integer.parseInt(_elements.get(_i)) - _set;
						String _newQuesNumC = _newQuesNumI + "";
						// 選択されたジャンルの問題を格納
						pw1.println(this._genreData + "," + _newQuesNumC + "," + _elements.get( _i + 1 ) + "," + _elements.get( _i + 2 ));
					} else if ( _i % 3 == 0 && ( _clickData - 1 ) * 3 == _i ){
						// 問題削除後、通し番号から引く数をセット
						_set = 1;
					} 
				}
				for (int _i = 0; _i < _otherElements.size(); _i ++){
					if ( _i % 4 == 0){
						// 選択されたジャンル以外の問題を格納
						pw1.println(_otherElements.get( _i ) + "," + _otherElements.get( _i + 1 ) + "," + _otherElements.get( _i + 2 ) + "," + _otherElements.get( _i + 3 ));
					}
				}

				pw1.close();
				// ここまで

			} catch (FileNotFoundException e) {
				// Fileオブジェクト生成時の例外補足
				e.printStackTrace();
			} catch (IOException e) {
				// BufferedWriterオブジェクトのクローズ時の例外補足
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "削除しました！", "Delete Success",JOptionPane.PLAIN_MESSAGE );
		}
	}

	public void printQestion(){
		frame = JFinit();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds((frame.getToolkit().getScreenSize().width/2)-400,
				(frame.getToolkit().getScreenSize().height/2)-300, 800, 600);
		frame.setTitle("問題追加削除画面");
		frame.setVisible(true);
	}

	public boolean checkQesAdd(String _tempCheckPriStr,String _tempCheckInpStr){
		String _checkPriStr = new String();
		String _checkInpStr = new String();
		boolean _addError;

		_checkPriStr = _tempCheckPriStr;
		_checkInpStr = _tempCheckInpStr;
		_addError = false;

		if(_checkPriStr.indexOf(",") !=  -1 || _checkInpStr.indexOf(",") !=  -1){
			JOptionPane.showMessageDialog(null, "「表示する文字」または「入力する文字」に「,」を入れないでください","Error",JOptionPane.ERROR_MESSAGE);
			_addError = true;
		}else if(_checkPriStr.length() == 0 || _checkInpStr.length() == 0){
			JOptionPane.showMessageDialog(null, "「表示する文字」または「入力する文字」が空欄です", "Error",JOptionPane.ERROR_MESSAGE);
			_addError = true;
		}else if(_checkPriStr.length() >= 200){
			JOptionPane.showMessageDialog(null, "「表示する文字」の文字数は２００文字以内にしてください","Error",JOptionPane.ERROR_MESSAGE);
			_addError = true;
		}else if(_checkInpStr.length() >= 500){
			JOptionPane.showMessageDialog(null, "「入力する文字」の文字数は５００文字以内にしてください","Error",JOptionPane.ERROR_MESSAGE);
			_addError = true;
		}
		for( int i=0; i<_tempCheckInpStr.length(); i++ ) {
			char c = _tempCheckInpStr.charAt( i );
			if( ( c<='\u007e' )|| 
					( c=='\u00a5' )|| 
					( c=='\u203e' ) 
					){

			}else{
				_addError = true;
				JOptionPane.showMessageDialog(null, "「入力する文字」は半角英数字で入力してください", "Error",JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
		return _addError;
	}

	public boolean checkQesAdd(int _tempCheckQNum){
		int _checkQNum = _tempCheckQNum;
		boolean _delError = false;
		if(_checkQNum <= 10){
			JOptionPane.showMessageDialog(null, "問題数は１０問以上にしてください", "これ以上削除できません",JOptionPane.ERROR_MESSAGE);
			_delError = true;
		}
		return _delError;
	}

	public int inputRepeat(){
		int _rePrintData = 0;
		return _rePrintData;
	}

	public JFrame JFinit(){

		ArrayList<Qcsv> Q = new ArrayList<Qcsv>();

		try {//make Qcsv arraylist
			String s;
			FileInputStream in = new FileInputStream("./mondaiDB.csv");//opening mondaiDB
			InputStreamReader sr = new InputStreamReader(in, "Shift_JIS");
			BufferedReader br = new BufferedReader(sr);

			while( (s = br.readLine()) != null ) {//read line until end of mondaiDB file
				String array[] = s.split( "," );//split to each of info
				if(Integer.valueOf(array[0]) == this._genreData){
					Qcsv tmpQ = new Qcsv( (Integer.valueOf(array[0])),( Integer.valueOf(array[1])),(array[2]),(array[3]));
					if(tmpQ._genreNum == this._genreData){
						Q.add(tmpQ);
					}
				}
			}
			Qsize = Q.size();
			br.close();
		} catch( IOException e )  {
		} catch( NumberFormatException e )  {
		}
		splitpane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitpane.setDividerLocation(150);

		//TEXTBOX
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel frmQuestionAddDelete_Top = new JPanel();
		frmQuestionAddDelete_Top.setLayout(null);

		int _backX; _backX = 72;
		int _backY; _backY = 10;
		int _posX; _posX = 75;
		int _posY; _posY = _backY + 50;

		JTextField _textBoxInpStr = new JTextField(40);
		JTextField _textBoxPriStr = new JTextField(40);
		String genre = null;
		switch(_genreData){
		case 1:
			genre = "英単語";
			break;
		case 2:
			genre = "難読漢字";
			break;
		case 3:
			genre = "日本文";
			break;
		}
		JLabel _labelGenre = new JLabel("ジャンル： " + genre);
		JLabel _labelPriStr = new JLabel("表示する文字");
		JLabel _labelInpStr = new JLabel("入力する文字");
		JButton _addButton = new JButton("追加");
		JButton _backButton= new JButton("もどる");

		_textBoxInpStr.setBounds(_posX +90,_posY+40,420, 30);
		_textBoxPriStr.setBounds(_posX +90,_posY,420, 30);
		_labelGenre.setBounds(_backX+510,_backY,150, 30);
		_labelPriStr.setBounds(_posX,_posY,80, 30);
		_labelInpStr.setBounds(_posX,_posY+40, 80, 30);
		_addButton.setBounds(_posX+560,_posY+40, 80, 30);
		_backButton.setBounds(_backX,_backY, 80, 30);

		frmQuestionAddDelete_Top.add(_labelGenre);
		frmQuestionAddDelete_Top.add(_labelPriStr);
		frmQuestionAddDelete_Top.add(_textBoxPriStr);
		frmQuestionAddDelete_Top.add(_labelInpStr);
		frmQuestionAddDelete_Top.add(_textBoxInpStr);
		frmQuestionAddDelete_Top.add(_addButton);
		frmQuestionAddDelete_Top.add(_backButton);

		ActionLis lsnrAdd  = new ActionLis(_textBoxInpStr,_textBoxPriStr);
		ActionLis lsnrBack = new ActionLis();
		ActionLis lsnrDel =  new ActionLis();

		_addButton.addActionListener(lsnrAdd);
		_addButton.setActionCommand("Addbutton");
		_backButton.addActionListener(lsnrBack);
		_backButton.setActionCommand("Backbutton");

		splitpane.setTopComponent(frmQuestionAddDelete_Top);

		//SCROLL
		JPanel frmQuestionAddDelete_Buttom = new JPanel();
		frmQuestionAddDelete_Buttom.setLayout(null);
		int bposX = 72;
		int bposY = 50;
		int endPosY = 0;
		JLabel labelTouroku = new JLabel("問題一覧"); labelTouroku.setBounds(330	,10   , 300, 30);

		JLabel[] LabelQ = new JLabel[Q.size()];
		JButton[]ButtonQ= new JButton[Q.size()];

		for(int i=0;i<Q.size();i++){
			LabelQ[i] = new JLabel("<html>"+  Q.get(i)._priStr + "<br>" +  Q.get(i)._inpStr + "</html>");
			ButtonQ[i] = new JButton("削除");

			LabelQ[i].setBounds(bposX,bposY + 70*i,550,60);
			ButtonQ[i].setBounds(bposX+560,bposY+5 + 70*i,80,40);

			ButtonQ[i].addActionListener(lsnrDel);
			ButtonQ[i].setActionCommand(""+i);

			frmQuestionAddDelete_Buttom.add(LabelQ[i]);
			frmQuestionAddDelete_Buttom.add(ButtonQ[i]);
			endPosY = bposY + 70*(i+1);  
		}
		frmQuestionAddDelete_Buttom.add(labelTouroku);

		//splitpane.setBottomComponent(pbuttom);
		frmQuestionAddDelete_Buttom.setPreferredSize(new Dimension(200,endPosY));
		JScrollPane scrollpane = new JScrollPane(frmQuestionAddDelete_Buttom,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		splitpane.setBottomComponent(scrollpane);//scrollpane seting to bottom
		Rframe.getContentPane().add(splitpane, BorderLayout.CENTER);
		//this.revalidate();
		validate();
		return Rframe;
	}

	class ActionLis implements ActionListener{
		JTextField _TBOXInpStr;
		JTextField _TBOXPriStr;
		JButton button;
		ActionLis( JTextField _tmpTBOXInpStr, JTextField _tmpTBOXPriStr ){
			this._TBOXInpStr = _tmpTBOXInpStr;
			this._TBOXPriStr = _tmpTBOXPriStr;
		}
		ActionLis(){
		}
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand() == "Addbutton"){
				String checkPriStr;
				String checkInpStr;	
				checkPriStr = _TBOXPriStr.getText();
				checkInpStr = _TBOXInpStr.getText();
				addQuestion(checkPriStr,checkInpStr,Qsize);
				// エラーメッセージ削除
				//JOptionPane.showMessageDialog(null, "pushed: " + Qsize +" button", "Delete",JOptionPane.PLAIN_MESSAGE );
				frame.invalidate();
				printQestion();
			}else if(e.getActionCommand() == "Backbutton"){
				//JOptionPane.showMessageDialog(null, "pushed: BackButton","title", JOptionPane.PLAIN_MESSAGE );
				Mode m = new Mode("メニュー画面");
				frame.setVisible(false);
			}else{
				deleteQuestion(1 + Integer.valueOf(e.getActionCommand()),Qsize);
				// エラーメッセージ削除
				//JOptionPane.showMessageDialog(null, "pushed: " + Integer.valueOf(e.getActionCommand()) +" button" + "size = " + Qsize, "Delete",JOptionPane.PLAIN_MESSAGE );
				frame.invalidate();
				printQestion();
			}
		}
	}
}
