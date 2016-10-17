package com.buxiaoyu.model; 

public class Author
{
	private int age;
	private String country; 
	private int authorID;
	private String name;
	public static void main(String args[])
	{
		
	}
	public Author(){}
	public Author(int authorID, String name, int age, String country)
	{
		super();
		this.authorID = authorID;
		this.name = name;
		this.age = age;
		this.country = country;
	}
	public int getAuthorID()
	{
		return authorID;
	}
	public void setAuthorID(int authorID)
	{
		this.authorID = authorID;
	}
	public String getName()
	{
		return name;
	}
	public int getAge()
	{
		return age;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	} 
}