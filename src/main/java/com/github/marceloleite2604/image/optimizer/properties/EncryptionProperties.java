package com.github.marceloleite2604.image.optimizer.properties;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(PropertiesPath.ENCRYPTION)
@Validated
public class EncryptionProperties {

	/**
	 * Cryptographic algorithm to use for encryption.
	 */
	@NotBlank
	private String cryptographicAlgorythm;

	/**
	 * Feedback mode to user for encryption.
	 */
	@NotBlank
	private String feedbackMode;

	/**
	 * Padding scheme to user for encryption.
	 */
	@NotBlank
	private String paddingScheme;

	/**
	 * Name of the environment variable which contains the encryption key.
	 */
	private String keyEnvironmentVariableName;

	public String getCryptographicAlgorythm() {
		return cryptographicAlgorythm;
	}

	public void setCryptographicAlgorythm(String cryptographicAlgorythm) {
		this.cryptographicAlgorythm = cryptographicAlgorythm;
	}

	public String getFeedbackMode() {
		return feedbackMode;
	}

	public void setFeedbackMode(String feedbackMode) {
		this.feedbackMode = feedbackMode;
	}

	public String getPaddingScheme() {
		return paddingScheme;
	}

	public void setPaddingScheme(String paddingScheme) {
		this.paddingScheme = paddingScheme;
	}

	public String getKeyEnvironmentVariableName() {
		return keyEnvironmentVariableName;
	}

	public void setKeyEnvironmentVariableName(String keyEnvironmentVariableName) {
		this.keyEnvironmentVariableName = keyEnvironmentVariableName;
	}
}
