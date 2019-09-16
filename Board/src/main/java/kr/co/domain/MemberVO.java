package kr.co.domain;

import java.io.Serializable;

public class MemberVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int acc;
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String email;
	private String adrs1;
	private String adrs2;
	private String phone;
	private String signDate;
	private int checkSN;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(int acc, String id, String pw, String name, String birth, String email, String adrs1, String adrs2,
			String phone, String signDate, int checkSN) {
		super();
		this.acc = acc;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.adrs1 = adrs1;
		this.adrs2 = adrs2;
		this.phone = phone;
		this.signDate = signDate;
		this.checkSN = checkSN;
	}
	
	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdrs1() {
		return adrs1;
	}

	public void setAdrs1(String adrs1) {
		this.adrs1 = adrs1;
	}

	public String getAdrs2() {
		return adrs2;
	}

	public void setAdrs2(String adrs2) {
		this.adrs2 = adrs2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public int getCheckSN() {
		return checkSN;
	}

	public void setCheckSN(int checkSN) {
		this.checkSN = checkSN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberVO [acc=" + acc + ", id=" + id + ", pw=" + pw + ", name=" + name + ", birth=" + birth
				+ ", email=" + email + ", adrs1=" + adrs1 + ", adrs2=" + adrs2 + ", phone=" + phone + ", signDate="
				+ signDate + ", checkSN=" + checkSN + "]";
	}

	
}
