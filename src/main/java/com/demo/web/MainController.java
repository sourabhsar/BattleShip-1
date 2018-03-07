package com.demo.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.exceptionhandlers.InputException;
import com.demo.facade.StartGame;

@Controller
public class MainController {

	@Autowired
	StartGame start;
	
	private final String uploadFolder = "C:\\Users\\Public\\Documents\\";
	
	@RequestMapping(value = { "/", "/home" }, method = { RequestMethod.GET })
	public ModelAndView visitHome() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@PostMapping("/uploadFile")
	public ModelAndView uploadFiles(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException, InputException {
		ModelAndView mav = new ModelAndView();
		
		File folder = new File(uploadFolder);
		if (!folder.exists()) {
			mav.addObject("error", "Upload dir not found.");
			mav.setViewName("home");
			return mav;
		}
		if (file.isEmpty()) {
			mav.addObject("error", "Empty File Provided.");
			mav.setViewName("home");
			return mav;
		}
		
		byte[] bytes = file.getBytes();
		Path path = Paths.get(uploadFolder + file.getOriginalFilename());
		Files.write(path, bytes);
		File inputFile=new File(uploadFolder + file.getOriginalFilename());
		mav.setViewName("result");
		mav.addObject("result",start.init(inputFile));
		return mav;
	}

	@PostMapping("/input")
	public ModelAndView visitInfo(@RequestParam(name = "input") String input) throws IOException, InputException{
		ModelAndView mav = new ModelAndView("result");
		byte[] bytes = input.getBytes();
		Path path = Paths.get(uploadFolder + "inputFile.txt");
		Files.write(path, bytes);
		File inputFile=new File(uploadFolder + "inputFile.txt");
		mav.setViewName("result");
		mav.addObject("result",start.init(inputFile));
		start.init(inputFile);
		return mav;
	}

}
