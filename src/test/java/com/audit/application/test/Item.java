
package com.audit.application.test;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Item {

	private String id;

	private String name;

	private String mfgYear;
	
	private String createdUser;
	private String lastModifiedUser;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ")
	private Date lastModifiedDate;
	
	
	
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm ")
	private Date createdDate;
	

	public Item(String id, String name, String mfgYear, Date createdDate, String createdUser) {
		this.id = id;
		this.name = name;
		this.mfgYear = mfgYear;
		this.setCreatedDate(createdDate);
		this.createdUser = createdUser;
	}
	public Item(String id, String name, String mfgYear) {
		this.id = id;
		this.name = name;
		this.mfgYear = mfgYear;
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the model
	 */
	public String getMfgYear() {
		return mfgYear;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setMfgYear(String mfgYear) {
		this.mfgYear = mfgYear;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}