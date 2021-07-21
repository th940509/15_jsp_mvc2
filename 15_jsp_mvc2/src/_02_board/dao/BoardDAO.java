package _02_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _02_board.dto.BoardDTO;

public class BoardDAO { // 싱글턴 패턴 생성

	private BoardDAO() {} 
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}

	Connection conn         = null;
	PreparedStatement pstmt = null;
	ResultSet rs            = null;

	public Connection getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 전체 게시글을 조회하는 DAO
	public ArrayList<BoardDTO> getAllBoard() {

		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO model = null;
		
		try {
			
			conn  = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs    = pstmt.executeQuery();
			
			while (rs.next()) { // rs.next가 있을 경우 model 클래스 변수에 대입

				model = new BoardDTO();
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));

				boardList.add(model);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return boardList;
		
	}
	
	// 한개의 게시글을 조회하는 DAO
	public BoardDTO getOneBoard(int num) {

		BoardDTO model = new BoardDTO();

		try {
			
			conn = getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT=READ_COUNT+1 WHERE NUM=?");
			pstmt.setInt(1, num);              // getOneBoard메서드 실행될때 마다 동일한NUM의 게시물 조회수(READ_COUNT)+1
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?"); // NUM이 일치하는 데이터 조회
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt(1)); // 위의 클래스 변수에 getter한 값을 setter
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return model;
		
	}

	// 업데이트할 데이터를 조회하는 DAO
	public BoardDTO getOneUpdateBoard(int num) {

		BoardDTO model = new BoardDTO();

		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return model;
		
	}

	
	// 비밀번호가 일치하는지 검증하는 DAO
	public boolean validMemberCheck(BoardDTO boardDTO) {

		boolean isValidMember = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM BOARD WHERE NUM=? AND PASSWORD=?"); //  패스워드와 num 두개가 모두 일치할 경우의 board값을 조회
			pstmt.setInt(1, boardDTO.getNum());
			pstmt.setString(2, boardDTO.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) 	isValidMember = true; // rs.next()가 존재하면 isValidMember를 true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}

		return isValidMember;
		
	}
	
	// 게시글을 추가하는 DAO
	public void insertBoard(BoardDTO boardDTO) { // dbto가져오기

		try {
			
				conn = getConnection();
				String sql = "INSERT INTO BOARD(WRITER,EMAIL,SUBJECT,PASSWORD,REG_DATE,READ_COUNT,CONTENT)";
					   sql += "VALUES(?, ?, ?, ?, now(), 0, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardDTO.getWriter()); //bdto에서 가져온 값 세팅
				pstmt.setString(2, boardDTO.getEmail());
				pstmt.setString(3, boardDTO.getSubject());
				pstmt.setString(4, boardDTO.getPassword());
				pstmt.setString(5, boardDTO.getContent());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}	
		
	}
	
	// 게시글을 수정하는 DAO
	public boolean updateBoard(BoardDTO boardDTO) {

		boolean isUpdate = false;
		
		try {
			
			if (validMemberCheck(boardDTO)) { // 비밀번호가 일치하는지 검증하는 DAO가 true일 경우
				conn = getConnection();
				pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT=?, CONTENT=? WHERE NUM=?");
				pstmt.setString(1, boardDTO.getSubject());
				pstmt.setString(2, boardDTO.getContent());
				pstmt.setInt(3, boardDTO.getNum());
				pstmt.executeUpdate();
				isUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isUpdate;
		
	}

	// 게시글을 삭제하는 DAO
	public boolean deleteBoard(BoardDTO boardDTO) {

		boolean isDelete = false;
		
		try {
			
			if (validMemberCheck(boardDTO)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
				pstmt.setInt(1, boardDTO.getNum());
				pstmt.executeUpdate();
				isDelete = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isDelete;
		
	}
	
}
