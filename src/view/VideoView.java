package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.VideoModel;
import model.vo.Video;

public class VideoView extends Panel implements ActionListener{
	JTextField tfVideoNum,tfVideoTitle,tfVideoDirector,tfVideoActor;
	JComboBox comVideoJanre;
	JTextArea taVideoContent;
	
	JCheckBox cbMultiInsert;
	JTextField tfInsertCount;
	
	JButton bVideoInsert,bVideoModify,bVideoDelete;

    JComboBox comVideoSearch;
    JTextField tfVideoSearch;
    JTable tableVideo;//jtable view����
    
    //�߰�
    VideoModel model;
    
public VideoView() {
		addLayout();//ȭ�鼳��
		//�ʱ�ȭ �۾�
	initStyle();
    eventProc();
    //db����
    connectDB();//ȣ��
}
void initStyle() {
	tfVideoNum.setEditable(false);//��Ȱ��ȭ
	tfInsertCount.setEditable(false);
}
public void connectDB() {
	try {
		model=new VideoModel();
		System.out.println("�������Ἲ��");
	} catch (Exception e) {
		System.out.println("�����������");
	}
}
public void eventProc(){
	//���ű� ����
	cbMultiInsert.addActionListener(this);//�����԰�
	bVideoDelete.addActionListener(this);
	bVideoInsert.addActionListener(this);
	bVideoModify.addActionListener(this);
	tfVideoSearch.addActionListener(this);//�˻�
}
private void addLayout() {
	//���� ������ ����
	tfVideoNum=new JTextField();
	tfVideoTitle=new JTextField();
	tfVideoDirector=new JTextField();
	tfVideoActor=new JTextField();
	
	String[] cbJanreStr= {"���","�׼�","����","�ڹ̵�"};	
	comVideoJanre=new JComboBox(cbJanreStr);
	taVideoContent=new JTextArea();
	
	cbMultiInsert=new JCheckBox("�����԰�");
	tfInsertCount=new JTextField("1",5);
	
	//��ư
	bVideoInsert=new JButton("�԰�");
	bVideoModify=new JButton("����");
	bVideoDelete=new JButton("����");
	
	String[] cbVideoSearch={"����","����"};
	comVideoSearch=new JComboBox(cbVideoSearch);
	tfVideoSearch=new JTextField(15);
	
	//ȭ�鱸��
	JPanel p_west=new JPanel();
	p_west.setLayout(new BorderLayout());
	
	//���� ���
	JPanel p_west_center=new JPanel();
	p_west_center.setLayout(new BorderLayout());
	
	//���� ��� ����
	JPanel p_west_center_north=new JPanel();
	p_west_center_north.setLayout(new GridLayout(5,2));
    p_west_center_north.add(new JLabel("������ȣ"));
    p_west_center_north.add(tfVideoNum);
    
    p_west_center_north.add(new JLabel("�帣"));
    p_west_center_north.add(comVideoJanre);
    
    p_west_center_north.add(new JLabel("����"));
    p_west_center_north.add(tfVideoTitle);
    
    p_west_center_north.add(new JLabel("����"));
    p_west_center_north.add(tfVideoDirector);

    p_west_center_north.add(new JLabel("���"));
    p_west_center_north.add(tfVideoActor);
    
    //���� ��� ��� ���� ����
    JPanel p_west_center_center=new JPanel();
    p_west_center_center.setLayout(new BorderLayout());
    
    p_west_center_center.add(new JLabel("����"),BorderLayout.WEST);
    p_west_center_center.add(taVideoContent,BorderLayout.CENTER);
    
    //���� ��� �ǳڿ� �ΰ��� �ǳ��߰�
    p_west_center.add(p_west_center_north,BorderLayout.NORTH);
    p_west_center.add(p_west_center_center,BorderLayout.CENTER);
    
    p_west_center.setBorder(new TitledBorder("���������Է�"));//��輱 �����
    
    //���� �Ʒ�
    JPanel p_west_south=new JPanel();;
    p_west_south.setLayout(new GridLayout(2,1));
    
    //���� �Ʒ��� ���� �ǳ�
    JPanel p_west_south_1=new JPanel();
    p_west_south_1.setLayout(new FlowLayout());
    p_west_south_1.add(cbMultiInsert);
    p_west_south_1.add(tfInsertCount);
    p_west_south_1.add(new JLabel("��"));
    p_west_south_1.setBorder(new TitledBorder("�����Է½ü���"));
    
    JPanel p_west_south_2=new JPanel();
    p_west_south_2.setLayout(new GridLayout(1, 3));
    p_west_south_2.add(bVideoInsert);
    p_west_south_2.add(bVideoModify);
    p_west_south_2.add(bVideoDelete);
    
    p_west_south.add(p_west_south_1);
    p_west_south.add(p_west_south_2);
    
    p_west.add(p_west_center,BorderLayout.CENTER);
    p_west.add(p_west_south,BorderLayout.SOUTH);
    
    //east �ǳ� ����
    JPanel p_east=new JPanel();
    p_east.setLayout(new BorderLayout());
    
    //�������� ����
    JPanel p_east_north=new JPanel();
    p_east_north.add(comVideoSearch);
    p_east_north.add(tfVideoSearch);
 
    //���� �����
    p_east_north.setBorder(new TitledBorder("�����˻�"));
    p_east.add(p_east_north,BorderLayout.NORTH);
    
    
    setLayout(new GridLayout(1,2));
    add(p_west);
    add(p_east);
}
@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==bVideoInsert) {//�԰��ư�� ������
			Video vo=new Video();
			vo.setGenre((String) comVideoJanre.getSelectedItem());
			vo.setActor(tfVideoActor.getText());
			vo.setDirector(tfVideoDirector.getText());
			vo.setExp(taVideoContent.getText());
			vo.setVideoName(tfVideoTitle.getText());
			
			int count=Integer.parseInt(tfInsertCount.getText());
			
			try {
				model.insertVideo(vo,count);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       }
	}

}
