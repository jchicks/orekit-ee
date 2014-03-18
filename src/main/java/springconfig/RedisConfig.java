package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration("RedisConfig")
public class RedisConfig 
{
  public static final String CHANNEL_PREFIX = "orekit::operation::";

  @Bean(name="jedisConnectionFactory")
  JedisConnectionFactory jedisConnectionFactory() 
  {
    return new JedisConnectionFactory();
  }

  @Bean
  RedisTemplate< String, Object > redisTemplate() 
  {
    final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
    template.setConnectionFactory( jedisConnectionFactory() );
    template.setKeySerializer( new StringRedisSerializer() );
    template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
    template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
    return template;
  }

  //    @Bean
  //    MessageListenerAdapter messageListener() {
  //        return new MessageListenerAdapter( 
  //        	new RedisMessageListener() );
  //    }

  @Bean(name="redisContainer")
  RedisMessageListenerContainer redisContainer() 
  {
    System.out.println("==========");
    System.out.println("seting up listener container");
    System.out.println("==========");

    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

    container.setConnectionFactory( jedisConnectionFactory() );
    //container.addMessageListener( messageListener(), orekitTopic() );

    return container;
  }

  //    @Bean
  //    IRedisPublisher redisPublisher() {
  //        return new RedisPublisherImpl( redisTemplate(), topic() );
  //    }

  @Bean(name="orekitTopic")
  PatternTopic orekitTopic() 
  {
    return new PatternTopic( "pubsub:queue*" );
  }
}