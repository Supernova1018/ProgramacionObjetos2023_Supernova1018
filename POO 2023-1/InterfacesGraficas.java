
import javax.swing.*;
import java.awt.*;
public class InterfacesGraficas {
	int x = 70;
	int y = 70;
	public static void main(String[] args) {
		InterfacesGraficas gui = new InterfacesGraficas();
		gui.go();
	}
	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanel drawPanel = new MyDrawPanel();
		frame.getContentPane().add(drawPanel);
		frame.setSize(600, 500);
		frame.setVisible(true);
		for (int i = 0; i < 130; i++) {
			x++;
			y = y + 2;
			drawPanel.repaint();
			try {
				Thread.sleep(1);
			} catch (Exception ex) {
			}
		}
	}// close go() method
	class MyDrawPanel extends JPanel {

		public void paintComponent(Graphics g) {
			g.setColor(Color.red);
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.green);
			g.fillOval(x, y, 80, 160);
			g.setColor(Color.pink);
			g.fillOval(x, y, 160, 160);

		}
	} // close inner class
} // close outer class