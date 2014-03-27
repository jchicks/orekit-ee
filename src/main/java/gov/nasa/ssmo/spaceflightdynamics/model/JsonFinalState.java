package gov.nasa.ssmo.spaceflightdynamics.model;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class JsonFinalState 
{
  DateTime t0;
  DateTime tf;
  ArrayList<Double> r0;
  ArrayList<Double> v0;
  
  
  
  public JsonFinalState(DateTime t0, DateTime tf, ArrayList<Double> r0,
      ArrayList<Double> v0) {
    super();
    this.t0 = t0;
    this.tf = tf;
    this.r0 = r0;
    this.v0 = v0;
  }
  public DateTime getT0() {
    return t0;
  }
  public void setT0(DateTime t0) {
    this.t0 = t0;
  }
  public DateTime getTf() {
    return tf;
  }
  public void setTf(DateTime tf) {
    this.tf = tf;
  }
  public ArrayList<Double> getR0() {
    return r0;
  }
  public void setR0(ArrayList<Double> r0) {
    this.r0 = r0;
  }
  public ArrayList<Double> getV0() {
    return v0;
  }
  public void setV0(ArrayList<Double> v0) {
    this.v0 = v0;
  }
  
  
}
