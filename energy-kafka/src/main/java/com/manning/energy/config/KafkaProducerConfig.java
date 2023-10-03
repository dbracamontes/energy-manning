package com.manning.energy.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.manning.energy.avro.model.BatteryEventAvro;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

@Configuration
public class KafkaProducerConfig {

	@Value(value = "${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value(value = "${kafka.producer.key-serializer}")
	private String keySerializer;

	@Value(value = "${kafka.producer.value-serializer}")
	private String valueSerializer;

	@Value(value = "${kafka.schema.registry.url}")
	private String schemaRegistryUrl;

	@Bean
	public ProducerFactory<String, BatteryEventAvro> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		// props.put(KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, false);
		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public KafkaTemplate<String, BatteryEventAvro> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
