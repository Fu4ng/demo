package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
//import java.io.File;
//import java.io.IOException;
//
//@SpringBootApplication
//public class DemoApplication {
//	public static final String ROOT = "D:\\upload-dir";
//	public static void main(String[] args) throws IOException {
//		File file=new File(ROOT);
//		if(!file.exists()) {
//			file.mkdir();
//		}
//
//		SpringApplication.run(DemoApplication.class, args);
//	}
//}