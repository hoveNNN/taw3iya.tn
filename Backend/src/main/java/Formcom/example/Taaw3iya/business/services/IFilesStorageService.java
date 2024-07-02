package Formcom.example.Taaw3iya.business.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;



public interface IFilesStorageService {
    
    public void init();

    public String save(MultipartFile file);

    public Resource load(String name);

    public void delete(String filename);
}
