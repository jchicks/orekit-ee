package gov.nasa.ssmo.spaceflightdynamics.controller;

import gov.nasa.ssmo.spaceflightdynamics.model.JsonFinalState;
import gov.nasa.ssmo.spaceflightdynamics.service.PropagatorService;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.orekit.errors.OrekitException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class JsonPropagatorController 
{
  @Resource(name="propagatorService")
  PropagatorService propagatorService;
  
  @RequestMapping("json/propagate")
  public @ResponseBody JsonFinalState propagate(
    @RequestParam(value="t0") @DateTimeFormat(iso=ISO.DATE_TIME) DateTime t0,
    @RequestParam(value="tf") @DateTimeFormat(iso=ISO.DATE_TIME) DateTime tf,     
    @RequestParam(value="r0", defaultValue="") ArrayList<Double> r0,
    @RequestParam(value="v0", defaultValue="") ArrayList<Double> v0)
  throws OrekitException
  {
    JsonFinalState finalState = 
        new JsonFinalState(t0, tf, r0, v0);
    
    return finalState;
  }
}