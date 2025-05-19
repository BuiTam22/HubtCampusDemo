package com.codecampushubt.NCKH2024TQQD.service.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        Map UploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return UploadResult.get("secure_url").toString();
    }
}
