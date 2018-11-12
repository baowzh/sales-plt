package com.xunno.sales.view.modules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.asiainfo.ewebframe.config.WebSysConfig;
import com.xunno.sales.basic.model.FileContent;

public class FileUploadControler {

	@Autowired
	private WebSysConfig config;
	@Value("${file.maxSize}")
	private Integer maxSize;

	protected String wiredFile(HttpServletRequest request) throws Exception {
		//
		if (!ServletFileUpload.isMultipartContent(request)) {
			return null;

		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> filenames = multipartRequest.getFileNames();
		MultipartFile file = null;
		while (filenames.hasNext()) {
			String filename = filenames.next();
			List<MultipartFile> fileList = multipartRequest.getFiles(filename);
			file = fileList.get(0);
		}
		if(file.getBytes()==null||file.getBytes().length==0){
			return null;
		}

		if (file.getSize() > maxSize) {

			throw new Exception("上传文件大小超过限制。");

		}
		String separator = File.separator;
		String savePath = request.getSession().getServletContext().getRealPath(separator);
		if (!savePath.endsWith(separator)) {
			savePath = savePath + separator;
		}
		savePath = savePath + "attached" + separator;
		// 定义允许上传的文件扩展名
		Map<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", config.getFileimage());
		extMap.put("flash", config.getFileflash());
		extMap.put("media", config.getFilemedia());
		extMap.put("file", config.getFilefile());
		String saveUrl = "/" + "attached" + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + separator;
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		//
		// 附件要上传到的目录
		extMap.put("attached", config.getFileattached());

		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {

			throw new Exception("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");

		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		try {
			File uploadedFile = new File(savePath, newFileName);
			OutputStream stram = new FileOutputStream(uploadedFile);
			stram.write(file.getBytes());
			stram.close();
			saveUrl = saveUrl + newFileName;

		} catch (Exception e) {
			throw new Exception("上传文件失败，" + e.getMessage());

		}
		//
		return saveUrl;
	}

	protected FileContent getFileContent(HttpServletRequest request) throws Exception {
		if (!ServletFileUpload.isMultipartContent(request)) {
			return null;

		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> filenames = multipartRequest.getFileNames();
		MultipartFile file = null;
		FileContent content = new FileContent();
		while (filenames.hasNext()) {
			String filename = filenames.next();
			List<MultipartFile> fileList = multipartRequest.getFiles(filename);
			file = fileList.get(0);
			content.setContnet(file.getBytes());
			String fileExt = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
			content.setExtendName(fileExt);
			return content;
		}
		return null;
	}

}
