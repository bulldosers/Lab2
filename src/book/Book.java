package book;

import java.io.Serializable;

public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5298528809857960577L; 	//���л� �� ��д�ļ���Ҫ
	private String number;		//���
	private String author;		//����
	private String name;		//����
	private String press;		//������
	private int sumOfBooks;		//���������
	private int nowSum;			//��ǰ������
	private String receiveDate;	//�������   ��Ϊ���漰�������㣬����ʹ���ַ�������
	private int types;			//����
	private float price;		//�۸�
	
	public Book() {}  
	
	/**
	 * ���췽��
	 * @param number
	 * @param name
	 * @param author
	 * @param press
	 * @param types
	 * @param sumOfBook
	 * @param receiveDate
	 * @param price
	 */
	public Book(String number,String name, String author, String press,  
			int types, int sumOfBook,String receiveDate,float price) {
		this.number = number;
		this.author = author;
		this.name = name;
		this.press = press;
		this.types = types;
		this.sumOfBooks = sumOfBook;
		this.nowSum = sumOfBook;
		this.receiveDate = receiveDate;
		this.price = price;
	}

	/**
	 * get ��  set ����
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	public int getSumOfBooks() {
		return sumOfBooks;
	}

	public void setSumOfBooks(int sumOfBooks) {
		this.sumOfBooks = sumOfBooks;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}
	
	public String toString(int x)
	{
		return toString();
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNowSum() {
		return nowSum;
	}
	public void setNowSum(int nowSum) {
		this.nowSum = nowSum;
	}
	
	/**
	 * �����ַ������飬���ڴ�ӡ���
	 * @return
	 */
	public String[] toStrings()
	{
		String[] strings = { name,number,press,author,String.valueOf(price),String.valueOf(types),String.valueOf(nowSum),String.valueOf(sumOfBooks),receiveDate};
		return strings;
	}
}
