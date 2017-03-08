package com.demo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.common.DropdownComponent;
import com.demo.entity.GenderProperties;
import com.demo.entity.User;
import com.demo.repository.UserRepository;

@Controller
@EnableConfigurationProperties(GenderProperties.class)
public class LoadController {
	@Autowired
	private UserRepository userRepository;
	@Resource
	private DropdownComponent dropdown;
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomePage(Model model, 
			@RequestParam(value="page", defaultValue = "1") Integer pageIndex,
			@RequestParam(value="pageSize", defaultValue = "10") Integer pageSize){
		if(!model.containsAttribute("gender")){
			model.addAttribute("gender", dropdown.generateComponent("gender"));
		}
		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(pageIndex-1, pageSize, sort);
		Page<User> pageUser = userRepository.findAll(pageable);
		model.addAttribute("users", pageUser.getContent());
		pageUser.hasContent();
		model.addAttribute("pageIndex", pageable.getPageNumber());
		model.addAttribute("pageSize", pageable.getPageSize());
		model.addAttribute("isFirst", pageUser.isFirst());
		model.addAttribute("isLast", pageUser.isLast());
		model.addAttribute("hasPrevious", pageUser.hasPrevious());
		model.addAttribute("hasNext", pageUser.hasNext());
		model.addAttribute("totalPages", pageUser.getTotalPages());
		return "home";		
	}
	
	@RequestMapping(value="/load")
	public String getLoadPage(Model model){
		return "load";
	}
	
	@RequestMapping(value="/login")
	public String getLoginPage(){		
		return "/login";
	}
	
	@RequestMapping(value="/validation")
	public String formValidation(){
		return "form_validation";
	}
}
