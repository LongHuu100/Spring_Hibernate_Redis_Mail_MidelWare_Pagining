package vn.printgo.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vn.printgo.components.WatchFile;
import vn.printgo.redis.IRedisPublisher;
import vn.printgo.redis.RedisMessageListener;
import vn.printgo.redis.RedisPublisherImpl;

@Configuration
@SpringBootApplication(scanBasePackages = "vn.printgo")
@EnableAutoConfiguration(exclude = {
    SecurityAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    ErrorMvcAutoConfiguration.class
})
@PropertySource(value = {
	"file:conf/application.properties",
	"file:conf/custom.properties" 
})
public class CrmApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CrmApplication.class);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CrmApplication.class, args);
        new Thread(new WatchFile()).start();
    }
    
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(""));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate< String, Object > redisTemplate() {
        final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
        template.setConnectionFactory( jedisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory( jedisConnectionFactory() );
        container.addMessageListener( messageListener(), topic() );
        return container;
    }
    
    // Bean này để nhận data từ redis sub với channel pubsub:printgo
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( new RedisMessageListener() );
    }
    // Bean này để publicser data vào redis với channel pubsub:printgo
    @Bean
    IRedisPublisher redisPublisher() {
        return new RedisPublisherImpl( redisTemplate(), topic() );
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic( "pubsub:printgo" );
    }
}