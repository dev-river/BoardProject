package kr.co.domain;

public class SPageTO<T> extends PageTO<T>{
	
	private String searchType;
	private String keyword;
	
	public SPageTO() { //디폴트 생성자
		super();
	}

	public SPageTO(int curPage, String searchType, String keyword) {
		//Generate Constructors from Superclass로 생성
		super(curPage);
		this.searchType=searchType;
		this.keyword=keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
}
