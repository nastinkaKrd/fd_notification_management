package com.nastinka_krd.fooddelivery.notifications.notification_service_parent.config;

import com.nastinka_krd.user_management.api.dto.EmailNotification;
import com.nastinka_krd.user_management.api.dto.UserDataForNotifications;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> targetType) {
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(targetType);
        deserializer.addTrustedPackages("com.nastinka_krd.user_management.api.dto");

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConsumerFactory<String, EmailNotification> emailNotificationConsumerFactory() {
        return createConsumerFactory(EmailNotification.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailNotification> emailNotificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailNotificationConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, UserDataForNotifications> userDataConsumerFactory() {
        return createConsumerFactory(UserDataForNotifications.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserDataForNotifications> userDataKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserDataForNotifications> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userDataConsumerFactory());
        return factory;
    }
}
