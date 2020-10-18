import java.awt.*; 
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class Mode extends JFrame implements ActionListener{
	static int _backData, _rePrintData, _clickData, _genreData;
	/*    public void displayMenu(){
	int _backData, _rePrintData, _clickData;
	}*/
	JLabel label;
	static Mode frame1;
	public static void main(String[] args){
		frame1 = new Mode("ƒƒjƒ…[‰æ–Ê");
		frame1.setVisible(true);
	}
	Mode (){
	}
	Mode(String title) {
		Toolkit tk = getToolkit();
		setVisible(true);
		setTitle(title);
		setBounds((tk.getScreenSize().width/2)-400, (tk.getScreenSize().height/2)-300, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		JPanel p = new JPanel();
		p.setLayout(null);

		JLabel label1 = new JLabel("ƒeƒXƒg", JLabel.CENTER);
		label1.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 20));
		label1.setBounds(138,70,160,25);
		label1.setBackground(Color.white);
		label1.setOpaque(true);
		label1.setBorder(new LineBorder(Color.BLACK));

		JLabel label2 = new JLabel("’Ç‰Á/íœ", JLabel.CENTER);
		label2.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 20));
		label2.setBounds(485,70,160,25);
		label2.setBackground(Color.white);
		label2.setOpaque(true);
		label2.setBorder(new LineBorder(Color.BLACK));

		label = new JLabel("");
		label.setHorizontalAlignment(JLabel.CENTER);
		//	label.setBounds(400,500,160,25);

		p.add(label1); p.add(label2); p.add(label);

		JButton button1=new JButton("‰p’PŒê");
		button1.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 40));
		button1.addActionListener(this);
		button1.setActionCommand("‰p’PŒê");
		JButton button2=new JButton("“ï“ÇŠ¿š");
		button2.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 40));
		button2.addActionListener(this);
		button2.setActionCommand("“ï“ÇŠ¿š");
		JButton button3=new JButton("“ú–{•¶");
		button3.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 40));
		button3.addActionListener(this);
		button3.setActionCommand("“ú–{•¶");
		JButton button4=new JButton("‰p’PŒê");
		button4.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 40)); 
		button4.addActionListener(this);
		button4.setActionCommand("‰p’PŒê2");
		JButton button5=new JButton("“ï“ÇŠ¿š");
		button5.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 40));
		button5.addActionListener(this);
		button5.setActionCommand("“ï“ÇŠ¿š2");
		JButton button6=new JButton("“ú–{•¶");
		button6.setFont(new Font("‚l‚r ƒSƒVƒbƒN", Font.BOLD, 40));
		button6.addActionListener(this);
		button6.setActionCommand("“ú–{•¶2");
		button1.setLocation(105,125);	button1.setSize(225,100);
		button2.setLocation(105,250);   button2.setSize(225,100);
		button3.setLocation(105,375);   button3.setSize(225,100);
		button4.setLocation(450,125);   button4.setSize(225,100);
		button5.setLocation(450,250);   button5.setSize(225,100);
		button6.setLocation(450,375);   button6.setSize(225,100);
		p.add(button1);    p.add(button2);    p.add(button3);
		p.add(button4);    p.add(button5);    p.add(button6);

		Canvas c = new Canvas(){
			public void paint(Graphics g){
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.white);
				g2.fillRoundRect(66, 83, 303, 430, 115, 115);
				g2.fillRoundRect(410, 83, 303, 430, 115, 115);

				g2.setColor(Color.black);
				g2.drawRoundRect(66, 83, 303, 430, 115, 115);
				g2.drawRoundRect(410, 83, 303, 430, 115, 115);
			}
		};
		c.setBounds(0, 0, 800, 600);
		p.add(c);

		cp.add(p, BorderLayout.CENTER);
		cp.add(label, BorderLayout.PAGE_END);
	}

	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();

		setVisible(false);

		if (cmd.equals("‰p’PŒê")){
			_genreData = 1;
			Typing Type = new Typing(_genreData);
			Type.showQuestion();
		}else if (cmd.equals("“ï“ÇŠ¿š")){
			_genreData = 2;
			Typing Type = new Typing(_genreData);
			Type.showQuestion();
		}else if (cmd.equals("“ú–{•¶")){
			_genreData = 3;
			Typing Type = new Typing(_genreData);
			Type.showQuestion();
		}else	if (cmd.equals("‰p’PŒê2")){
			_genreData = 4;            
			Question Q = new Question(_genreData);
			Q.printQestion();
		}else if (cmd.equals("“ï“ÇŠ¿š2")){
			_genreData = 5;            
			Question Q = new Question(_genreData);
			Q.printQestion();
		}else if (cmd.equals("“ú–{•¶2")){
			_genreData = 6;            
			Question Q = new Question(_genreData);
			Q.printQestion();
		}
	}
}