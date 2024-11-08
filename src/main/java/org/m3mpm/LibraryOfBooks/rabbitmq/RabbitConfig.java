package org.m3mpm.LibraryOfBooks.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "bookQueue";

    @Bean
    public Queue bookQueue() {
        return new Queue(QUEUE_NAME, true);  // true - очередь будет сохраняться при перезапуске
    }
}
