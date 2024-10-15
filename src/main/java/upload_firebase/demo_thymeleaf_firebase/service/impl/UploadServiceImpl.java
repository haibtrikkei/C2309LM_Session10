package upload_firebase.demo_thymeleaf_firebase.service.impl;

import com.google.cloud.storage.*;
import jakarta.servlet.ServletContext;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import upload_firebase.demo_thymeleaf_firebase.service.UploadService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private ServletContext servletContext;
    @Value("${buck_name}")
    private String buck_name;
    @Autowired
    private Storage storage;

    @Override
    public String uploadToLocal(MultipartFile file) {
        String path = servletContext.getRealPath("/uploads");
        File dest = new File(path);

        if(!dest.exists())
            dest.mkdir();  //Tao thu muc

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss");
        String fileName = formatter.format(new Date().toInstant().atOffset(ZoneOffset.of("+07:00")))+"_"+file.getOriginalFilename();
        String localPath = dest.getAbsolutePath()+File.separator+fileName;

        try {
            FileCopyUtils.copy(file.getBytes(),new File(localPath));
            return uploadToFirebase(localPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadToFirebase(String localPath) {
        Path path = Paths.get(localPath);
        String fileName = path.getFileName().toString();

        BlobId blobId = BlobId.of(buck_name, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        List<Acl> aclList = new ArrayList<>();
        aclList.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        blobInfo = blobInfo.toBuilder().setAcl(aclList).build();

        try {
            Blob blob = storage.createFrom(blobInfo, path);
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
