package upload_firebase.demo_thymeleaf_firebase.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadToLocal(MultipartFile file);
    String uploadToFirebase(String localPath);
}
