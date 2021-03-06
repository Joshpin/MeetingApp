package com.meeting.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.meeting.data.Meeting;
import com.meeting.data.Question;

public class MeetingDAO {
	
	public static final Logger logger = Logger.getLogger(MeetingDAO.class.getName());

	public boolean save(Meeting m) throws Throwable {

		boolean isSuccess = false;

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(m);
			logger.info("successfully saved");
			isSuccess = true;
		} catch (Throwable e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			isSuccess = false;
		} finally {
			pm.close();
		}

		logger.info("save status returning : "+isSuccess);
		return isSuccess;
	}
	
	public boolean saveAll(List<Question> questions) throws Throwable {

		boolean isSuccess = false;

		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistentAll(questions);
			logger.info("successfully saved");
			isSuccess = true;
		} catch (Throwable e) {
			logger.log(Level.ERROR, e.getMessage(), e);
			isSuccess = false;
		} finally {
			pm.close();
		}

		logger.info("save status returning : "+isSuccess);
		return isSuccess;
	}

	public List<Meeting> getObjects(String cond, Query q)throws Throwable{
	
		List<Meeting> meetings		=		new ArrayList<Meeting>();
		if(cond!=null){	
			q.setFilter(cond);
			logger.info("Filter added");
		}
		try{
			meetings = (List<Meeting>) q.execute();
			logger.info("Meetings from DB  : " + meetings);
		} catch(Throwable e){
			logger.log(Level.ERROR, e.getMessage(), e);
		} finally{
			q.closeAll();
		}
		
		return meetings;
	}
	
	public List<Question> getQuestions(String cond, Query q)throws Throwable{
		
		List<Question> questions		=		new ArrayList<Question>();
		if(cond!=null){	
			q.setFilter(cond);
			logger.info("Filter added");
		}
		try{
			questions = (List<Question>) q.execute();
			logger.info("Questions from DB : " + questions);
		} catch(Throwable e){
			logger.log(Level.ERROR, e.getMessage(), e);
		} finally{
			q.closeAll();
		}
		
		return questions;
	}
}