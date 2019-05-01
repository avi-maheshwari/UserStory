package com.avi.UserStory;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private User user; 

	@RequestMapping("/")
	public String home()
	{
		return "register.jsp";
	}

	@RequestMapping("/registerUser")
	public ModelAndView registerUser(@RequestParam("username") String username,@RequestParam("password") String password)
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("status.jsp");
		Pattern userRule = Pattern.compile("^(?=.{5,})[a-zA-Z0-9]*$");
		boolean correctUser = userRule.matcher(username).matches();

		if(correctUser == true)
		{
			//check in db
			if(userRepo.existsById(username)==true)
			{
				mv.addObject("status","User - " + username + " not registered as already exists in db\n");
			}
			else
			{
				//user allowed-checking for password
				Pattern passwordRule = Pattern.compile("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$");
				boolean correctPassword = passwordRule.matcher(password).matches();

				if(correctPassword == true)
				{
					//save user in db
					user.setUsername(username);
					user.setPassword(password);
					userRepo.save(user);
					mv.addObject("status","User - " + username + " Registration Successful");
				}
				else if(correctPassword == false)
				{
					String length="wrong";
					String lowerCase="wrong";
					String upperCase="wrong";
					String number="wrong";
					if(Pattern.compile("^(?=.{8,}).*$").matcher(password).matches()) length="correct"; 
					if(Pattern.compile("^(?=.*[a-z]).*$").matcher(password).matches()) lowerCase="correct"; 
					if(Pattern.compile("^(?=.*[A-Z]).*$").matcher(password).matches()) upperCase="correct";
					if(Pattern.compile("^(?=.*[0-9]).*$").matcher(password).matches()) number="correct";
					mv.addObject("status","User - " + username + " not registered as Password Incorrect - length - " + length + ", upperCase - " + upperCase + ", lowerCase - " + lowerCase + ", number - " + number);
				}
			}
		}
		else if(correctUser == false)
		{
			mv.addObject("status","User - " + username + " Incorrect");
		}
		return mv;
	}

}
