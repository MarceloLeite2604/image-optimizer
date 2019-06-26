package com.github.marceloleite2604.image.optimizer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(PropertiesPath.IMAGE_OPTIMIZER)
@Validated
public class ImageOptimizerProperties {

	/**
	 * Maximum image width.
	 */
	private int maxWidth;
	
	/**
	 * Maximum image height.
	 */
	private int maxHeight;

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
}
