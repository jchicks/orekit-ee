package gov.nasa.ssmo.spaceflightdynamics.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsageController {
	@Resource(name="UTCTAI_PATH")
	public String UTCTAI_PATH;
	
	@RequestMapping("usage")
	public String loadHomePage(Model model) 
	{
		model.addAttribute("name", "CodeTutrrrr");
		model.addAttribute("UTCTAI_PATH", UTCTAI_PATH);
		return "usage";
	}
}
