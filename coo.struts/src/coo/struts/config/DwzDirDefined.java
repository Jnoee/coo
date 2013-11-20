package coo.struts.config;

/**
 * UI资源定位辅助类。
 */
public class DwzDirDefined {
	private String dir;
	private String std;
	private String dwz;
	private String fix;

	/**
	 * 构造方法。
	 * 
	 * @param contextPath
	 *            应用上下文路径
	 */
	public DwzDirDefined(String contextPath) {
		this.dir = contextPath + "/coo/struts/static/dwz";
		this.std = this.dir + "/std";
		this.dwz = this.dir + "/dwz";
		this.fix = this.dir + "/fix";
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public String getDwz() {
		return dwz;
	}

	public void setDwz(String dwz) {
		this.dwz = dwz;
	}

	public String getFix() {
		return fix;
	}

	public void setFix(String fix) {
		this.fix = fix;
	}

	@Override
	public String toString() {
		return dir;
	}
}
