package kr.co.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {

	public static String uploadFile(String uploadPath, MultipartFile file) throws Exception {
		
		String ori_name = file.getOriginalFilename();
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+ori_name; //실제로 할때는 "_new_"처럼 구분할 수 있는 값을 넣어줘야함
		String datePath = calPath(uploadPath);
		
		
		File target = new File(uploadPath+datePath, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		
		String bp = datePath + File.separator +savedName;
		String ap = bp.replace(File.separatorChar, '/');
		
		return ap;
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
}
