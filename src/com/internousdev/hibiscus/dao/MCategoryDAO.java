package com.internousdev.hibiscus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.hibiscus.dto.MCategoryDTO;
import com.internousdev.hibiscus.util.DBConnector;

/**
 * カテゴリーマスターDAO
 * @author Yoshiki Nagai
 *
 */
public class MCategoryDAO {

	/**
	 * カテゴリーリストの取得
	 * @return List<MCategoryDTO>
	 */
	public List<MCategoryDTO> getMCategoryList(){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
		String sql = "select * from m_category";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(rs.getInt("id"));
				mCategoryDTO.setCategoryId(rs.getInt("category_id"));
				mCategoryDTO.setCategoryName(rs.getString("category_name"));
				mCategoryDTO.setCategoryDescription(rs.getString("category_description"));
				mCategoryDTO.setInsertDate(rs.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(rs.getDate("update_date"));
				mCategoryDtoList.add(mCategoryDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mCategoryDtoList;
	}
}
