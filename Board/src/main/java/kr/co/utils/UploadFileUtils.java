package kr.co.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {
	
	public static ResponseEntity<String> deletefile(String uploadPath, String filename){
		
		ResponseEntity<String> entity = null;
		
		try {
			
			filename = filename.replace('/', File.separatorChar);
			
			String formatName = filename.substring(filename.lastIndexOf(".")+1);
			boolean isImg = UploadFileUtils.checkFormat(formatName);
			
			if(isImg) {
				String newfilename = filename.replace("s_", "");
				File ori_img = new File(uploadPath+newfilename);
				ori_img.delete();
			}
			
			File generalAndThumb = new File(uploadPath+filename);
			
			generalAndThumb.delete();
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} finally {
			try {

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return entity;
	}

	public static String makeIcon(String uploadPath, String datePath, String savedName) throws Exception {
		
		File file = new File(uploadPath+datePath, savedName);
		
		BufferedImage sourceImg = ImageIO.read(file);
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 100);
		// sourceImg : 원본 이미지 / Scalr.Method.AUTOMATIC : 왜곡을 자동으로 맞춘다 / Scalr.Mode.FIT_EXACT, 100(px) : 가로세로를 100으로 크기를 맞춘다
		
		String thumbnailName = uploadPath + datePath + File.separator + "s_" + savedName;
		
		String formatName = savedName.substring(savedName.lastIndexOf(".")+1);
		
		File newFile = new File(thumbnailName);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile); //확장자명을 대문자로 통일
		
		String bp = thumbnailName.substring(uploadPath.length());
		
		String bp_suffix = bp.substring(bp.lastIndexOf(".")+1);
		bp = bp.replace(bp_suffix,  bp_suffix.toLowerCase());
		
		String ap = bp.replace(File.separatorChar, '/');
		
		return ap;
	}
	
	public static String uploadFile(String uploadPath, MultipartFile file) throws Exception {
		
		String ori_name = file.getOriginalFilename();
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+ori_name; //실제로 할때는 "_new_"처럼 구분할 수 있는 값을 넣어줘야함
		String datePath = calPath(uploadPath);
		
		
		File target = new File(uploadPath+datePath, savedName);
		if(ori_name ==null || ori_name.equals("")) {
			return "";
		}
		FileCopyUtils.copy(file.getBytes(), target);
		
		String formatName =ori_name.substring(ori_name.lastIndexOf(".")+1);
		
		if(checkFormat(formatName)) {
			String ap = makeIcon(uploadPath, datePath, savedName);
			return ap;
		}else {
			String bp = datePath + File.separator +savedName;
			String ap = bp.replace(File.separatorChar, '/');
			return ap;
		}
	}
	
	public static boolean checkFormat(String formatName) {
		List<String> list = new ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("png");
		list.add("gif");
		list.add("jpg".toUpperCase());
		list.add("jpeg".toUpperCase());
		list.add("png".toUpperCase());
		list.add("gif".toUpperCase());
		return list.contains(formatName); //contains = 포함하고 있는가? 로 포함하면 true, 없으면 false
	}

	public static String calPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format((cal.get(Calendar.MONTH)+1));
		//month는 0부터 시작이므로 1 더하기, new DecimalFormat을 이용해 1~9를 01의 형태로 만들어준다
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}

	public static void makeDir(String uploadPath, String...arr) { //원래 private로 작업 해야함
		//arr[0] = yearPath, arr[1] = monthPath, arr[2] = datePath
		
		if(new File(uploadPath+arr[arr.length-1]).exists()) {
			return;
		}
		
		for(String path : arr) {
			File dirPath = new File(uploadPath+path); //년, 월, 일 폴더가 순차적으로 생성됨
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	public static ResponseEntity<byte[]> displayfile(String uploadPath, String filename) {
		//작업해뒀던 displayfile100 복사해서 붙여넣음
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		try {
			in = new FileInputStream(uploadPath+File.separator+filename);
			HttpHeaders headers = new HttpHeaders();
			
			setContent(headers, filename);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if(in!=null)in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return entity;
	}

	private static void setContent(HttpHeaders headers, String filename) throws Exception {
		
		String formatName = filename.substring(filename.lastIndexOf(".")+1);
		
		if(formatName.equalsIgnoreCase("png")) {
			headers.setContentType(MediaType.IMAGE_PNG);
		}else if(formatName.equalsIgnoreCase("jpg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		}else if(formatName.equalsIgnoreCase("jpeg")) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		}else if(formatName.equalsIgnoreCase("gif")) {
			headers.setContentType(MediaType.IMAGE_GIF);
		}else {
			filename = filename.substring(filename.indexOf("_")+1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //클릭시 다운로드 되게 만드는 문장
			headers.add("Content-Disposition", "attachment;filename=\""+new String(filename.getBytes("UTF-8"), "ISO-8859-1")+"\"");
		}
		
	}
}
