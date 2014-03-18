package gov.nasa.ssmo.spaceflightdynamics.controller;

import gov.nasa.ssmo.spaceflightdynamics.model.FinalState;
import gov.nasa.ssmo.spaceflightdynamics.service.PropagatorService;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.orekit.errors.OrekitException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springconfig.OrekitConfig;

@Controller
public class PropagatorController 
{
	@Resource(name="UTCTAI_PATH")
	public String UTCTAI_PATH;
	
	@Resource(name="propagatorService")
	PropagatorService propagatorService;
	
	@Resource(name="OrekitConfig")
	OrekitConfig orekitConfig;
	
	@RequestMapping("propagate")
	public String loadPropagatePage(
		Model model,
		@RequestParam(value="t0") @DateTimeFormat(iso=ISO.DATE_TIME) DateTime t0,
		@RequestParam(value="tf") @DateTimeFormat(iso=ISO.DATE_TIME) DateTime tf,			
		@RequestParam(value="r0", defaultValue="") ArrayList<Double> r0,
		@RequestParam(value="v0", defaultValue="") ArrayList<Double> v0) 
	throws OrekitException
	{		
		FinalState finalState = 
			propagatorService.propagate(t0, tf, r0, v0);
	
		model.addAttribute("name", "propagator controller");
		model.addAttribute("t0", t0);
		model.addAttribute("tf", tf);
		model.addAttribute("r0", r0);
		model.addAttribute("v0", v0);
		model.addAttribute("UTCTAI_PATH", UTCTAI_PATH);
		model.addAttribute("finalState", finalState);
		
		return "propagator";
	}
}
