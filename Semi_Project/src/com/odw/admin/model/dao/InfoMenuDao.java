package com.odw.admin.model.dao;

import static com.odw.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.attachment.model.vo.Attachment;
import com.odw.common.model.vo.PageInfo;
import com.odw.information.model.vo.Information;


public class InfoMenuDao {
	
	private Properties prop = new Properties();
	
	public InfoMenuDao() {
		String fileName = MemberMenuDao.class.getResource("/sql/admin/infoMenu-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectSeasonListCount(Connection conn, Information info) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectSeasonListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getSeason());
			pstmt.setString(2, info.getInfoDeleteYn());

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Information> selectSeasonInfoList(Connection conn, PageInfo pi, Information information) {
		ArrayList<Information> infoList = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectSeasonInfoList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, information.getSeason());
			pstmt.setString(2, information.getInfoDeleteYn());
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Information info = new Information();
				
				info.setInfoNo(rset.getInt("INFO_NO"));
				info.setInfoType(rset.getString("INFO_TYPE"));
				info.setType(rset.getString("TYPE"));
				info.setInfoWriterNo(rset.getString("MEM_ID"));
				info.setInfoTitle(rset.getString("INFO_TITLE"));
				info.setInfoContent(rset.getString("INFO_CONTENT"));
				info.setInfoMedia(rset.getString("INFO_MEDIA"));
				info.setInfoCreateDate(rset.getDate("INFO_CREATE_DATE"));
				info.setInfoCount(rset.getInt("INFO_COUNT"));
				info.setInfoDeleteYn(rset.getString("INFO_DELETE_YN"));
				info.setSeason(rset.getString("SEASON"));
				info.setLocal(rset.getString("LOCAL"));
				info.setTurnAround(rset.getInt("TURNAROUND"));
				info.setCourse(rset.getString("COURSE"));
				info.setTraffic(rset.getString("TRAFFIC"));
				
				infoList.add(info);
			}        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return infoList;
	}
	
	public int insertLocalInfo(Connection conn, Information info) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertLocalInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(info.getInfoWriterNo()));
			pstmt.setString(2, info.getInfoTitle());
			pstmt.setString(3, info.getLocal());
			pstmt.setInt(4, info.getTurnAround());
			pstmt.setString(5, info.getCourse());
			pstmt.setString(6, info.getTraffic());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertNewAttachment(Connection conn, Attachment at, int infoNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, infoNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Information selectInformation(Connection conn, int infoNo) {
		Information info = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectInformation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, infoNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				info = new Information();
				info.setInfoNo(rset.getInt("INFO_NO"));
				info.setInfoType(rset.getString("INFO_TYPE"));
				info.setType(rset.getString("TYPE"));
				info.setInfoWriterNo(rset.getString("MEM_ID"));
				info.setInfoTitle(rset.getString("INFO_TITLE"));
				info.setInfoContent(rset.getString("INFO_CONTENT"));
				info.setInfoCreateDate(rset.getDate("INFO_CREATE_DATE"));
				info.setInfoCount(rset.getInt("INFO_COUNT"));
				info.setInfoDeleteYn(rset.getString("INFO_DELETE_YN"));
				info.setSeason(rset.getString("SEASON"));
				info.setLocal(rset.getString("LOCAL"));
				info.setTurnAround(rset.getInt("TURNAROUND"));
				info.setCourse(rset.getString("COURSE"));
				info.setTraffic(rset.getString("TRAFFIC"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return info;
	}
	
	public Attachment selectAttachment(Connection conn, int infoNo) {
		Attachment at = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, infoNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setCreateDate(rset.getDate("CREATE_DATE"));
				at.setStatus(rset.getString("STATUS"));
				at.setInfoNo(rset.getInt("INFO_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return at;
	}
	
	public int updateLocalInfo(Connection conn, Information info) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateLocalInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getInfoTitle());
			pstmt.setString(2, info.getLocal());
			pstmt.setInt(3, info.getTurnAround());
			pstmt.setString(4, info.getCourse());
			pstmt.setString(5, info.getTraffic());
			pstmt.setInt(6, info.getInfoNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateAttachment(Connection conn, Attachment at, int infoNo) {
		//System.out.println("들어왔나222");
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, infoNo);
			
			//System.out.println(infoNo);
//			System.out.println(at.getOriginName());
//			System.out.println(at.getChangeName());
//			System.out.println(at.getFilePath());
//			System.out.println(at.getInfoNo());
//			
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println(result);
		return result;
	}
	
	public int insertBeginnerInfo(Connection conn, Information info) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBeginnerInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(info.getInfoWriterNo()));
			pstmt.setString(2, info.getInfoTitle());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBeginnerInfo(Connection conn, Information info) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBeginnerInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getInfoTitle());
			pstmt.setInt(2, info.getInfoNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertSeasonInfo(Connection conn, Information info) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertSeasonInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(info.getInfoWriterNo()));
			pstmt.setString(2, info.getInfoTitle());
			pstmt.setString(3, info.getSeason());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateSeasonInfo(Connection conn, Information info) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateSeasonInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getInfoTitle());
			pstmt.setString(2, info.getSeason());
			pstmt.setInt(3, info.getInfoNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteInfoList(Connection conn, int infoNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInfoList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, infoNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteInfo(Connection conn, int infoNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, infoNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectBeginnerListCount(Connection conn, String deleteYn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBeginnerListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deleteYn);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Information> selectBeginnerInfoList(Connection conn, PageInfo pi, String deleteYn){
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Information> infoList = new ArrayList();
		
		String sql = prop.getProperty("selectBeginnerInfoList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, deleteYn);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Information info = new Information();
				
				info.setInfoNo(rset.getInt("INFO_NO"));
				info.setInfoType(rset.getString("INFO_TYPE"));
				info.setType(rset.getString("TYPE"));
				info.setInfoWriterNo(rset.getString("MEM_ID"));
				info.setInfoTitle(rset.getString("INFO_TITLE"));
				info.setInfoContent(rset.getString("INFO_CONTENT"));
				info.setInfoMedia(rset.getString("INFO_MEDIA"));
				info.setInfoCreateDate(rset.getDate("INFO_CREATE_DATE"));
				info.setInfoCount(rset.getInt("INFO_COUNT"));
				info.setInfoDeleteYn(rset.getString("INFO_DELETE_YN"));
				info.setSeason(rset.getString("SEASON"));
				info.setLocal(rset.getString("LOCAL"));
				info.setTurnAround(rset.getInt("TURNAROUND"));
				info.setCourse(rset.getString("COURSE"));
				info.setTraffic(rset.getString("TRAFFIC"));
				
				infoList.add(info);
			}        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return infoList;
	}
	
	public int selectLocalListCount(Connection conn, Information info) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectLocalListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, info.getInfoDeleteYn());
			pstmt.setString(2, info.getLocal());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Information> selectLocalInfoList(Connection conn, PageInfo pi, Information information){
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Information> infoList = new ArrayList();
		
		String sql = prop.getProperty("selectLocalInfoList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, information.getInfoDeleteYn());
			pstmt.setString(2, information.getLocal());
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Information info = new Information();
				
				info.setInfoNo(rset.getInt("INFO_NO"));
				info.setInfoType(rset.getString("INFO_TYPE"));
				info.setType(rset.getString("TYPE"));
				info.setInfoWriterNo(rset.getString("MEM_ID"));
				info.setInfoTitle(rset.getString("INFO_TITLE"));
				info.setInfoContent(rset.getString("INFO_CONTENT"));
				info.setInfoMedia(rset.getString("INFO_MEDIA"));
				info.setInfoCreateDate(rset.getDate("INFO_CREATE_DATE"));
				info.setInfoCount(rset.getInt("INFO_COUNT"));
				info.setInfoDeleteYn(rset.getString("INFO_DELETE_YN"));
				info.setSeason(rset.getString("SEASON"));
				info.setLocal(rset.getString("LOCAL"));
				info.setTurnAround(rset.getInt("TURNAROUND"));
				info.setCourse(rset.getString("COURSE"));
				info.setTraffic(rset.getString("TRAFFIC"));
				
				infoList.add(info);
			}        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return infoList;
	}
	
}
