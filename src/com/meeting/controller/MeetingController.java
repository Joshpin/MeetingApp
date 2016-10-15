package com.meeting.controller;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meeting.services.MeetingUtilities;


@Controller
public class MeetingController {
	
	public static final Logger logger = Logger.getLogger(MeetingController.class.getName());

	public static final MeetingUtilities utilities = new MeetingUtilities();
	
	@RequestMapping("/")
	private String home(){
	return "home";
	}
	
	@RequestMapping(value = "/meeting", method = RequestMethod.POST)
	public @ResponseBody String createMeeting(@RequestBody String reqString) {
		//Log.info("Test");
		logger.info("Directing to create meeting view");
		return utilities.saveMeeting(reqString);
	}

	@RequestMapping(value = "/meeting/{meetingName}", method = RequestMethod.GET)
	public String getMeeting(@PathVariable String meetingName) {

		logger.info("Directing to get meeting view");
		return "";
	}

	@RequestMapping(value = "/meeting", method = RequestMethod.PUT)
	public String addMeeting(@RequestBody String reqString) {

		logger.info("Directing to add meeting view");
		return "";
	}

	@RequestMapping(value = "/details/{name}", method = RequestMethod.GET)
	public String getDetails(@PathVariable String name) {

		logger.info("Directing to get meeting details view");
		return "";
	}

}
