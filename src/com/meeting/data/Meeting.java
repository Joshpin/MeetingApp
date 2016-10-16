package com.meeting.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Meeting {
	
	@PrimaryKey
	@Persistent
	private String meetingName = "";

	@Persistent
	private Long startTime = 0L;

	@Persistent
	private Long endTime = 0L;

	@Persistent
	private List<String> people 		=		 new ArrayList<String>();

	@Persistent
	private List<Map<String, Object>> items		=		new ArrayList<Map<String, Object>>();
	
	/*Meeting(String meetingName,Long startTime,Long endTime,List<String> people,List<Map<String,Object>> items){
		
		this.meetingName=meetingName;
		this.startTime=startTime;
		this.endTime=endTime;
		this.people=people;
		this.items=items;
	}*/

	

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public List<String> getPeople() {
		return people;
	}

	public void setPeople(List<String> people) {
		this.people = people;
	}

	public List<Map<String, Object>> getItems() {
		return items;
	}

	public void setItems(List<Map<String, Object>> items) {
		this.items = items;
	}
	
}


