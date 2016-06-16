package com.extra.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CKeditor_Upload extends HttpServlet{
	private static String baseDir;// CKEditor上傳檔案存放的目錄
    private static boolean debug = false;// 是否debug模式
    private static boolean enabled = false;
    private static Hashtable allowedExtensions;//可上傳的副檔名
    private static Hashtable deniedExtensions;//不可上傳的副檔名
    private static SimpleDateFormat dirFormatter;// 目錄格式yyyMM
    private static SimpleDateFormat fileFormatter;// 文件格式yyyyMMddHHmmssSSS

    /**
     * Servlet初始化方法
     */
    public void init() throws ServletException {
        //web.xml如果設為debug mode
        debug = (new Boolean(getInitParameter("debug"))).booleanValue();
        if (debug)
            System.out.println("\r\n---- SimpleUploaderServlet initialization started ----");
        // 目錄及文件的命名格式
        dirFormatter = new SimpleDateFormat("yyyyMM");
        fileFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // web.xml裡存放檔案的根目錄
        baseDir = getInitParameter("baseDir");
        // web.xml是否可上傳檔案
        enabled = (new Boolean(getInitParameter("enabled"))).booleanValue();
        if (baseDir == null)
            baseDir = "/UserFiles/";
        String realBaseDir = getServletContext().getRealPath(baseDir);
        File baseFile = new File(realBaseDir);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        // 黑名單及白名單,可上傳之副檔名
        allowedExtensions = new Hashtable(3);
        deniedExtensions = new Hashtable(3);
        // web.xml裡
        allowedExtensions.put("File",stringToArrayList(getInitParameter("AllowedExtensionsFile")));
        deniedExtensions.put("File",stringToArrayList(getInitParameter("DeniedExtensionsFile")));
        allowedExtensions.put("Image",stringToArrayList(getInitParameter("AllowedExtensionsImage")));
        deniedExtensions.put("Image",stringToArrayList(getInitParameter("DeniedExtensionsImage")));
        allowedExtensions.put("Flash",stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
        deniedExtensions.put("Flash",stringToArrayList(getInitParameter("DeniedExtensionsFlash")));
        if (debug)
            System.out.println("---- SimpleUploaderServlet initialization completed ----\r\n");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (debug)
            System.out.println("--- BEGIN DOPOST ---");
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        // File/Image/Flash
        String typeStr = request.getParameter("Type");
        if (typeStr == null) {
            typeStr = "File";
        }
        if (debug)
            System.out.println(typeStr);
        // 取得目前時間
        Date dNow = new Date();
        // 要上傳的目錄
        String currentPath = baseDir + typeStr + "/"
                + dirFormatter.format(dNow);
        // Web的實際位置
        String currentDirPath = getServletContext().getRealPath(currentPath);
        // 判斷檔案是否存在,不存在時則建立
        File dirTest = new File(currentDirPath);
        if (!dirTest.exists()) {
            dirTest.mkdirs();
        }
        // 檔案上傳後的目錄位置http://xxx.xxx.xx/123/
        currentPath = request.getContextPath() + currentPath;
        if (debug)
            System.out.println(currentDirPath);
        
        String newName = "";
        String fileUrl = "";
        if (enabled) {
            // 使用Apache Common的fileupload上傳
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List items = upload.parseRequest(request);
                Map fields = new HashMap();
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField())
                        fields.put(item.getFieldName(), item.getString());
                    else
                        fields.put(item.getFieldName(), item);
                }
                // CKEditor上傳form的upload元件名稱是upload
                FileItem uplFile = (FileItem) fields.get("upload");
                // 取得文件名稱做處理
                String fileNameLong = uplFile.getName();
                fileNameLong = fileNameLong.replace('\\', '/');
                String[] pathParts = fileNameLong.split("/");
                String fileName = pathParts[pathParts.length - 1];
                // 取得副檔名
                String ext = getExtension(fileName);
                // 實際要存檔的名稱
                fileName = fileFormatter.format(dNow) + "." + ext;
                // 上傳檔案的檔名
                String nameWithoutExt = getNameWithoutExtension(fileName);
                File pathToSave = new File(currentDirPath, fileName);
                fileUrl = currentPath + "/" + fileName;
                if (extIsAllowed(typeStr, ext)) {
                    int counter = 1;
                    while (pathToSave.exists()) {
                        newName = nameWithoutExt + "_" + counter + "." + ext;
                        fileUrl = currentPath + "/" + newName;
                        pathToSave = new File(currentDirPath, newName);
                        counter++;
                    }
                    uplFile.write(pathToSave);
                } else {
                    if (debug)
                        System.out.println("無效的檔案類型： " + ext);
                }
            } catch (Exception ex) {
                if (debug)
                    ex.printStackTrace();
            }
        } else {
            if (debug)
                System.out.println("未啟用CKEditor上傳功能!!");
        }
        // CKEditorFuncNum是要回傳位置
        String callback = request.getParameter("CKEditorFuncNum");
        out.println("<script>");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                + ",'" + fileUrl + "',''" + ");");
        out.println("</script>");
        // window.parent.CKEDITOR.tools.callFunction(xxxx, 'yyyy','')
        out.flush();
        out.close();
        if (debug)
            System.out.println("--- END DOPOST ---");
    }
    /**
     * 取得檔案檔名
     */
    private static String getNameWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
    /**
     * 取得檔案副檔名
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    /**
     * 字串轉換成ArrayList
     */
    private ArrayList stringToArrayList(String str) {
        if (debug)
            System.out.println(str);
        String[] strArr = str.split("\\|");
        ArrayList tmp = new ArrayList();
        if (str.length() > 0) {
            for (int i = 0; i < strArr.length; ++i) {
                if (debug)
                    System.out.println(i + " - " + strArr[i]);
                tmp.add(strArr[i].toLowerCase());
            }
        }
        return tmp;
    }
    /**
     * 判斷是否為可上傳檔案
     */
    private boolean extIsAllowed(String fileType, String ext) {
        ext = ext.toLowerCase();
        ArrayList allowList = (ArrayList) allowedExtensions.get(fileType);
        ArrayList denyList = (ArrayList) deniedExtensions.get(fileType);
        if (allowList.size() == 0) {
            if (denyList.contains(ext)) {
                return false;
            } else {
                return true;
            }
        }
        if (denyList.size() == 0) {
            if (allowList.contains(ext)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
