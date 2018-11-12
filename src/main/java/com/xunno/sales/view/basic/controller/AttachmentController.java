package com.xunno.sales.view.basic.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.asiainfo.ewebframe.config.WebSysConfig;

import net.sf.json.JSONObject;
@RequestMapping(value = "file")
@Controller
public class AttachmentController {
	@Autowired
	private WebSysConfig config;
	@Value("${file.maxSize}")
	private long maxSize;

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String separator = File.separator;
		String savePath = request.getSession().getServletContext().getRealPath(separator);
		if (!savePath.endsWith(separator)) {
			savePath = savePath + separator;
		}
		savePath = savePath + "attached" + separator;
		// localUrl
		// 文件保存目录URL
		String saveUrl = "/" + "attached" + "/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", config.getFileimage());
		extMap.put("flash", config.getFileflash());
		extMap.put("media", config.getFilemedia());
		extMap.put("file", config.getFilefile());

		// 附件要上传到的目录
		extMap.put("attached", config.getFileattached());
		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");

		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");

		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");

		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "attached";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");

		}
		// 创建文件夹
		savePath += dirName + separator;
		saveUrl += dirName + "/";
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
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> filenames = multipartRequest.getFileNames();
		while (filenames.hasNext()) {
			String filename = filenames.next();
			List<MultipartFile> fileList = multipartRequest.getFiles(filename);
			for (MultipartFile multipartFile : fileList) {
				if (multipartFile.getSize() > maxSize) {
					return getError("上传文件大小超过限制。");

				}
				String fileName = multipartFile.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");

				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					OutputStream stram = new FileOutputStream(uploadedFile);
					stram.write(multipartFile.getBytes());
					stram.close();
					returnMap.put("error", 0);
					returnMap.put("url", request.getContextPath() + saveUrl + newFileName);
					returnMap.put("file", saveUrl + newFileName);
					returnMap.put("name", fileName);
					returnMap.put("size", multipartFile.getSize());
					// returnMap.put("id", "file_"+UUIDGenerator.generate());
				} catch (Exception e) {
					return getError("上传文件失败。");

				}

			}

		}
		// return returnMap;
		return JSONObject.fromObject(returnMap).toString();
	}

	/**
	 * 错误信息
	 * 
	 * @param message
	 * @return
	 */
	private String getError(String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// JSONObject obj = new JSONObject();
		returnMap.put("error", 1);
		returnMap.put("message", message);
		return JSONObject.fromObject(returnMap).toString();
		// return returnMap;
	}

	@RequestMapping("/manager")
	@ResponseBody
	public String manager(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * KindEditor JSP
		 *
		 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
		 *
		 */

		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		if (!rootPath.endsWith(File.separator)) {
			rootPath = rootPath + File.separator;
		}
		rootPath = rootPath + "attached" + File.separator;
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl = request.getContextPath() + "/attached/";
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
				return "Invalid Directory name.";
			}
			rootPath += dirName + File.separator;
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			return "Access is not allowed.";
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			return "Parameter is not valid.";

		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			return "Directory does not exist.";

		}

		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		response.setContentType("application/json; charset=UTF-8");
		return result.toString();
	}

	private class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	private class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	private class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}

	@RequestMapping(value = "/downAttached")
	public ResponseEntity<byte[]> getFileDetail(HttpServletRequest request, HttpServletResponse response, String file)
			throws IOException {
		if (StringUtils.isEmpty(file)) {
			return null;
		}
		String[] files = file.split(":");
		if (files.length != 2) {
			return null;
		}
		String file_name = files[1];
		// String file0=files[0];
		// String file_path=file0.replaceFirst(request.getContextPath(), "");
		HttpHeaders responseHeaders = new HttpHeaders();

		if (file_name.endsWith(".JPEG") || file_name.endsWith(".JPG") || file_name.endsWith(".jpeg")
				|| file_name.endsWith(".jpg"))
			responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		else if (file_name.endsWith(".GIF") || file_name.endsWith(".gif"))
			responseHeaders.setContentType(MediaType.IMAGE_GIF);
		else if (file_name.endsWith(".png") || file_name.endsWith(".PNG"))
			responseHeaders.setContentType(MediaType.IMAGE_PNG);
		else if (file_name.endsWith(".DOC") || file_name.endsWith(".doc") || file_name.endsWith(".DOCX")
				|| file_name.endsWith(".docx"))
			responseHeaders.add("Content-Type", "application/vnd.msword");
		else if (file_name.endsWith(".XLS") || file_name.endsWith(".xls") || file_name.endsWith(".XLSX")
				|| file_name.endsWith(".xlsx"))
			responseHeaders.add("Content-Type", "application/vnd.ms-excel");
		else if (file_name.endsWith(".PPT") || file_name.endsWith(".ppt") || file_name.endsWith(".PPTX")
				|| file_name.endsWith(".pptx"))
			responseHeaders.add("Content-Type", "application/vnd.ms-powerpoint");
		else if (file_name.endsWith(".pdf") || file_name.endsWith(".pdf"))
			responseHeaders.add("Content-Type", "application/pdf");
		else
			responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		responseHeaders.setCacheControl("no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
		responseHeaders.setPragma("no-cache");
		responseHeaders.add("Content-Disposition",
				"attachment;filename=" + new String(file_name.getBytes("UTF-8"), "ISO8859-1"));

		String basePath = request.getSession().getServletContext().getRealPath(File.separator);
		if (!basePath.endsWith(File.separator)) {
			basePath += File.separator;
		}
		byte[] file_b = null;
		try {
			File srcFile = new File(basePath + files[0]);
			file_b = FileUtils.readFileToByteArray(srcFile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		responseHeaders.setContentLength(file_b.length);
		return new ResponseEntity<byte[]>(file_b, responseHeaders, HttpStatus.OK);
	}

}
