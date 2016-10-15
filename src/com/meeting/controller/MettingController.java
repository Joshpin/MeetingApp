package com.meeting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MettingController {

	@RequestMapping(value = "/meeting", method = RequestMethod.POST)
	public String createMeeting(@RequestBody String reqString) {

		
		return  "";
	}

	@RequestMapping(value = "/meeting/{meetingName}", method = RequestMethod.GET)
	public String getMeeting(@PathVariable String meetingName) {

		return "";
	}

	@RequestMapping(value = "/meeting", method = RequestMethod.PUT)
	public String addMeeting(@RequestBody String reqString) {

		return "";
	}

	@RequestMapping(value = "/details/{name}", method = RequestMethod.GET)
	public String getDetails(@PathVariable String name) {

		return "";
	}

}
