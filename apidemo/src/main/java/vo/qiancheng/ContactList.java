package vo.qiancheng;

/**
 * 最近通话记录
 * @author 王帅
 * @since 2017年3月3日下午3:54:34
 * CopyRight (c) 2016 RONGDU
 */
public class ContactList {
	
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 最近联系时间
	 */
	private String last_contact_date;
	
	/**
	 * 通话时长
	 */
	private Integer talk_seconds;
	
	/**
	 * 通话次数
	 */
	private Integer talk_cnt;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLast_contact_date() {
		return last_contact_date;
	}

	public void setLast_contact_date(String last_contact_date) {
		this.last_contact_date = last_contact_date;
	}

	public Integer getTalk_seconds() {
		return talk_seconds;
	}

	public void setTalk_seconds(Integer talk_seconds) {
		this.talk_seconds = talk_seconds;
	}

	public Integer getTalk_cnt() {
		return talk_cnt;
	}

	public void setTalk_cnt(Integer talk_cnt) {
		this.talk_cnt = talk_cnt;
	}
}
