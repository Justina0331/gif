package demo.ex;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

//判斷使用者有沒有input，未輸入給予預設值
public class CustomFocusListener implements FocusListener {
	
	private String hintText;
	private JTextField textField;
	public CustomFocusListener(JTextField jTextField,String hintText) {
		this.textField = jTextField;
		this.hintText = hintText;
		jTextField.setText(hintText);  //默認提示內容
		jTextField.setForeground(Color.GRAY);
	}
 
	@Override
	public void focusGained(FocusEvent e) {
		//使用者若有輸入，清空提示内容
		String temp = textField.getText();
		if(temp.equals(hintText)) {
			textField.setText("");
			textField.setForeground(Color.BLACK);
		}	
	}
 
	@Override
	public void focusLost(FocusEvent e) {	
		//失去輸入內容時，顯示提示內容
		String temp = textField.getText();
		if(temp.equals("")) {
			textField.setForeground(Color.GRAY);
			textField.setText(hintText);
		}
		
	}

}
