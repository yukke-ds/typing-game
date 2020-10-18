//package tesr;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.*;

public class SelectQuestion {
	//�{���f�[�^�̊i�[
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
			//ArrayList�����
			_selectShowWord.clear();
			_selectAnsWord.clear();
			//mondaiDB�ǂݍ���
			FileInputStream in = new FileInputStream("./mondaiDB.csv");//opening mondaiDB
			InputStreamReader sr = new InputStreamReader(in, "Shift_JIS");
			BufferedReader br = new BufferedReader(sr);
			//�ǂݍ��񂾃t�@�C�����P�s����������
			String line;
			StringTokenizer token;
			//����CSV�f�[�^������ǂݍ��ށB
			while ((line = br.readLine()) != null) {
				//�ǂݍ��񂾃f�[�^����؂蕶��","�ŕ�������
				token = new StringTokenizer(line, ",");
				while (token.hasMoreTokens()) {
					//�I�������W�������f�[�^��CSV��̃W�������f�[�^����v�����ꍇ�Aif�����񂷁B
					if(Integer.parseInt(token.nextToken()) == _genreData){
						//���ԍ��͂���Ȃ��Ǝv���̂ŁA�X�L�b�v�����Ⴂ�܂�
						token.nextToken();
						_selectShowWord.add(token.nextToken());
						_selectAnsWord.add(token.nextToken());
					} else {
						//�~�����W�������̃f�[�^����Ȃ��ꍇ�A�u���C�N����B
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

