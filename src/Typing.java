//package tesr;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import java.util.*;

public class Typing {
	static int _genreData;
	Typing(int _tmpgenreData) {
		_genreData = _tmpgenreData;
	}
	/* �E�C���h�E�\�� */
	static void showQuestion() {
		JFrame frame = new InputAnswer();
		frame.setTitle("���o����");
		frame.setBounds((frame.getToolkit().getScreenSize().width/2)-400,
				(frame.getToolkit().getScreenSize().height/2)-300, 800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		SelectQuestion selQues = new SelectQuestion(_genreData);
	}
}

class InputAnswer extends JFrame {
	SelectQuestion selQues = new SelectQuestion();
	int _showFontSize = 0, _inputFontSize = 0, 
			x_input = 0, y_input = 0, x_show = 0, y_show = 0;
	int j = 0, _type = 0, _miss = 0, sum = 0, num = 0, miss_check = 0;
	int sp = 10, time_62 = 15, rand;
	int str_number, i = 0, strWidth1 = 0, strWidth2 = 0;
	int _quesCheck1 = 0, _quesCheck2 = 0, _startCheck = 0, _quesNum = 1, check_Array = 0;
	long start, stop, _answerTime;
	String Genre;
	String _showWord;
	String _inputWord;
	int[] randArray;
	//String msg=" "; 
	public void paint(Graphics g) {
		super.paint(g);
		if (check_Array == 0) {
			selQues.selQuestion();
			randArray = new int[selQues._selectShowWord.size()];
			check_Array += 1;
		}
		/* �I�΂ꂽ�W���������� */
		switch (selQues._genreData) {
		case 1: Genre = "�p�P��"; break;
		case 2: Genre = "��Ǌ���"; break;
		case 3: Genre = "���{��"; break;
		default: Genre = "�s��"; break;
		}
		/* �I�΂���茈�� */
		if (_quesCheck1 == _quesCheck2) {
			if (sum < 10) {
				while (true) {
					rand = (int)(Math.random() * selQues._selectShowWord.size());
					if (randArray[rand] == 0) {
						_showWord = selQues._selectShowWord.get(rand);
						_inputWord = selQues._selectAnsWord.get(rand);
						randArray[rand] = 1;
						break;
					}
				}
			}
		}
		/* ���Ɠ��͕����̃t�H���g�T�C�Y���� */
		if (_showWord.length() == 0) {
			System.exit(0);
		} else if ((0 < _showWord.length()) && (_showWord.length() <= 5)) {
			_showFontSize = 100;
			_inputFontSize = 45;
		} else if ((5 < _showWord.length()) && (_showWord.length()) <= 10) {
			_showFontSize = 60;
			_inputFontSize = 45;
		} else if (10 < _showWord.length()) { 
			_showFontSize = 25;
			_inputFontSize = 30;
		}

		/* ������̒����擾 */
		g.setFont(new Font("Serif", Font.PLAIN, _inputFontSize));
		FontMetrics fmInput = g.getFontMetrics();
		g.setFont(new Font("Serif", Font.PLAIN, _showFontSize));
		FontMetrics fmShow = g.getFontMetrics();

		/* �扽��ƃW�������\�� */
		g.setFont(new Font("Serif", Font.PLAIN, 50));
		g.setColor(Color.black);
		g.drawString("��"+ _quesNum +"��", 20, 80);
		g.drawString(Genre, 580, 80);

		/* ���Ƒł����̕\���ꏊ�ݒ� */
		if (_quesCheck1 == _quesCheck2) {
			for(j = 0;j < _inputWord.length();j ++) {
				strWidth1 += fmInput.charWidth(_inputWord.charAt(j));
			}
			x_input = 400 - (strWidth1 / 2);
			y_input = 450;

			for(j = 0;j < _showWord.length();j ++) {
				strWidth2 += fmShow.charWidth(_showWord.charAt(j));
			}
			x_show = 400 - (strWidth2 / 2);
			y_show = 325;
			if (_showWord.length() > 10) {
				y_show = 150;
			}
			_quesCheck1 += 1;
		}

		/* START�\������ */
		if (_startCheck == 0) {
			try {
				g.setFont(new Font("Serif", Font.PLAIN, 100));
				g.setColor(Color.red);
				g.drawString("START", 250, 350);
				Thread.sleep(500);

				g.setFont(new Font("Serif", Font.PLAIN, 100));
				Color color = UIManager.getColor ( "Panel.background" );
				g.setColor(color);
				g.drawString("START", 250, 350);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			_startCheck += 1;
			start = System.currentTimeMillis();
		}

		/* ���\�� */
		g.setFont(new Font("Serif", Font.PLAIN, _showFontSize)); 
		g.setColor(Color.black);	
		if (_showWord.length() > 31) {
			for (str_number = 0;str_number <= _showWord.length();str_number ++) {
				g.drawString(_showWord.substring(num,str_number), 8, y_show);

				if ((str_number == 31) || (str_number == 62) || (str_number == 93) ||
						(str_number == 124) || (str_number == 155) || (str_number == 186) ||
						(str_number == 217)) {
					y_show += 40;
					num += 31;
				}
			}
			num = 0; y_show = 150;
		} else {
			g.drawString(_showWord, x_show, y_show);
		}

		/* �^�C�s���O�����\�� */
		g.setFont(new Font("Serif", Font.PLAIN, _inputFontSize));
		g.setColor(Color.black);	
		if (_inputWord.length() > 62) {
			if (miss_check != _type - _miss) {
				if (i > 30) {
					time_62 -= 13;
				}
			}
			g.drawString(_inputWord, time_62, y_input);
		} else {
			g.drawString(_inputWord, x_input, y_input);
		}

		/* �����F�X�V */
		if (i > 0) { 
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.PLAIN, _inputFontSize));
			if (_inputWord.length() > 62) {
				if (miss_check != _type - _miss) {
					x_input = time_62;
				}
			}
			g.drawString(_inputWord.substring(0,i), x_input, y_input);
		}

		if (_inputWord.length() < i+1) { //�Ō�̕����܂ŏI�������
			try {
				if (sum == 9) { //�I������
					stop = System.currentTimeMillis();
					_answerTime = stop - start; //���ԑ���I��

					/* �I���\�� */
					Color color = UIManager.getColor ( "Panel.background" );
					g.setFont(new Font("Serif", Font.PLAIN, _showFontSize));
					g.setColor(color);
					g.drawString(_showWord, x_show, y_show);

					g.setFont(new Font("Serif", Font.PLAIN, _inputFontSize));
					g.setColor(color);
					g.drawString(_inputWord, x_input, y_input);

					g.setFont(new Font("Serif", Font.PLAIN, 100));
					g.setColor(Color.red);
					g.drawString("FINISH", 250, 350);

					g.setFont(new Font("Serif", Font.PLAIN, 20));
					g.setColor(Color.black);
					//g.drawString("���^�C�v��:"+ _type, 10, 130);
					//g.drawString("���~�X��:"+ _miss, 10, 160);
					//g.drawString("�v������(ms):"+ _answerTime, 10, 190);
					Thread.sleep(3000);

					Result result = new Result();
					result.jituryokuhantei(_answerTime, (double)_type, (double)_miss,Genre);
					setVisible(false);
					//Typing Type = new Typing(1);
					//Type.showQuestion();
				}
				_quesNum += 1;
				sum += 1;
				_quesCheck2 += 1;

				_showFontSize = 0; _inputFontSize = 0;
				time_62 = 15; miss_check = 0;
				i = 0; j = 0; num = 0;
				x_input = 0; y_input = 0; x_show = 0; y_show = 0;
				strWidth1 = 0; strWidth2 = 0;
				Thread.sleep(10);

				repaint();
			} catch (Exception t) {
				t.printStackTrace();
			}
		}

	}

	/* �L�[���� */
	InputAnswer() {
		requestFocus();   //�t�H�[�J�X���擾
		addKeyListener(   //�L�[���X�i�[��ǉ�
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						//msg = "";
						if (e.getModifiers() == e.SHIFT_MASK) {//�V�t�g�L�[��������Ă��邩
							sp = 50;
						} else {
							sp = 10;
						}
						if (i < _inputWord.length()) {
							switch (e.getKeyChar()){  //�����ꂽ�L�[�R�[�h�𓾂�
							default:
								//int keycode = e.getKeyCode();
								//String moji = (KeyEvent.getKeyText(keycode)).toLowerCase();//���͕���
								char key = e.getKeyChar();
								String moji = String.valueOf(key);//���͕���

								miss_check = _type - _miss;			   
								if (moji.equals(_inputWord.substring(i,i+1))) {
									//msg = " �����I";
									_type += 1;
									i += 1;//�������炷
									repaint();
								} else {
									//msg = "  " + moji + " ��������܂���";
									_type += 1;
									_miss += 1;
									repaint();
								}
								break;
							}
							repaint();
						}
					} 
				}
				);
		addWindowListener(new WinAdapter());
	}
}

class WinAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}
}