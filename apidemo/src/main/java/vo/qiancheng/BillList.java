package vo.qiancheng;

/**
 * 前程 - 月账单
 * @author 王帅
 * @since 2017年3月3日下午3:53:44
 * CopyRight (c) 2016 RONGDU
 */
public class BillList {
	
	/**
	 * 月份(yymm)
	 */
	private String month;
	
	
	/**
	 * 金额(元)
	 */
	private Float amount;


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	

}
