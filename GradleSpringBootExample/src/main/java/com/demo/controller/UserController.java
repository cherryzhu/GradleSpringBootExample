package com.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.common.DropdownComponent;
import com.demo.entity.Gender;
import com.demo.entity.GenderProperties;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.utils.LocaleMessageSourceService;
import com.google.gson.Gson;

@Controller
@EnableConfigurationProperties
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GenderProperties genderProperties;
	@Resource
	private LocaleMessageSourceService localeMessageSource;
	
	@Resource
	private DropdownComponent dropdown;
	
	@RequestMapping("/users")
	public String getAllUsers(Model model) {
		Map<String, String> gMap = genderProperties.getGender();
		List<String> keys = new ArrayList<String>(2);
		for(String key : gMap.keySet()){
			keys.add(key);
		}
		Map<String, Gender> newGender = new HashMap<String, Gender>();		
		for(String k : keys){
			String gValue = gMap.get(k);
			Gson gson = new Gson();
			Gender g = new Gender();
			g = gson.fromJson(gValue, g.getClass());
			if(!StringUtils.isEmpty(g.getI18n())){
				g.setI18n(localeMessageSource.getMessage(g.getI18n()));
			}
			newGender.put(k, g);			
		}
		model.addAttribute("jsonGender", newGender);
		Pageable pageable = new PageRequest(0, 10, Direction.ASC,"id");
		model.addAttribute("users", userRepository.findAll(pageable).getContent());
		return "users";
	}

	@RequestMapping("/users2")
	public String getAllUsers2(Model model) {
		if(!model.containsAttribute("gender")){
			model.addAttribute("gender", dropdown.generateComponent("gender"));
		}
		Pageable pageable = new PageRequest(0, 10, Direction.ASC,"id");
		model.addAttribute("users", userRepository.findAll(pageable).getContent());
		return "users2";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String add(@RequestParam(value = "email") String email, @RequestParam(value = "name") String name,
			@RequestParam(value = "gender") int gender) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setGender(gender);
		userRepository.saveAndFlush(user);
		return "redirect:/home?page=3";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		userRepository.delete(Long.valueOf(id));
		return "redirect:/home?page=3";
	}

}
