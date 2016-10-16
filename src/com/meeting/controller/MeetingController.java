package com.meeting.controller;

import org.apache.log4j.Logger;
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
	
	@RequestMapping(value = "/meeting", method = RequestMethod.POST)
	public @ResponseBody String createMeeting(@RequestBody String reqString) {
		logger.info("Directing to create meeting view");
		return utilities.saveMeeting(reqString);
	}

	@RequestMapping(value = "/meeting/{meetingName}", method = RequestMethod.GET)
	public @ResponseBody String getMeeting(@PathVariable String meetingName) {

		logger.info("Directing to get meeting view");
		return utilities.getMeetingByName(meetingName);
	}

	@RequestMapping(value = "/meeting", method = RequestMethod.PUT)
	public String addMeeting(@RequestBody String reqString) {

		logger.info("Directing to add meeting view");
		return "";
	}

	@RequestMapping(value = "/details/{mailId}", method = RequestMethod.GET)
	public @ResponseBody String getDetails(@PathVariable String mailId) {
		logger.info("Directing to get meeting details view");
		return utilities.getMeetingByUser(mailId);
	}

}
