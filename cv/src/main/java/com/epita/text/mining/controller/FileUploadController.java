package com.epita.text.mining.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Bhrigu Mahajan
 *
 */
@Controller
public class FileUploadController {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@GetMapping("/")
	public String fileUploadForm(Model model) {

		return "fileUploadForm";
	}

	// Handling single file upload request
	@SuppressWarnings("unused")
	@PostMapping("/singleFileUpload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {

		// Save file on system
		if (!file.getOriginalFilename().isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.flush();
				stream.close();
				String fileLocation = dir.getPath().concat(File.separator).concat(file.getOriginalFilename());
				readDocxFile(serverFile);
			} catch (Exception e) {
				return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
			}

		} else {
			model.addAttribute("msg", "Please select a valid file..");
			return "fileUploadForm";
		}
		model.addAttribute("msg", "File uploaded successfully.");
		return "fileUploadForm";
	}

	// Handling multiple files upload request
	@SuppressWarnings("unused")
	@PostMapping("/multipleFileUpload")
	public String multipleFileUpload(@RequestParam("file") MultipartFile[] files, Model model) throws IOException {

		// Save file on system
		for (MultipartFile file : files) {
			if (!file.getOriginalFilename().isEmpty()) {

				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.flush();
				stream.close();
				readDocxFile(serverFile);
			} else {
				model.addAttribute("msg", "Please select at least one file..");
				return "fileUploadForm";
			}
		}
		model.addAttribute("msg", "Multiple files uploaded successfully.");
		return "fileUploadForm";
	}

	@SuppressWarnings("resource")
	public static List<String> readDocxFile(File serverFile) {
		List<String> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(serverFile);

			XWPFDocument document = new XWPFDocument(fis);

			List<XWPFParagraph> paragraphs = document.getParagraphs();

			System.out.println("Total no of paragraph " + paragraphs.size());
			StringBuffer st = new StringBuffer();
			for (XWPFParagraph para : paragraphs) {
				System.out.println(para.getText());
				st.append(para.getText());
			}
			list.add(st.toString());
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void readDocFile(File fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);

			HWPFDocument doc = new HWPFDocument(fis);

			WordExtractor we = new WordExtractor(doc);

			String[] paragraphs = we.getParagraphText();

			System.out.println("Total no of paragraph " + paragraphs.length);
			for (String para : paragraphs) {
				System.out.println(para.toString());
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveData(List<String> p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
	}

}
