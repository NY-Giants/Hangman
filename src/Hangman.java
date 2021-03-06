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
	int wordssolved = 0;
	Random choosedaword = new Random();
	String Displayunderscorestuff = "";
	String Choosedawords = "";
	Stack<String> Stackword = new Stack<String>();
	JLabel label1 = new JLabel("Lives:" + ln);
	JLabel label2 = new JLabel();
	JLabel label3 = new JLabel(Displayunderscorestuff);
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	ArrayList<Character> letterstyped = new ArrayList<Character>();

	public static void main(String[] args) {
		Hangman hm = new Hangman();
	}

	Hangman() {
		String x = JOptionPane.showInputDialog("Pick a Number");
		int result = Integer.parseInt(x);
		frame.add(panel);
		panel.add(label1);
		panel.add(label3);
		frame.setVisible(true);
		frame.setSize(800, 500);
		frame.addKeyListener(this);
		for (int i = 0; i <= result; i++) {
			try {
				BufferedReader readwords = new BufferedReader(new FileReader("src//dictionary.txt"));
				int randomwords = choosedaword.nextInt(2999);
				for (int j = 0; j < randomwords; j++) {
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

		if (letterstyped.contains(e.getKeyChar())) {

		} else {
			letterstyped.add(e.getKeyChar());
			String Empty = "";
			boolean truefalseword = false;
			for (int w = 0; w < Choosedawords.length(); w++) {
				if (e.getKeyChar() == Choosedawords.charAt(w)) {
					Empty += Choosedawords.charAt(w);
					truefalseword = true;
					letterstyped.clear();
				} else {
					Empty += Displayunderscorestuff.charAt(w);
				}

			}
			if (truefalseword == false) {
				ln--;
				label1.setText("Lives: " + ln);
			}
			if (ln == 0) {
				System.out.println("You solved " + wordssolved + " words. Nice Job!");
				System.exit(0);
			}

			Displayunderscorestuff = Empty;
			label3.setText(Displayunderscorestuff);
		}
		if (Displayunderscorestuff.equals(Choosedawords)) {
			Displayunderscorestuff = "";
			if (Stackword.size() > 0) {
				Choosedawords = Stackword.pop();
			}
			if (Stackword.isEmpty()) {
				JOptionPane.showInputDialog("You solved all the words!" + " Click below to exit the program");
				System.exit(0);

			}

			wordssolved++;
			for (int k = 0; k < Choosedawords.length(); k++) {
				Displayunderscorestuff += "_";
				
			}
			label3.setText(Displayunderscorestuff);

		}
		System.out.println(Choosedawords);

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
