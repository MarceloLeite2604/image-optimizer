package com.github.marceloleite2604.image.optimizer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(PropertiesPath.TINY_PNG)
@Validated
public class TinyPngProperties {

	/**
	 * API key (encrypted).
	 */
	private String apiKey;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
