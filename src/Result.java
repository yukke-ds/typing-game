import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Result implements ActionListener{
	//main���\�b�h���Œ�`*/
	JFrame frame4;
	JFrame frame3;
	//static String _resultGenre;
	//static double _type;
	//static double _miss;
	//static long _answerTime;
	String _rank="";
	// static double _successPercentage; 
	DecimalFormat df2 = new DecimalFormat("#.#");

	Result(){
		frame4=new JFrame("���ʕ\�����");
		frame4.setBounds((frame4.getToolkit().getScreenSize().width/2)-400,
				(frame4.getToolkit().getScreenSize().height/2)-300, 800, 600);
		frame4.setLayout(null);
	}

	//}
	//3.1���͔��菈��
	public void jituryokuhantei(long _answerTime,double _type,double _miss,String Genre){
		//������
		frame4.setVisible(true);
		double _successPercentage = 100;
		if( _miss != 0 ) _successPercentage = ( _type - _miss ) / _type * 100;
		//���_�v�Z
		int _score = (int)( _type / ( _answerTime / 1000) ) ;
		if( _miss != 0 ) _score = _score - 
				(int)( _miss / ( _answerTime / 1000)) * 3;
		_score = _score * 100; 
		//�����N����
		if( _score >= 600 ) _rank = "SP";
		else if ( _score >= 550 && _score <= 599 ) _rank = "EX";
		else if ( _score >= 500 && _score <= 549 ) _rank = "SSS";
		else if ( _score >= 450 && _score <= 499 ) _rank = "SS";
		else if ( _score >= 400 && _score <= 449 ) _rank = "S";
		else if ( _score >= 350 && _score <= 399 ) _rank = "A";
		else if ( _score >= 300 && _score <= 349 ) _rank = "B";
		else if ( _score >= 250 && _score <= 299 ) _rank = "C";
		else if ( _score >= 200 && _score <= 249 ) _rank = "D";
		else if ( _score >= 150 && _score <= 199 ) _rank = "E";
		else if ( _score >= 100 && _score <= 149 ) _rank = "F";
		else if ( _score <= 99 ) _rank = "G";
		/*new Result();
    }*/

		//3.2���ʕ\������    
		// Result(){
		long _answerTimeS = _answerTime / 1000;
		//�t���[���쐬
		/*	frame4=new JFrame("���ʕ\�����");
	frame4.setSize(800,600);
	frame4.setLayout(null);*/
		//������x�{�^���쐬
		JButton button4_1 = new JButton("������x");  
		button4_1.setLocation(130,450);
		button4_1.setSize(200,75);
		button4_1.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		button4_1.addActionListener(this);
		button4_1.setActionCommand("������x");
		frame4.add(button4_1);
		//�I���{�^���쐬
		JButton button4_2 = new JButton("�I��");    
		button4_2.setLocation(470,450);
		button4_2.setSize(200,75);
		button4_2.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		button4_2.addActionListener(this);
		button4_2.setActionCommand("�I��");
		frame4.add(button4_2);
		//���x��4_1�쐬
		Label label4_1 = new Label("�W�������F"+Genre);
		label4_1.setBounds(500,10,300,100);    
		label4_1.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_1);
		//���x��4_2�쐬
		Label label4_2 = new Label("�����ʁ�");
		label4_2.setBounds(300,80,230,80);      
		label4_2.setFont(new Font("MS �S�V�b�N",Font.BOLD,52));
		frame4.add(label4_2);
		//���x��4_3�쐬
		Label label4_3 = new Label("�^�C�s���O������");
		label4_3.setBounds(100,170,300,80);      
		label4_3.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_3);
		//���x��4_4�쐬
		Label label4_4 = new Label(df2.format(_successPercentage)+"%");
		label4_4.setBounds(500,170,300,80);      
		label4_4.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_4);
		//���x��4_5�쐬
		Label label4_5 = new Label("���v����");
		label4_5.setBounds(100,250,300,80);     
		label4_5.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_5);
		//���x��4_6�쐬
		Label label4_6 = new Label(_answerTime/60000+"��"+_answerTimeS%60+"�b");
		label4_6.setBounds(500,250,300,80);      
		label4_6.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_6);
		//���x��4_7�쐬
		Label label4_7 = new Label("���Ȃ��̃����N");
		label4_7.setBounds(100,330,300,80);      
		label4_7.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_7);
		//���x��4_8�쐬
		Label label4_8 = new Label(""+_rank);
		label4_8.setBounds(500,330,300,80);      
		label4_8.setFont(new Font("MS �S�V�b�N",Font.BOLD,32));
		frame4.add(label4_8);
		//�E�B���h�N���[�Y�����ǉ�
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		Mode mm = new Mode();
		if (cmd.equals("������x")){
			frame4.setVisible(false);
			Typing Type = new Typing(mm._genreData);
			Type.showQuestion();
			//JFrame frame = new InputAnswer();
			//frame3�ֈړ�
		}else if(cmd.equals("�I��")){
			//frame4������
			frame4.setVisible(false);
			Mode m = new Mode("���j���[���");

		}
	}
}
