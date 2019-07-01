package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.vo.Video;

public class VideoModel {
	Connection con;
	public VideoModel() throws Exception {
		con=DBcon.getConnection();
	}
	public void insertVideo(Video vo, int count) throws Exception {
		//DB에 insert 시키기
		String sql1="INSERT INTO VINFO(VICODE,TITLE,GENRE,DIRECTOR,ACTOR,DETAIL)"+
				"VALUES(SEQ_VI_CODE.nextval,?,?,?,?,?)";
		
		PreparedStatement ps1=con.prepareStatement(sql1);
		ps1.setString(1, vo.getVideoName());
		ps1.setString(2, vo.getGenre());
		ps1.setString(3, vo.getDirector());
		ps1.setString(4, vo.getActor());
		ps1.setString(5, vo.getExp());
		
		int r1=ps1.executeUpdate();//sql실행
		if(r1==1){
			System.out.println("insert success");
		}
	}
	
}
