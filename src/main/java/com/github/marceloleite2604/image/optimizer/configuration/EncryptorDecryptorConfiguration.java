package com.github.marceloleite2604.image.optimizer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.encryptor.EncryptorDecryptor;
import com.github.marceloleite2604.image.optimizer.properties.EncryptionProperties;

@Configuration
public class EncryptorDecryptorConfiguration {

	@Bean(BeanNames.ENCRYPTOR_DECRYPTOR)
	public EncryptorDecryptor createEncryptorDecryptor(EncryptionProperties encryptionProperties) {
		String keyEnvironmentVariableName = encryptionProperties.getKeyEnvironmentVariableName();
		String cryptographicAlgorythm = encryptionProperties.getCryptographicAlgorythm();
		String feedbackMode = encryptionProperties.getFeedbackMode();
		String paddingScheme = encryptionProperties.getPaddingScheme();

		return EncryptorDecryptor.builder()
				.cryptographicAlgorythm(cryptographicAlgorythm)
				.feedbackMode(feedbackMode)
				.paddingScheme(paddingScheme)
				.keyEnvironmentVariableName(keyEnvironmentVariableName)
				.build();
	}
}
