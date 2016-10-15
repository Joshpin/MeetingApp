package com.meeting.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

import com.meeting.services.MeetingUtilities;

@PersistenceCapable
public class Meeting {
	
	@PrimaryKey
	@Persistent
	private Key key;
	
	@Persistent
	private String meetingName;
	
	@Persistent
	private Date startingTime;
	
	@Persistent
	private Date endTime;
	
	@Persistent
	private ArrayList<String> people;
	
	@Persistent
	private ArrayList<HashMap<String,MeetingUtilities>> questions;
	
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ArrayList<String> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<String> people) {
		this.people = people;
	}

	public ArrayList<HashMap<String, MeetingUtilities>> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<HashMap<String, MeetingUtilities>> questions) {
		this.questions = questions;
	}

	
	

}
