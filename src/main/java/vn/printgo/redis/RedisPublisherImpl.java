package vn.printgo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisPublisherImpl implements IRedisPublisher {

    private final RedisTemplate< String, Object> template;
    private final ChannelTopic topic;

    public RedisPublisherImpl(final RedisTemplate< String, Object> template,
            final ChannelTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    @Override
    public void publish(Object str) {
        template.convertAndSend(topic.getTopic(), str);
    }
}
