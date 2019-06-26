package com.github.marceloleite2604.image.optimizer.model;

import java.io.File;

public class Image {

	private int width;

	private int height;

	private double ratio;

	private File file;

	private Image(Builder builder) {
		this.width = builder.width;
		this.height = builder.height;
		this.ratio = builder.ratio;
		this.file = builder.file;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int width;
		private int height;
		private double ratio;
		private File file;

		private Builder() {
		}

		public Builder width(int width) {
			this.width = width;
			return this;
		}

		public Builder height(int height) {
			this.height = height;
			return this;
		}

		public Builder ratio(double ratio) {
			this.ratio = ratio;
			return this;
		}

		public Builder file(File file) {
			this.file = file;
			return this;
		}

		public Image build() {
			return new Image(this);
		}
	}

}
