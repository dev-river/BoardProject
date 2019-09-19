package kr.co.ca;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.utils.UploadFileUtils;

@Controller
public class UploadController {
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping("/displayfile100")
	@ResponseBody
	public ResponseEntity<byte[]> displayfile(String filename){
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		try {
			in = new FileInputStream(uploadPath+File.separator+filename);
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.IMAGE_PNG);
			
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

	@RequestMapping("/uploadform")
	public void uploadform() {
		
	}
	
	@RequestMapping(value="/uploadform", method=RequestMethod.POST)
	public String uploadform(MultipartHttpServletRequest request, Model model) throws Exception {
		
		List<MultipartFile> list = request.getFiles("file");
		List<String> savedNameList = new ArrayList<String>();
		
		for(MultipartFile x : list) {
			String savedName = UploadFileUtils.uploadFile(uploadPath, x);
			savedNameList.add(savedName);
		}
		
		model.addAttribute("savedNameList", savedNameList);
		
		return "showupload";
	}
}
