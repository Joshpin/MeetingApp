package com.meeting.services;

import java.util.*;

import javax.jdo.Query;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.dao.MeetingDAO;
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
			logger.info("Exception occured an added to response map : "+e.getMessage()+"and success status is false");
		}
		
		try {
			logger.info("Returning Map as String : " + resMap);
			return mapper.writeValueAsString(resMap);
		} catch (JsonProcessingException e) {
			logger.info("JsonProcessingException");
			return "{\"success\":false,\"exception\":" + e.getMessage() + "}";
		}
	}
	
	/*public String getMeetingByName(String reqString){
		if(reqString!=null){
			
			Query q = pm.newQuery(Meeting.class);
			String filter = "reqString==meetingName";
			
			 meetingDAO.getObjects(filter,q);
			
		}
	}*/
}
