package com.meeting.services;

import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.dao.MeetingDAO;
import com.meeting.dao.PMF;
import com.meeting.data.Meeting;
import com.meeting.data.Question;

public class MeetingUtilities {

	public static final Logger logger = Logger.getLogger(MeetingUtilities.class.getName());

	public static final ObjectMapper mapper = new ObjectMapper();
	public MeetingDAO meetingDAO = new MeetingDAO();

	public String saveMeeting(String reqString) {

		Map<String, Object> reqMap = new HashMap<String, Object>();
		Map<String, Object> resMap = new HashMap<String, Object>();
		logger.info("In saveMeeting");
		resMap.put("success", false);
		
		try {
			
			reqMap		=		mapper.readValue(reqString, new TypeReference<Map<String, Object>>(){});
			
			if (reqMap != null && !reqMap.isEmpty()) {
				
				List<Question>	ques	=		mapper.convertValue(reqMap.get("items"), new TypeReference<List<Question>>(){});
				reqMap.remove("items");
				
				Meeting m		 = 		mapper.convertValue(reqMap,Meeting.class );	
				
				String meetingName = m.getMeetingName();
				
				for(Question Q : ques){
					
					Q.setMeetingName(meetingName);
				}
				
				if(meetingDAO.saveAll(ques)) {
					resMap.put("success", true);
				} else {
					throw new Exception("Questions didn't persisted");
				}
				
				logger.info("GettingName : "+ m.getMeetingName());
				if(meetingDAO.save(m)){
					resMap.put("success", true);
					logger.info("Response map - success is added as true");
				} else {
					throw new Exception("Meeting persistence failed");
				}
			}	
		} catch (Throwable e) {
			resMap.put("success", false);
			resMap.put("exception", e.getMessage());
			logger.log(Level.ERROR, e.getMessage(), e);
		}
		
		try {
			logger.info("Returning Map as String : " + resMap);
			return mapper.writeValueAsString(resMap);
		} catch (JsonProcessingException e) {
			logger.info("JsonProcessingException in save Meeting");
			return "{\"success\":false,\"exception\":" + e.getMessage() + "}";
		}
	}
	
	public String getMeetingByName(String meetingName){
		
	PersistenceManager pm = null;
	
	Query q				  = null;
	Map<String, Object> resMap = new HashMap<String, Object>();
	logger.info("Inside get Meetings by name : " + meetingName);
	resMap.put("success", false);
	try{
		if(meetingName!=null){
			
			pm 		=	 PMF.get().getPersistenceManager();
			q 		=	 pm.newQuery(Meeting.class);
			String cndn = "meetingName == '"+meetingName+"'";
			List<Meeting> meetingList = meetingDAO.getObjects(cndn,q);
			
			logger.info("query & condition sent to DAO");
		
			if(meetingList!=null && !meetingList.equals(null)){
				resMap.put("success", true);
				resMap.put("meetingDetails", meetingList);
				logger.info("Got metting List successfully");
			} else{
				resMap.put("success", false);
				logger.info("Meeting List is empty");
			}
		} 
	} catch(Throwable e){
		resMap.put("success", false);
		resMap.put("exception", e.getMessage());
		logger.log(Level.ERROR, e.getMessage(), e);
	} finally{
		pm.close();
		q.closeAll();
	}
	try {
			logger.info("Returning Map as String : " + resMap);
			return mapper.writeValueAsString(resMap);
		} catch (JsonProcessingException e) {
			logger.info("JsonProcessingException in get Meeting");
			return "{\"success\":false,\"exception\":" + e.getMessage() + "}";
		}
	}
	
	public String getMeetingByUser(String mailId) {
		
		logger.info("Inside getMeetingByUser method, mailId : " + mailId);
		
		PersistenceManager pm		=		null;
		
		Query q = null;
		
		Map<String, Object> resMap		=		new HashMap<String, Object>();
		resMap.put("success", false);
		
		try {
			
			if(mailId != null && !mailId.isEmpty()) {
				pm 						=		PMF.get().getPersistenceManager();
				q						=		pm.newQuery(Meeting.class);
				
				List<Meeting> meetings		=		meetingDAO.getObjects(null, q);
				
				logger.info("Number of items has filtered : " + meetings.size());
				if(meetings != null && !meetings.isEmpty()) {
					
					List<String> people				=		null;
					List<Map<String, Object>> partMeetings		=		new ArrayList<Map<String, Object>>();
					List<Question> tempQuestions	=		null;
					
					Map<String, Object> meetingMap		=		null;
					
					Query	questionsQuery	=		pm.newQuery(Question.class);
					
					for(Meeting meeting : meetings) {
						
						people			=		meeting.getPeople();
						
						if(people != null && !people.isEmpty()) {
							
							String cond		=		null;
							meetingMap		=		new HashMap<String, Object>();
							if(people.contains(mailId)) {
								
								logger.info("Meeting Name : " + meeting.getMeetingName());
								//partMeetings.add(meeting);
								
								meetingMap		=		mapper.convertValue(meeting, Map.class);
								
								cond		=		"meetingName == '" + meeting.getMeetingName() + "'";
								tempQuestions	=		meetingDAO.getQuestions(cond, questionsQuery);
								if(tempQuestions != null && tempQuestions.size() != 0) {
									logger.info("Number of the questions  : " + tempQuestions);
									meetingMap.put("items", tempQuestions);
								}
								
								partMeetings.add(meetingMap);
							}
						}
					}
					
					resMap.put("success", true);
					resMap.put("meetings", partMeetings);
				} else {
					
					resMap.put("success", false);
				}
			} else {
				
				throw new Exception("MailId is mandatory");
			}
		} catch(Throwable e) {
			logger.log(Level.ERROR, e.getMessage(), e);
		} finally{
			pm.close();
			q.closeAll();
		}
		
		try {
			return mapper.writeValueAsString(resMap);
		} catch(Throwable e) {
			return "{\"success\":false,\"exception\":" + e.getMessage() + "}";
		}
	}
}
