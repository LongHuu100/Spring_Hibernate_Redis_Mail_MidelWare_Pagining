package vn.printgo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener implements MessageListener {
	
    @Override
    public void onMessage( final Message message, final byte[] pattern ) {
    	
    	Logger logger = LoggerFactory.getLogger(RedisMessageListener.class);
    	logger.info( "Message received: {}", message.toString() );
    }
}
