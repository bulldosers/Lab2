package book;

import book.Book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class BookManage implements Serializable{
	
	private static final long serialVersionUID = 2350961706510775057L; //���л�
	public static final int TYPE_NUMBER = 7; // ������7���鼮���� 
    private ArrayList<ArrayList<Book>> bookArray; //Book����������
    private ArrayList<BorrowedBook> borrowedBooks = new ArrayList<BorrowedBook>(); //ͼ��ȥ������
    public ArrayList<BorrowedBook> getBorrowedBooks() {
		return borrowedBooks;
	}

	/**
     * �޲ι�����
     * ���ļ���ȡbookArray��Ϣ
     */
	public BookManage() throws ClassNotFoundException, IOException {
    	bookArray = readBookArray();
    	borrowedBooks = readBookArray0();
    	if(bookArray == null)
    	{
    		bookArray = new ArrayList<ArrayList<Book>>();
    		for(int i=0;i<TYPE_NUMBER;i++)
        		bookArray.add(new ArrayList<Book>());
    	}
    }
    
	/**
	 * �Ǽ�ͼ��ȥ��
	 * @param bBook
	 * @throws IOException
	 */
	public void regesiterBorrow(BorrowedBook bBook) throws IOException
	{
		borrowedBooks.add(bBook);
		writebBookArray();
	}
	/**
	 * ���ͼ�飺������
	 * @param book
	 * @throws IOException
	 */
    public void addBook(Book book) throws IOException {
    	int type = book.getTypes();
		bookArray.get(type).add(book);
		
		writeBookArray();
    }
    
    /**
     * ����������ͼ��
     * @param name
     * @return
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
	public Book searchByName(String name){	
    	for(ArrayList<Book> eArray : bookArray)
    	{
    		if(eArray != null)
    		for(Book e : eArray)
    		{
    			if(e == null) break;
    			if(e.getName()!=null && e.getName().equals(name) )
    			{
    				return e;
    			}
    		}
    	}
    	return null;
    }
  
	/**
	 * ���鼮��Ϊ�ѽ��鼮
	 * @param book
	 * @param loanQuantity
	 * @param loanDate
	 * @param returnDate
	 * @param renewTimes
	 * @return
	 */
	public BorrowedBook changeBookToBorrowedBooks(String name, Date loanDate,
				Date returnDate, int renewTimes) {
		BorrowedBook borrowedBook = new BorrowedBook();
		Book book = searchByName(name);
		if(book == null) return null;
		borrowedBook.setAuthor(book.getAuthor());
		borrowedBook.setName(book.getName());
		borrowedBook.setPrice(book.getPrice());
		borrowedBook.setPress(book.getPress());
		borrowedBook.setNumber(book.getNumber());
		borrowedBook.setTypes(book.getTypes());
		borrowedBook.setReceiveDate(book.getReceiveDate());
		borrowedBook.setLoanDate(loanDate);
		borrowedBook.setReturnDate(returnDate);
		borrowedBook.setRenewTimes(renewTimes);
		return borrowedBook;
	}
	
	/**
	 * ���鼮����ѽ��鼮�������߷�����һ���û��������
	 */
	public BorrowedBook changeBookToBorrowedBooks(String name, Date loanDate,
			Date returnDate,String id) {
	BorrowedBook borrowedBook = new BorrowedBook();
	Book book = searchByName(name);
	if(book == null) return null;
	borrowedBook.setAuthor(book.getAuthor());
	borrowedBook.setName(book.getName());
	borrowedBook.setPrice(book.getPrice());
	borrowedBook.setPress(book.getPress());
	borrowedBook.setNumber(book.getNumber());
	borrowedBook.setTypes(-1);
	borrowedBook.setReceiveDate(book.getReceiveDate());
	borrowedBook.setLoanDate(loanDate);
	borrowedBook.setReturnDate(returnDate);
	borrowedBook.setRenewTimes(0);
	borrowedBook.setId(id);
	return borrowedBook;
}
	/**
	 * ����Ǽǣ����ö��߱�ź�����ƥ��ɾ��
	 * @param id
	 * @param name
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void deleteBorrowedBooks(String id,String name) throws IOException
	{
		ArrayList<BorrowedBook> k = (ArrayList<BorrowedBook>) borrowedBooks.clone();
		for(BorrowedBook e : k)
		{
			if(e == null ) continue;
			if(e.getName().equals(name) && e.getId().equals(id))
			{
				borrowedBooks.remove(e);
			}
		}
		writebBookArray();
	}
    /**
     * �����Ͳ���ͼ��
     * @param type
     * @param name
     * @return
     */
	public Book searchByType(int type,String name)
    {		
    	for(Book e : bookArray.get(type))
		{
			if(e.getName().equals(name) )
			{
				return e;
			}
		}
    	return null;
    }
    
	/**
	 * ����Ų���ͼ��
	 * @param number
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
    public Book searchByNumber(String number)
    {
    	for(ArrayList<Book> eArray : bookArray)
    	{
    		for(Book e : eArray)
    		{
    			if(e.getNumber().equals(number) )
    			{
    				return e;
    			}
    		}
    	}
    	return null;
    }
    
    /**
     * ������ɾ��ͼ��
     * @param name
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void deleteBook(String name) throws ClassNotFoundException, IOException
    {
    	Book book = this.searchByName(name);
    	if(book != null)
    	{
    		int type = book.getTypes();
    		bookArray.get(type).remove(book);
    		writeBookArray();
    	}
    }
    
    /**
     * �޸�ͼ����Ϣ
     * @param book
     * @param newBook
     * @throws IOException 
     */
    public void modifyBook(Book book,Book newBook) throws IOException
    {
    	int type = book.getTypes();
    	bookArray.get(type).remove(book);
    	bookArray.get(type).add(newBook);

    	writeBookArray();
    }
    
    /**
     * �����鼮����
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public boolean addBookNumber(String name) throws ClassNotFoundException, IOException
    {
    	Book book = this.searchByName(name);
    	if(book != null)
    	{
    		int nowSum = book.getNowSum();
    		book.setNowSum(nowSum+1);
    		writeBookArray();
    	    return true;
    	}
    	return false;
    }
    
    /**
     * ����ͼ��
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public boolean subBookNumber(String name) throws ClassNotFoundException, IOException
    {
    	Book book = this.searchByName(name);
    	if(book != null)
    	{
    		int nowSum = book.getNowSum();
    		if(nowSum>0) {
    			book.setNowSum(nowSum-1);
    			writeBookArray();
    			return true;
    		}
    		else return false;
    	}
    	return false;
    }
    
	/**
	 * ���ļ��ж�ȡbook
	 * @param name
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
    @SuppressWarnings("unchecked")
	private ArrayList<ArrayList<Book>> readBookArray() throws IOException, ClassNotFoundException {
		File file = new File("data\\book.dat");
		if(!file.exists())
			return null;
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        //if(oin==null) return null;
		bookArray = (ArrayList<ArrayList<Book>>) oin.readObject(); 
        oin.close();
		return bookArray;
	}
    
    /**
     * ��ȡͼ��ȥ����Ϣ
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
	private ArrayList<BorrowedBook> readBookArray0() throws IOException, ClassNotFoundException {
		File file = new File("data\\borrowedBook.dat");
		if(!file.exists())
			return new ArrayList<BorrowedBook>();
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        //if(oin==null) return null;
		borrowedBooks = (ArrayList<BorrowedBook>) oin.readObject(); 
        oin.close();
		return borrowedBooks;
	}
    /**
     * ���ͼ�鲢д���ļ�
     * @param bookArray2
     * @throws IOException
     */
    private void writeBookArray() throws IOException {
		// TODO Auto-generated method stub
    	File file = new File("data\\book.dat");
		if (!file.exists())
			file.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));        
		out.writeObject(bookArray);
		out.close();
	}
    
    /**
     *ͼ��ȥ��д���ļ�
     * @throws IOException
     */
    private void writebBookArray() throws IOException {
		// TODO Auto-generated method stub
    	File file = new File("data\\borrowedBook.dat");
		if (!file.exists())
			file.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));        
		out.writeObject(borrowedBooks);
		out.close();
	}
    
    /**
     *һ������
     * @throws IOException
     */
    public void write() throws IOException {
		// TODO Auto-generated method stub
    	File file = new File("dat\\borrowedBook.dat");
		if (!file.exists())
			file.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));        
		out.writeObject(borrowedBooks);
		out.close();
		
		File file0 = new File("dat\\book.dat");
		if (!file0.exists())
			file0.createNewFile();
		ObjectOutputStream out0 = new ObjectOutputStream(new FileOutputStream(file0));        
		out0.writeObject(bookArray);
		out0.close();
	}
    
    /**
     * һ����ԭ
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
	public void read() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
    	File file = new File("dat\\borrowedBook.dat");
		if (!file.exists())
			{
				file.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));        
				out.writeObject(new ArrayList<BorrowedBook>());
				out.close();
			}
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));        
		borrowedBooks = (ArrayList<BorrowedBook>) in.readObject();
		in.close();
		
		File file0 = new File("dat\\book.dat");
		if (!file0.exists())
			{
				file0.createNewFile();
				ObjectOutputStream out0 = new ObjectOutputStream(new FileOutputStream(file0));        
				out0.writeObject(new ArrayList<ArrayList<Book>>());
				out0.close();
			}
		ObjectInputStream in0 = new ObjectInputStream(new FileInputStream(file0));        
		bookArray = (ArrayList<ArrayList<Book>>) in0.readObject();
		in0.close();
	}
    
    public ArrayList<ArrayList<Book>> getBookArray() {
		return bookArray;
	}
    
}
