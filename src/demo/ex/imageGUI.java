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
	private JFrame menu; //初始選單
    private JPanel panel;   //圖片區
    private JLabel label;   
    private JButton start;            //控制開始編譯

    //Creates new form makingGIF
    public imageGUI() {
    	initComponents();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponents() {
        menu = new JFrame("image");	//視窗
        menu.setLocationByPlatform(true);
        menu.setSize(800, 600);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLayout(null);			
                    
        label = new JLabel("歡迎來到亭亭TODO", JLabel.CENTER);
        label.setBounds(170,70,400,50);
        label.setFont(new java.awt.Font("Dialog", 1, 40));	//(字體，粗體，大小)
        menu.add(label);                     
       
        start = new JButton("開始");
        start.setFont(new java.awt.Font("Dialog", 1, 40));

        panel = new JPanel();	//面板
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("複數圖片檔"));

        start.setBounds(290,380,180,100);
                    
        menu.add(panel);
        menu.add(start);
        menu.setVisible(true);

        start.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
                //開始編譯
                startPerformed(e);
            }
        });
    }


    private void startPerformed(java.awt.event.ActionEvent evt) {
        
    }
}
