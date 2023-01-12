import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Screen extends JPanel implements ActionListener{

    private BinaryTree<Integer> tree;
    private JButton add;
    private JButton clear;
    private JTextField input;



    public Screen(){
        tree = new BinaryTree<Integer>();
        
        this.setLayout(null);


        add = new JButton();
        add.setText("Add");
        add.setBounds(550, 490, 200, 30);
        this.add(add);
        add.addActionListener(this);

        clear = new JButton();
        clear.setText("clear");
        clear.setBounds(550, 530, 200, 30);
        this.add(clear);
        clear.addActionListener(this);

        input = new JTextField();
        input.setText("num");
        input.setBounds(550, 450, 200, 30);
        this.add(input);

        this.setFocusable(true);
    }

    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        tree.drawMe(g, 400, 50);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == add){
            int addNum = Integer.parseInt(input.getText());
            tree.add(addNum);
        }
        if(e.getSource() == clear){
            tree.clear();
        }
        repaint();
    }


}
