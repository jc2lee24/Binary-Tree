import javax.swing.*;

public class Runner extends JFrame {
  public static void main(String[] args) {
    JFrame fr = new JFrame("this might be actually hard");
		Screen sc = new Screen();
		
		fr.add(sc);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);
  }
}