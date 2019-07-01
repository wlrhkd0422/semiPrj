package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class search extends JFrame implements ActionListener{
	JLabel name,kou,bou,hp,mp,kakaku;
	JTextField	name1,kou1,bou1,hp1,mp1,kakaku1;
	JButton tsugi,kensaku,kesu;
	JPanel panel;
	public search() {
		setTitle("검색기");
		setSize(800, 500);
		panel=new JPanel();
		name=new JLabel("무기 검색");
		tsugi=new JButton("다음");
		tsugi.addActionListener(this);
		
		
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public static void main(String[] args) {
		new search();
	}
}
