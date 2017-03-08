package com.demo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.demo.entity.Gender;
import com.demo.entity.GenderProperties;
import com.demo.utils.LocaleMessageSourceService;
import com.google.gson.Gson;

@Component
public class DropdownComponent {
	
	@Autowired
	private GenderProperties genderProperties;
	
	@Resource
	private LocaleMessageSourceService localeMessageSource;
	
	public String generateComponent(String name) {
		Map<String, String> gMap = genderProperties.getGender();
		List<String> keys = new ArrayList<String>(2);
		for(String key : gMap.keySet()){
			keys.add(key);
		}
		/**
		 * <select class="ui dropdown">
			  <option value="">Gender</option>
			  <option value="1">Male</option>
			  <option value="0">Female</option>
			</select>
		 */
		StringBuffer sb = new StringBuffer("<select class='ui dropdown' name='"+name+"'>");
		/*sb.append("<option value=''>");
		sb.append(localeMessageSource.getMessage("header.user.gender"));
		sb.append("</option>");*/
		
		for(String k : keys){
			String gValue = gMap.get(k);
			Gson gson = new Gson();
			Gender g = new Gender();
			g = gson.fromJson(gValue, g.getClass());
			sb.append("<option value="+g.getValue()+">");
			if(!StringUtils.isEmpty(g.getI18n())){
				sb.append(localeMessageSource.getMessage(g.getI18n()));
			}else{
				sb.append(g.getDisplay());
			}
			sb.append("</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}

}
