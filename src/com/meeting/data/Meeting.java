package com.meeting.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Meeting {

	
	private String meetingId		=		"";

	private String meetingName = "";

	private Long startTime = 0L;

	private Long endTime = 0L;

	private List<String> people = new ArrayList<String>();

	private List<Map<String, Object>> items		=		new ArrayList<Map<String, Object>>();

	public String getMeetingId() {
			return meetingId;
		}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

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


