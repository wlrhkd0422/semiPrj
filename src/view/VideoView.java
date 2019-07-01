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
    JTable tableVideo;//jtable view역할
    
    //추가
    VideoModel model;
    
public VideoView() {
		addLayout();//화면설계
		//초기화 작업
	initStyle();
    eventProc();
    //db연결
    connectDB();//호출
}
void initStyle() {
	tfVideoNum.setEditable(false);//비활성화
	tfInsertCount.setEditable(false);
}
public void connectDB() {
	try {
		model=new VideoModel();
		System.out.println("비디오연결성공");
	} catch (Exception e) {
		System.out.println("비디오연결실패");
	}
}
public void eventProc(){
	//수신기 부착
	cbMultiInsert.addActionListener(this);//다중입고
	bVideoDelete.addActionListener(this);
	bVideoInsert.addActionListener(this);
	bVideoModify.addActionListener(this);
	tfVideoSearch.addActionListener(this);//검색
}
private void addLayout() {
	//탭의 디자인 구성
	tfVideoNum=new JTextField();
	tfVideoTitle=new JTextField();
	tfVideoDirector=new JTextField();
	tfVideoActor=new JTextField();
	
	String[] cbJanreStr= {"멜로","액션","스릴","코미디"};	
	comVideoJanre=new JComboBox(cbJanreStr);
	taVideoContent=new JTextArea();
	
	cbMultiInsert=new JCheckBox("다중입고");
	tfInsertCount=new JTextField("1",5);
	
	//버튼
	bVideoInsert=new JButton("입고");
	bVideoModify=new JButton("수정");
	bVideoDelete=new JButton("삭제");
	
	String[] cbVideoSearch={"제목","감독"};
	comVideoSearch=new JComboBox(cbVideoSearch);
	tfVideoSearch=new JTextField(15);
	
	//화면구성
	JPanel p_west=new JPanel();
	p_west.setLayout(new BorderLayout());
	
	//왼쪽 가운데
	JPanel p_west_center=new JPanel();
	p_west_center.setLayout(new BorderLayout());
	
	//왼쪽 가운데 위쪽
	JPanel p_west_center_north=new JPanel();
	p_west_center_north.setLayout(new GridLayout(5,2));
    p_west_center_north.add(new JLabel("비디오번호"));
    p_west_center_north.add(tfVideoNum);
    
    p_west_center_north.add(new JLabel("장르"));
    p_west_center_north.add(comVideoJanre);
    
    p_west_center_north.add(new JLabel("제목"));
    p_west_center_north.add(tfVideoTitle);
    
    p_west_center_north.add(new JLabel("감독"));
    p_west_center_north.add(tfVideoDirector);

    p_west_center_north.add(new JLabel("배우"));
    p_west_center_north.add(tfVideoActor);
    
    //왼쪽 가운데 가운데 비디오 설명
    JPanel p_west_center_center=new JPanel();
    p_west_center_center.setLayout(new BorderLayout());
    
    p_west_center_center.add(new JLabel("설명"),BorderLayout.WEST);
    p_west_center_center.add(taVideoContent,BorderLayout.CENTER);
    
    //왼쪽 가운데 판넬에 두개의 판넬추가
    p_west_center.add(p_west_center_north,BorderLayout.NORTH);
    p_west_center.add(p_west_center_center,BorderLayout.CENTER);
    
    p_west_center.setBorder(new TitledBorder("비디오정보입력"));//경계선 만들기
    
    //왼쪽 아래
    JPanel p_west_south=new JPanel();;
    p_west_south.setLayout(new GridLayout(2,1));
    
    //왼쪽 아래에 사용될 판넬
    JPanel p_west_south_1=new JPanel();
    p_west_south_1.setLayout(new FlowLayout());
    p_west_south_1.add(cbMultiInsert);
    p_west_south_1.add(tfInsertCount);
    p_west_south_1.add(new JLabel("개"));
    p_west_south_1.setBorder(new TitledBorder("다중입력시선택"));
    
    JPanel p_west_south_2=new JPanel();
    p_west_south_2.setLayout(new GridLayout(1, 3));
    p_west_south_2.add(bVideoInsert);
    p_west_south_2.add(bVideoModify);
    p_west_south_2.add(bVideoDelete);
    
    p_west_south.add(p_west_south_1);
    p_west_south.add(p_west_south_2);
    
    p_west.add(p_west_center,BorderLayout.CENTER);
    p_west.add(p_west_south,BorderLayout.SOUTH);
    
    //east 판넬 구성
    JPanel p_east=new JPanel();
    p_east.setLayout(new BorderLayout());
    
    //오른쪽의 위쪽
    JPanel p_east_north=new JPanel();
    p_east_north.add(comVideoSearch);
    p_east_north.add(tfVideoSearch);
 
    //경계썬 만들기
    p_east_north.setBorder(new TitledBorder("비디오검색"));
    p_east.add(p_east_north,BorderLayout.NORTH);
    
    
    setLayout(new GridLayout(1,2));
    add(p_west);
    add(p_east);
}
@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==bVideoInsert) {//입고버튼이 눌리면
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
