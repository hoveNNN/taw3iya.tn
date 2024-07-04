package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.services.AuthenticationService;
import Formcom.example.Taaw3iya.business.serviceslmpl.FilesStorageServiceImpl;
import Formcom.example.Taaw3iya.web.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/storage")
@CrossOrigin("http:localhost:8005")
public class FilesStorageController {

    @Autowired
    FilesStorageServiceImpl StorageService;

    @Autowired
    private AuthenticationService authservice;

    @RequestMapping("/uploadphotouser")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
      Long  iduser= Long.valueOf(getidUser());
        String message="";
        try{
            String fileName = StorageService.save(file);
            this.authservice.updateUserImage(iduser,fileName);
            message = "File uploaded Successfully"+file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch(Exception e){
            message = "could Not upload the file"+file.getOriginalFilename()+".error:"+e.getMessage();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    public Integer getidUser(){
        Integer idd=-1;
        String currentUserName="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();

            idd= authservice.getidOfUserAuth(currentUserName);
        }
        return idd;
    }
}
