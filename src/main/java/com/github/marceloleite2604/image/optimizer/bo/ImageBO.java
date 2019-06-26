package com.github.marceloleite2604.image.optimizer.bo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.image.optimizer.mapper.FileToImageMapper;
import com.github.marceloleite2604.image.optimizer.model.Image;
import com.github.marceloleite2604.image.optimizer.util.FileUtil;

@Component
public class ImageBO {

	@Inject
	private FileUtil fileUtil;

	@Inject
	private FileToImageMapper fileToImageMapper;

	public List<Image> retrieveAllImages(String rootDirectoryPath) {

		List<File> imageFiles = fileUtil.retrieveAllFilesFrom(rootDirectoryPath)
				.stream()
				.filter(fileUtil::isImageFile)
				.collect(Collectors.toList());

		List<Image> images = new LinkedList<>();
		fileToImageMapper.mapList(imageFiles)
				.forEach(images::add);

		List<File> subdirectories = fileUtil.retrieveAllDirectoriesFrom(rootDirectoryPath);

		for (File subdirectory : subdirectories) {
			images.addAll(retrieveAllImages(subdirectory.getAbsolutePath()));
		}
		
		return images;
	}
}
