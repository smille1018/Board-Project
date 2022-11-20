package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.bean.BoardVO;
import com.example.util.JDBCUtil;

public class BoardDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	private final String BOARD_INSERT = "insert into BOARD (category, title, writer, photo, content) values (?,?,?,?,?)";
	private final String BOARD_UPDATE = "update BOARD set category=?, title=?, writer=?, photo=?, content=? where sid=?";
	private final String BOARD_DELETE = "delete from BOARD  where sid=?";
	private final String BOARD_SELECT = "select * from BOARD  where sid=?";
	private final String BOARD_LIST = "select * from BOARD order by regdate desc";

	public int insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getCategory());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getWriter());
			stmt.setString(4, vo.getPhoto());
			stmt.setString(5, vo.getContent());
			stmt.executeUpdate();
			return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getCategory());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getWriter());
			stmt.setString(4, vo.getPhoto());
			stmt.setString(5, vo.getContent());
			stmt.setInt(6, vo.getSid());
			
			System.out.println(vo.getCategory() + "-" + vo.getTitle() + "-" + vo.getWriter() + "-" + vo.getPhoto() + "-" + vo.getContent() + "-" + vo.getSid());
			stmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public BoardVO getBoard(int sid) {
		BoardVO one = new BoardVO();
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_SELECT);
			stmt.setInt(1, sid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				one.setSid(rs.getInt("sid"));
				one.setCategory(rs.getString("category"));
				one.setTitle(rs.getString("title"));
				one.setWriter(rs.getString("writer"));
				one.setPhoto(rs.getString("photo"));
				one.setContent(rs.getString("content"));
				one.setRegdate(rs.getDate("regdate"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return one;
	}
	
	public ArrayList<BoardVO> getList(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVO one = new BoardVO();
				one.setSid(rs.getInt("sid"));
				one.setCategory(rs.getString("category"));
				one.setTitle(rs.getString("title"));
				one.setWriter(rs.getString("writer"));
				one.setContent(rs.getString("content"));
				one.setRegdate(rs.getDate("regdate"));
				list.add(one);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	public String getPhotoFilename(int sid) {
		String filename = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_SELECT);
			//stmt = setInt(1,sid);
			rs = stmt.executeQuery();
			if(rs.next()) filename = rs.getString("photo");
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("==> JDBC로 getPhotoFilename() 기능 처리");
		return filename;
	}
}
