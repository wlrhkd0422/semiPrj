package vidioShop;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.VideoView;

public class VideoShop extends JFrame{
	VideoView video;
	public VideoShop() {
		video=new VideoView();//��ü����
		//���߰�
		JTabbedPane pane=new JTabbedPane();
		pane.add("��������", video);
		pane.setSelectedIndex(0);
		add("Center",pane);
		setSize(800,600);
		setVisible(true);
	}
	public static void main(String[] args) {
			
		new VideoShop();
	}
}
