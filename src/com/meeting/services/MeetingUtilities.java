package com.meeting.services;

import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.dao.MeetingDAO;
import com.meeting.dao.PMF;
import com.meeting.data.Meeting;

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
				Meeting m		 = 		mapper.convertValue(reqMap,Meeting.class );	
				logger.info("GettingId : "+ m.getMeetingId());
				if(meetingDAO.save(m)){
					resMap.put("success", true);
					logger.info("Response map - success is added as true");
				} else {
					resMap.put("success", false);
					logger.info("Response map - success is added as false");
				}
			}	
		} catch (Throwable e) {
			resMap.put("success", false);
			resMap.put("exception", e.getMessage());
			logger.info("Exception occured and added to response map : "+e.getMessage());
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
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		logger.info("Inside get Meetings by name : " + meetingName);
		resMap.put("success", false);
		try{
		if(meetingName!=null){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(Meeting.class);
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
			logger.info("Exception occured: "+e.getMessage());
		}
		try {
				logger.info("Returning Map as String : " + resMap);
				return mapper.writeValueAsString(resMap);
			} catch (JsonProcessingException e) {
				logger.info("JsonProcessingException in get Meeting");
				return "{\"success\":false,\"exception\":" + e.getMessage() + "}";
			}
		}
}
