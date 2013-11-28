package coo.struts.blank.model;

/**
 * 公司扩展信息。
 */
public class CompanyExtendInfo {
	/** 地址 */
	private String address;
	/** 电话 */
	private String tel;
	/** 传真 */
	private String fax;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
