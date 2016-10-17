package com.buxiaoyu.action;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.buxiaoyu.dao.authorDao;
import com.buxiaoyu.dao.bookDao;
import com.buxiaoyu.model.Author;
import com.buxiaoyu.model.AuthorArray;
import com.buxiaoyu.model.Book;
import com.buxiaoyu.model.BookArray;

public class bookAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private ArrayList<BookArray> bookManager; 
	private ArrayList<Book> books;
	private AuthorArray autherManager;
	private ArrayList<Author> authors;
	private bookDao bookDAO = bookDao.getbookDao();
	private int rp = 0;
	private authorDao authorDAO = authorDao.getauthorDao();
	private String isbn;
	private String fu;
	private String[] isbns;
	private String searchAuthorName;
	private int finalAuthorID;
	private int sameAuthorCnt;
	private int authorID;
	private String lastInputPage;
	private Book newBook;
	private Author newAuthor;

	private int year, month, day;
	private boolean modify;
	private Map<String, Object> session;
	public ArrayList<Book> getBooks()
	{
		return books;
	}
	public void setBooks(ArrayList<Book> books)
	{
		this.books = books;
	}
	public String updateBook() throws SQLException
	{ 
		System.out.println("update!");
		newBook = bookDAO.getBookByISBN(isbn);
		String[] dateStrs = newBook.getPublishDate().split("-");
		setYear(Integer.parseInt(dateStrs[0]));
		setMonth(Integer.parseInt(dateStrs[1]));
		setDay(Integer.parseInt(dateStrs[2])); 
		return SUCCESS;
	}

	public String updateAuthor() throws SQLException
	{  
		newAuthor = authorDAO.getAuthorByAuthorID(authorID); 
		return SUCCESS;
	}

	public String indexUpdateBook() throws SQLException
	{ 
		bookDAO.updateBook(newBook); 
		return SUCCESS;
	} 

	public String delBook() throws SQLException
	{
		System.out.println("update!");
		bookDAO.deleteBook(isbn);
		return SUCCESS;
	} 

	public String BooksArray() throws SQLException
	{ 
		System.out.println("update!");
		books = bookDAO.getAllBooks();
		return SUCCESS;
	}

	public String searchBooks() throws SQLException
	{
		authors = authorDAO.getAuthorsByName(searchAuthorName);
		System.out.println("update!");
		sameAuthorCnt = authors.size();
		if (sameAuthorCnt == 0)
		{
			return "noAuthor";
		}
		else if (sameAuthorCnt > 1)
		{
			return "moreThanOne";
		}
		finalAuthorID = authors.get(0).getAuthorID();
		books = bookDAO.getBooksByAuthorID(finalAuthorID);
		if (books.size() == 0) return "noBook";
		return SUCCESS;
	}

	public String commitUpdatedAuthor() throws SQLException 
	{ 
		authorDAO.updateAuthor(newAuthor);
		return SUCCESS;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}

	public String[] getIsbns()
	{
		return isbns;
	}

	public void setIsbns(String[] isbns)
	{
		this.isbns = isbns;
	}

	public String getSearchAuthorName()
	{
		return searchAuthorName;
	}

	public void setSearchAuthorName(String searchAuthorName)
	{
		this.searchAuthorName = searchAuthorName;
	}

	public ArrayList<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors)
	{
		this.authors = authors;
	}

	public int getFinalAuthorID()
	{
		return finalAuthorID;
	}

	public void setFinalAuthorID(int finalAuthorID)
	{
		this.finalAuthorID = finalAuthorID;
	}

	public int getSameAuthorCnt()
	{
		return sameAuthorCnt;
	}

	public void setSameAuthorCnt(int sameAuthorCnt)
	{
		this.sameAuthorCnt = sameAuthorCnt;
	}

	public Book getNewBook()
	{
		return newBook;
	}

	public void setNewBook(Book newBook)
	{
		this.newBook = newBook;
	}

	public Author getNewAuthor()
	{
		return newAuthor;
	}

	public void setNewAuthor(Author newAuthor)
	{
		this.newAuthor = newAuthor;
	}

	public String addBook() throws SQLException
	{ 
		try
		{
			if (newAuthor != null)
			{
				session = ActionContext.getContext().getApplication();
				setNewBook((Book) session.get("newBook"));
				newAuthor.setAuthorID(getAuthorID()); 
				authorDAO.addAuthor(newAuthor);
			}
			if (newBook != null) bookDAO.addBook(newBook);
		}
		catch (MySQLIntegrityConstraintViolationException e)
		{ 
			session = ActionContext.getContext().getApplication();
			session.put("newBook", newBook);
			return "noAuthor";
		}
		return SUCCESS;
	}

	public static boolean judgeDate(String str)
	{
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			format.setLenient(false);
			format.parse(str);
		}
		catch (ParseException e)
		{
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/*@Override
	public void validate()
	{
		System.out.println("update!");
		if (newAuthor != null)
		{
			lastInputPage = "addAuthorForm";
			if (newAuthor.getName() == null || newAuthor.getName().length() == 0)
			{
				addFieldError("newBook.invail", "输入非法！");
			}

			else if (newAuthor.getCountry() == null || newAuthor.getCountry().length() == 0)
			{
				addFieldError("newBook.invail", "输入非法！");
			}

			else if (newAuthor.getAge() <= 0 || newAuthor.getAge() > 150)
			{
				addFieldError("newBook.invail", "输入非法！");
			}
		}
		else
			if (newBook != null)
			{
				lastInputPage = "addBookForm";
				if (newBook.getIsbn() == null || newBook.getIsbn().length() == 0)
				{
					addFieldError("newBook.invail", "输入非法！");
				}
				else
					if (newBook.getTitle() == null || newBook.getTitle().length() == 0)
					{
						addFieldError("newBook.invail", "输入非法！");
					}
					else
						if (newBook.getPublisher() == null || newBook.getPublisher().length() == 0)
						{
							addFieldError("newBook.invail", "输入非法！");
						}
						else
							try
				{
								if (modify == false && bookDAO.isISBNExist(newBook.getIsbn()))
								{
									addFieldError("newBook.invail", "输入非法！");
								}
								else
									if (newBook.getPrice() <= 0)
									{
										addFieldError("newBook.invail", "输入非法！");
									}
									else
										if (newBook.getAuthorID() <= 0)
										{
											addFieldError("newBook.invail", "输入非法！");
										}
										else
										{
											String dateStr = getYear() + "-" + getMonth() + "-" + getDay();


											if (!judgeDate(dateStr))
											{
												addFieldError("newBook.invail", "输入非法！");
											}
											else
											{
												newBook.setPublishDate(dateStr);
											}

										}
								setAuthorID(newBook.getAuthorID());

				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}

			}
	}*/

	@Override
	public void validate()
	{
		System.out.println("newAuthor is " + newAuthor);
		System.out.println("newBook is " + newBook);


		System.out.println("当前" + (modify?"是":"不是") + "修改中....");
		if (newAuthor != null)
		{
			lastInputPage = "addAuthor";
			if (newAuthor.getName() == null || newAuthor.getName().length() == 0)
			{
				addFieldError("newAuthor.name", "姓名为空！");
			}

			if (newAuthor.getCountry() == null || newAuthor.getCountry().length() == 0)
			{
				addFieldError("newAuthor.country", "国家为空！");
			}

			if (newAuthor.getAge() <= 0 || newAuthor.getAge() > 150)
			{
				addFieldError("newAuthor.age", "年龄非法！");
			}
		}

		if (newBook != null)
		{
			lastInputPage = "Addbook";
			if (newBook.getIsbn() == null || newBook.getIsbn().length() == 0)
			{
				addFieldError("newBook.isbn", "ISBN为空！");
			}

			if (newBook.getTitle() == null || newBook.getTitle().length() == 0)
			{
				addFieldError("newBook.title", "书名为空！");
			}

			if (newBook.getPublisher() == null || newBook.getPublisher().length() == 0)
			{
				addFieldError("newBook.publisher", "出版社为空！");
			}

			try
			{
				if (modify == false && bookDAO.isISBNExist(newBook.getIsbn()))
				{
					addFieldError("newBook.isbn", "该ISBN已存在！");
				}

				if (newBook.getPrice() <= 0)
				{
					addFieldError("newBook.price", "价格不是正数！");
				}

				if (newBook.getAuthorID() <= 0)
				{
					addFieldError("newBook.authorID", "非法输入！");
				}

				String dateStr = getYear() + "-" + getMonth() + "-" + getDay();

				if (!judgeDate(dateStr))
				{
					addFieldError("day", "日期不合法！");
				}
				else
				{
					newBook.setPublishDate(dateStr);
				}

				setAuthorID(newBook.getAuthorID());

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	} 

	public String getLastInputPage()
	{
		return lastInputPage;
	}

	public void setLastInputPage(String lastInputPage)
	{
		this.lastInputPage = lastInputPage;
	}

	public boolean isModify()
	{
		return modify;
	}

	public void setModify(boolean modify)
	{
		this.modify = modify;
	}

	public int getAuthorID()
	{
		return authorID;
	}

	public void setAuthorID(int authorID)
	{
		this.authorID = authorID;
	}

	public ArrayList<BookArray> getBookManager() {
		return bookManager;
	}

	public void setBookManager(ArrayList<BookArray> bookManager) {
		this.bookManager = bookManager;
	}

	public AuthorArray getAutherManager() {
		return autherManager;
	}

	public void setAutherManager(AuthorArray autherManager) {
		this.autherManager = autherManager;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public String getFu() {
		return fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}
}
