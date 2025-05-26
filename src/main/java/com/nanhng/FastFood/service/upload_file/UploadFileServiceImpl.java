package com.nanhng.FastFood.service.upload_file;

import com.nanhng.FastFood.entity.upload_file.UploadFile;
import com.nanhng.FastFood.entity.upload_file.constant.UploadFileType;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.other_service.storage.StorageResource;
import com.nanhng.FastFood.service.repository.upload_file.UploadFileRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {
    private final UploadFileRepository uploadFileRepository;

    private final StorageResource storageResource;
    @Override
    public UploadFile uploadImage(final MultipartFile file) {
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());

        String randomString = RandomStringUtils.randomAlphanumeric(6);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename().toLowerCase());
        String originalName = timeStamp + "_" + randomString + "_" + fileName;
        String thumbName = timeStamp + "_" + randomString + "_thumb_" + fileName;
        if (originalName.contains("..")) {
            throw new LovelyException("Sorry! Filename contains invalid path sequence " + originalName);
        }
        String type = file.getContentType();
        if ((type == null || !type.toLowerCase().startsWith("image")) && !originalName.endsWith("jpg") && !originalName.endsWith("jpeg") && !originalName.endsWith("png")) {
            throw new LovelyException("File format error");
        }

        try {
            BufferedImage bimg = ImageIO.read(file.getInputStream());
            UploadFile image = new UploadFile();
            if (bimg != null) {
                image.setWidth(bimg.getWidth());
                image.setHeight(bimg.getHeight());
            }
            image.setType(UploadFileType.IMAGE);
            image.setSize(file.getSize());
            image.setOriginFilePath(storageResource.writeResource(file.getInputStream(),"image/" + originalName));
            image.setOriginalFileName(originalName);
            ByteArrayOutputStream thumbOutputStream = createThumbnail(file, type, fileName);
            if (thumbOutputStream != null) {
                try (InputStream inputStream = new ByteArrayInputStream(thumbOutputStream.toByteArray())) {
                    image.setThumbFilePath(storageResource.writeResource(inputStream, "image/" + thumbName));
                    image.setThumbFileName(thumbName);
                }
            } else {
                image.setThumbFilePath(image.getOriginFilePath());
                image.setThumbFileName(originalName);
            }
            image = uploadFileRepository.save(image);
            return image;
        } catch (IOException e) {
            throw new LovelyException(e.getMessage());
        }


    }

    @Override
    public InputStream getInputStream(final String fileName) {
        return storageResource.readResource(fileName);
    }

    private ByteArrayOutputStream createThumbnail(final MultipartFile originalFile, String contentType, String fileName) {
        try {
            String formatType;
            if ((contentType != null && contentType.contains("png")) || fileName.contains("png")) {
                formatType = "png";
            } else {
                formatType = "jpeg";
            }
            ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
            BufferedImage img = ImageIO.read(originalFile.getInputStream());
            BufferedImage thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, Math.min(img.getWidth(), 1000), Scalr.OP_ANTIALIAS);
            ImageIO.write(thumbImg, formatType, thumbOutput);
            return thumbOutput;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
