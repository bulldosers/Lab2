package book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 图书信息类包含读者的借书信息
 * @version 1.0
 * @author think
 *
 */
public class BorrowedBook extends Book{
		
	private static final long serialVersionUID = -3868252705658045985L;
	private Date loanDate;			//借阅日期
	private Date returnDate;		//归还日期
	private int renewTimes;			//续借次数
	private String id;   //借阅者id
	
	public BorrowedBook() {}
	
	public BorrowedBook(String number,String name, String author, String press,  
			int types, Date loanDate,Date returnDate,float price) {
		setNumber(number);
		setAuthor(author);
		setName(name);
		setPress(press);
		setTypes(types);
		this.loanDate = loanDate; 
		this.returnDate = returnDate;
		setPrice(price);
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	/**
	 * Get method for loanTime
	 * @return loanTime
	 */
	public Date getLoanDate() {
		return loanDate;
	}
	
	/**
	 * Set method for loanTime
	 * @param loanTime
	 */
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	
	/**
	 * Get method for returnTime
	 * @return returnTime
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	
	/**
	 * Set method for returnTime
	 * @param returnTime
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	/**
	 * Get method for renewTimes
	 * @return renewtTimes
	 */
	public int getRenewTimes() {
		
		return renewTimes;
	}
	
	/**
	 * Set method for renewTimes
	 * @param renewTimes
	 */
	public void setRenewTimes(int renewTimes) {
		this.renewTimes = renewTimes;
	}
	
	/**
	 * 一键续借，默认增加归还日期三十天
	 */
	public void reBorrowed()
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(returnDate);
		cal.add(Calendar.DATE, 30);
		returnDate = cal.getTime();
	}
	
	/**
	 * 生成字符串数组，用于打印信息
	 */
	public String[] toStrings()
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String s0 = df.format(loanDate);
		String s1 = df.format(returnDate);
		String[] strings = { getName(),getNumber(),getPress(),getAuthor(),String.valueOf(getPrice()),String.valueOf(getTypes()),s0,s1,getReceiveDate()};
		return strings;
	}
	
	/**
	 * 打印图书去向字符串
	 * @return
	 */
	public String[] toStrings0()
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String s0 = df.format(loanDate);
		String s1 = df.format(returnDate);
		String[] strings = { getName(),getNumber(),getPress(),getAuthor(),String.valueOf(getPrice()),id,s0,s1,getReceiveDate()};
		return strings;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
