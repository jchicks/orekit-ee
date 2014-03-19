package gov.nasa.ssmo.spaceflightdynamics.service;

import gov.nasa.ssmo.spaceflightdynamics.model.FinalState;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.joda.time.DateTime;
import org.orekit.bodies.CelestialBodyFactory;
import org.orekit.errors.OrekitException;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.CartesianOrbit;
import org.orekit.orbits.Orbit;
import org.orekit.propagation.SpacecraftState;
import org.orekit.propagation.numerical.NumericalPropagator;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeScalesFactory;
import org.orekit.utils.PVCoordinates;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service("propagatorService")
public class PropagatorService 
{
  private double stepSize = 60;	

  @Resource(name="jedisConnectionFactory")
  JedisConnectionFactory jedisConnectionFactory;

  @Resource(name="orekitTopic")
  PatternTopic orekitTopic;

  @Resource(name="redisContainer")
  RedisMessageListenerContainer redisContainer;

  @PostConstruct
  public void initialize() 
  {
    redisContainer.addMessageListener( 
        new MessageListener() {
          @Override
          public void onMessage(Message message, byte[] pattern) {
            System.out.println( 
                "Message received: " + message.toString() + " "+
                    "Patttern: " + new String(pattern));

            System.out.println("channel " + 
                new String(message.getChannel()));


//            try {
//              propagate(null, null, null, null);
//            } catch (OrekitException e) {
//              // TODO Auto-generated catch block
//              e.printStackTrace();
//            }
          }
        }, orekitTopic);
  }	

  public FinalState propagate(DateTime t0,
      DateTime tf,
      ArrayList<Double> r0,
      ArrayList<Double> v0) 
          throws OrekitException
  {
    AbsoluteDate epoch = AbsoluteDate.J2000_EPOCH;

    epoch = 
        new AbsoluteDate(t0.toDate(), 
            TimeScalesFactory.getUTC());

    Vector3D v3r = 
        new Vector3D(r0.get(0), 
            r0.get(1), 
            r0.get(2));

    Vector3D v3v = 
        new Vector3D(v0.get(0), 
            v0.get(1), 
            v0.get(2));

    /*
     * We're finally ready to start the Orekit stuff.  First create an 
     * Orekit NumericalPropagator using the apache-commons Runge-Kutta
     * integrator.
     */
    NumericalPropagator numericalPropagator = 
        new NumericalPropagator(
            new ClassicalRungeKuttaIntegrator(stepSize));

    Orbit orbit = 
        new CartesianOrbit(
            new PVCoordinates(v3r,v3v), 
            FramesFactory.getEME2000(), 
            epoch, 
            CelestialBodyFactory.getEarth().getGM());

    SpacecraftState state = new SpacecraftState(orbit);

    numericalPropagator.setInitialState(state);

    SpacecraftState finalSpacecraftState = 
        numericalPropagator.propagate(
            new AbsoluteDate(tf.toDate(), 
                TimeScalesFactory.getUTC()));

    FinalState finalState = 
        new FinalState(
            finalSpacecraftState.getPVCoordinates()
            .getPosition(), 
            finalSpacecraftState.getPVCoordinates()
            .getVelocity());

    return finalState;
  }
}
