package com.internousdev.hibiscus.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.ProductInfoDAO;
import com.internousdev.hibiscus.dto.ProductInfoDTO;
import com.internousdev.hibiscus.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品検索機能
 * @author Yoshiki Nagai
 *
 */
public class SearchItemAction extends ActionSupport implements SessionAware {
	// Value Stack, session
	private Map<String, Object> session;
	private String categoryId;
	private String keywords;
	private List<String> keywordsErrorMessageList = new ArrayList<>();

	// private field
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<>();
	private ProductInfoDAO productInfoDAO = new ProductInfoDAO();
	private InputChecker inputChecker = new InputChecker();

	public String execute() {
		if(!session.containsKey("mCategoryDtoList")){ return "session"; }

		// -- 文字列の整形処理 -- //

		// Blankならば空文字を入れる
		if (StringUtils.isBlank(keywords)){
			keywords = "";
		}else{
			// replaceAll("　"," ") => 全角空白を半角空白に置換
			// trim => 文字列前後の空白削除
			// replaceAll(" {2,}", " ") => ２文字以上の半角空白を１文字の半角空白に置換
			keywords = keywords.replaceAll("　", " ").trim().replaceAll(" {2,}", " ");
		}

		// -- 文字列の入力チェック -- //

		if(!keywords.isEmpty()){
			keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords, 0, 16,
					true, true, true, true, false, true, false, true, true);

			// エラーが存在するならreturn
			if(!keywordsErrorMessageList.isEmpty()){ return SUCCESS; }
		}

		// -- 検索文字列を用いた商品一覧の取得 -- //
		switch(categoryId){
		case "1":
			productInfoDtoList = productInfoDAO.getProductInfoListAll(keywords.split(" "));
			break;
		default:
			productInfoDtoList = productInfoDAO.getProductInfoListByKeywords(keywords.split(" "), categoryId);
			break;
		}

		session.put("productInfoDtoList", productInfoDtoList);

		return SUCCESS;
	}

	// -- Getter, Setter -- //

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> sesison) {
		this.session = sesison;
	}

	public List<String> getKeywordsErrorMessageList() {
		return keywordsErrorMessageList;
	}

	public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList) {
		this.keywordsErrorMessageList = keywordsErrorMessageList;
	}
}
