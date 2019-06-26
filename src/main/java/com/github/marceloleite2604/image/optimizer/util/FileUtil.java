package com.github.marceloleite2604.image.optimizer.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	private static final String[] UNITS = { "B", "kB", "MB", "GB", "TB", "PB", "EB" };

	private static final int BYTE_BLOCK_SIZE = 1024;

	private static final String REGEX_EXTENSION_GROUP_NAME = "extension";

	private static final Pattern FILE_EXTENSION_PATTERN = Pattern
			.compile("\\.(?<" + REGEX_EXTENSION_GROUP_NAME + ">\\w+)$");

	private static final List<String> IMAGE_EXTENSIONS = new ArrayList<>();

	static {
		IMAGE_EXTENSIONS.add("jpg");
		IMAGE_EXTENSIONS.add("jpeg");
		IMAGE_EXTENSIONS.add("png");
	}

	public String formatAsHumanReadableSize(int size) {
		int logSizeBaseBlock = (int) log(size, BYTE_BLOCK_SIZE);

		return String.format("%.1f %s", size / Math.pow(BYTE_BLOCK_SIZE, logSizeBaseBlock),
				UNITS[logSizeBaseBlock]);
	}

	private double log(double value, double base) {
		return Math.log(value) / Math.log(base);
	}

	public void createDirectoryIfDoesNotExist(String directoryPath) {
		File directory = Paths.get(directoryPath)
				.toFile();

		if (!directory.exists()) {
			try {
				FileUtils.forceMkdir(directory);
			} catch (IOException exception) {
				String message = String.format("Could not create directory \"%s\".", directoryPath);
				throw new RuntimeException(message, exception);
			}
		}
	}

	public void throwErrorIfDirectoryDoesNotExist(String directoryPath) {
		throwErrorIfFileIsNotDirectory(directoryPath);

		File directory = Paths.get(directoryPath)
				.toFile();

		if (!directory.exists()) {
			String message = String.format("Directory \"%s\" does not exist.", directoryPath);
			throw new RuntimeException(message);
		}
	}

	public void throwErrorIfFileIsNotDirectory(String directoryPath) {
		File directory = Paths.get(directoryPath)
				.toFile();

		if (directory.isFile()) {
			String message = String.format("File \"%s\" is not a directory.", directoryPath);
			throw new RuntimeException(message);
		}
	}

	public List<File> retrieveAllDirectoriesFrom(String directoryPath) {
		return retrieveAllDirectoriesFrom(new File(directoryPath));
	}

	public List<File> retrieveAllDirectoriesFrom(File directory) {
		throwErrorIfFileIsNotDirectory(directory.getAbsolutePath());

		File[] directoriesArray = directory.listFiles(File::isDirectory);
		List<File> directories = new ArrayList<>(Arrays.asList(directoriesArray));
		Collections.sort(directories, (a, b) -> a.getName()
				.compareTo(b.getName()));
		return directories;
	}

	public List<File> retrieveAllFilesFrom(String directoryPath) {
		return retrieveAllFilesFrom(new File(directoryPath));
	}

	public List<File> retrieveAllFilesFrom(File directory) {
		throwErrorIfFileIsNotDirectory(directory.getAbsolutePath());

		File[] filesArray = directory.listFiles(file -> !file.isDirectory());
		List<File> files = new ArrayList<>(Arrays.asList(filesArray));
		Collections.sort(files, (a, b) -> a.getName()
				.compareTo(b.getName()));
		return files;
	}

	public boolean isImageFile(File file) {
		Optional<String> optionalFileExtension = retrieveFileExtension(file);

		if (optionalFileExtension.isEmpty()) {
			return false;
		}

		return IMAGE_EXTENSIONS.contains(optionalFileExtension.get()
				.toLowerCase());

	}

	public Optional<String> retrieveFileExtension(File file) {
		Matcher fileExtensionMatcher = FILE_EXTENSION_PATTERN.matcher(file.getName());

		if (fileExtensionMatcher.matches()) {
			return Optional.of(fileExtensionMatcher.group(REGEX_EXTENSION_GROUP_NAME));
		}

		return Optional.empty();
	}
}