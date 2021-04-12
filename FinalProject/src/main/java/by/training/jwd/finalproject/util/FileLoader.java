package by.training.jwd.finalproject.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code FileLoader} class represents file loader.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class FileLoader {
	private static final Logger logger = LogManager.getLogger(FileLoader.class);
	private static final String FILE_EXTENSION = ".jpg";

	/**
	 * Load image.
	 *
	 * @param parts     the parts of photo
	 * @param directory the directory
	 * @return the optional of new generated photo name
	 */
	public Optional<String> load(Collection<Part> parts, String directory) {
		File fileSaveDir = new File(directory);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		Optional<String> fileOptional = Optional.empty();
		for (Part part : parts) {
			String fileName = part.getSubmittedFileName();
			try {
				if (fileName != null) {
					if (fileName.endsWith(FILE_EXTENSION)) {
						String uniqueFileName = UUID.randomUUID().toString();
						part.write(directory + uniqueFileName + FILE_EXTENSION);
						fileOptional = Optional.of(uniqueFileName);
						logger.log(Level.INFO, "File was uploaded successfully {}", fileName);
					} else {
						logger.log(Level.ERROR, "File was not uploaded. Incorrect extension {}", fileName);
					}
				}
			} catch (IOException e) {
				logger.log(Level.ERROR, "File was not uploaded {}", fileName, e);
			}
		}
		return fileOptional;
	}
}
