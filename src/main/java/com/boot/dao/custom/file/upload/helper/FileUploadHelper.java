package com.boot.dao.custom.file.upload.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String uploadDir = "/Users/ishasethia/IdeaProjects/daocustom/src/main/resources/static/image";
    public boolean uploadFile(MultipartFile file){
        boolean f = false;
        try{
            /*InputStream inputStream = file.getInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);

            //writing
            FileOutputStream fileOutputStream = new FileOutputStream(uploadDir+ File.separator+ file.getOriginalFilename());
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();*/
            Files.copy(file.getInputStream(), Paths.get(uploadDir+ File.separator+ file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;

        }catch (Exception e){
          e.printStackTrace();
        }
        return f;
    }

}
