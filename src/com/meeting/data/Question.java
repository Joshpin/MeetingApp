package com.meeting.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Question {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	Long questionID ;
	
	@Persistent 
	String meetingName = "";
	
	@Persistent
	String questionName = "";
	
	@Persistent
	String questionConc = "";
	
	public Long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Long questionID) {
		this.questionID = questionID;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionConc() {
		return questionConc;
	}

	public void setQuestionConc(String questionConc) {
		this.questionConc = questionConc;
	}
}
