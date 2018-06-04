import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	int ln = 10;
	String x = JOptionPane.showInputDialog("Pick a Number");
	int result = Integer.parseInt(x);
	Random choosedaword = new Random();
	String Displayunderscorestuff = "";
	String Choosedawords = "";
	Stack<String> Stackword = new Stack<String>();
	JLabel label1 = new JLabel("Lives:" + ln);
	JLabel label2 = new JLabel();
	JLabel label3 = new JLabel(Displayunderscorestuff);

	public static void main(String[] args) {
		Hangman hm = new Hangman();
	}

	Hangman() {

		JOptionPane.showInputDialog("Write in a number");
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		frame.add(panel);
		panel.add(label1);
		panel.add(label3);
		frame.setVisible(true);
		frame.setSize(800, 500);
		frame.addKeyListener(this);
		for (int i = 0; i < result; i++) {
			try {
				BufferedReader readwords = new BufferedReader(new FileReader("src//dictionary.txt"));
				int randomwords = choosedaword.nextInt(2999);
				for (int j = 0; i < randomwords; i++) {
					readwords.readLine();
				}
				String line = readwords.readLine();
				Stackword.push(line);
				while (line != null) {
					line = readwords.readLine();
				}

				readwords.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Choosedawords = Stackword.pop();
		System.out.println(Choosedawords);
		for (int k = 0; k < Choosedawords.length(); k++) {
			Displayunderscorestuff += "_";
		}
		label3.setText(Displayunderscorestuff);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		String Empty = "";
		System.out.println(Choosedawords);
		for (int w = 0; w < Stackword.size(); w++) {
			if (e.getKeyChar() == Choosedawords.charAt(w)) {
				Empty += Choosedawords.charAt(w);
				
			} else {
				Empty += Displayunderscorestuff.charAt(w);
			}
		}
		Displayunderscorestuff = Empty;
		label3.setText(Displayunderscorestuff);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
