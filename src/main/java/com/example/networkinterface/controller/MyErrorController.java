package com.example.networkinterface.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR; // default to internal server error

		if (status != null) {
			int statusCodeInt = Integer.parseInt(status.toString());
			try {
				statusCode = HttpStatus.valueOf(statusCodeInt);
			} catch (IllegalArgumentException e) {
				// Invalid status code, fallback to internal server error
				// TODO log illegal argument
			}
		}

		String errorMessage;
		switch (statusCode) {
			case NOT_FOUND -> errorMessage = "The requested page was not found";
			case UNAUTHORIZED -> errorMessage = "You are not authorized to access this page";
			case FORBIDDEN -> errorMessage = "Access to this page is forbidden";
			case BAD_REQUEST -> errorMessage = "Your request was invalid";
			default -> errorMessage ="An unknown error occurred";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}

}
