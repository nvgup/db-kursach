package ua.com.kpi.kursach.util;


import com.google.common.io.Files;

import static ua.com.kpi.kursach.web.CustomWebMvcConfigurerAdapter.UPLOADED_RESOURCE_PATTERNS_NAME;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader  {
	private static final Logger LOGGER = Logger.getLogger(FileUploader.class);

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    public static final String UPLOAD_FOLDER ="d:\\tmp\\files";

    public String getUploadedFilePath(final MultipartFile file) {
        File uploadFolderFile = new File(UPLOAD_FOLDER);
        String outFileName = uploadFolderFile + PATH_SEPARATOR + file.getOriginalFilename();
        try {
            return getUploadedFileName(file, outFileName);
        } catch (IOException e) {
        	LOGGER.error(String.format("Unable to get name for uploaded file '%s'", outFileName));
        }
        return null;
    }

    private String getUploadedFileName(final MultipartFile file, final String outFileName) throws IOException {
        File outFile = new File(outFileName);
        if (!file.isEmpty()) {
            Files.write(file.getBytes(), outFile);
        }
        return outFileName.replace(UPLOAD_FOLDER, "/"
                + UPLOADED_RESOURCE_PATTERNS_NAME).replace(PATH_SEPARATOR, "/");
    }
}

