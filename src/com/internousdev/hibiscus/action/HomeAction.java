package com.internousdev.hibiscus.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.MCategoryDAO;
import com.internousdev.hibiscus.dto.MCategoryDTO;
import com.internousdev.hibiscus.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ホーム画面
 * @author Yoshiki Nagai
 *
 */
public class HomeAction extends ActionSupport implements SessionAware {
	// Value Stack , Session
	private Map<String, Object> session;
	private List<MCategoryDTO> mCategoryDtoList;

	// private field
	private MCategoryDAO mCategoryDAO = new MCategoryDAO();

	public String execute() {

		if(session == null) {
			session = new HashMap<>();
		}

		// idが存在しない初めてのユーザーなら一時的にidを付与する
		if (!session.containsKey("loginId") && !session.containsKey("tempUserId")) {
			 CommonUtility commonUtility = new CommonUtility();
			 session.put("tempUserId", commonUtility.getRamdomValue());
		}

		// loginedをセッションに追加
		// TODO:不要な可能性あり
		if(!session.containsKey("logined")) {
			session.put("logined", 0);
		}

		// カテゴリーリストがセッションにない時はDAOから持ってくる
		if(!session.containsKey("mCategoryDtoList")){
			mCategoryDtoList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}
		return SUCCESS;
	}

	// -- Getter, Setter -- //

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<MCategoryDTO> getmCategoryDtoList() {
		return mCategoryDtoList;
	}

	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
		this.mCategoryDtoList = mCategoryDtoList;
	}

}
