package demo.ex;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class imageGUI {
	private JFrame menu; //��l���
    private JPanel panel;   //�Ϥ���
    private JLabel label;   
    private JButton start;            //����}�l�sĶ

    //Creates new form makingGIF
    public imageGUI() {
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        menu = new JFrame("image");	//����
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);			
                    
        label = new JLabel("�w��Ө�F�FTODO", JLabel.CENTER);
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 40));	//(�r��A����A�j�p)
        menu.add(label);                     
       
        start = new JButton("�}�l");
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//���O
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("�ƼƹϤ���"));

        start.setBounds(290,380,180,100);
                    
        menu.add(panel);
        menu.add(start);
        menu.setVisible(true);

        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //�}�l�sĶ
                startPerformed(e);
            }
        });
    }


    private void startPerformed(java.awt.event.ActionEvent evt) {
        
    }
}
