package com.github.marceloleite2604.image.optimizer.mapper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.image.optimizer.model.Image;

@Component
public class FileToImageMapper extends AbstractMapper<File, Image> {

	@Override
	public Image map(File file) {

		BufferedImage bufferedImage = createBufferedImage(file);
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double ratio = (double) width / (double) height;

		return Image.builder()
				.width(width)
				.height(height)
				.ratio(ratio)
				.file(file)
				.build();
	}

	private BufferedImage createBufferedImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException exception) {
			throw new RuntimeException(
					"Could not read image file \"" + file.getAbsolutePath() + "\".", exception);
		}
	}

}
