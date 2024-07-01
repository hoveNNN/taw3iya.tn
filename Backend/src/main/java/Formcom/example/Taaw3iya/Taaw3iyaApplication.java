package Formcom.example.Taaw3iya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Formcom.example.Taaw3iya.business.services.IFilesStorageService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Taaw3iyaApplication implements CommandLineRunner {
	@Resource
	IFilesStorageService filesStorageService;
	public static void main(String[] args) {
		SpringApplication.run(Taaw3iyaApplication.class, args);
		System.out.println("Apllication Started");
		System.out.println("Apllication Started3");

	}
	@Override
	public void run(String... args)throws Exception{
	 	log.info("Storage initialisation");
		 filesStorageService.init();
	}

}
