package com.bin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bin.service.modifyInfoService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 * 进行用户修改个人信息后的逻辑处理
 */
@WebServlet(name = "modifyInfoServlet")
public class modifyInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");


        Map<String, String> newInfo = new HashMap<>();
        String userId = (String) request.getSession().getAttribute("name");
        String trueName = null;//文件名


        if (ServletFileUpload.isMultipartContent(request)){
            //文件存储路径
            String path=this.getServletContext().getRealPath("/resources/images");
            //临时文件存储路径
            String tempPath=this.getServletContext().getRealPath("/resources/temp");
            File file=new File(path);
            File temp=new File(tempPath);
            //如果目录不存在就创建
            if(!file.exists() && !file.isDirectory()){
                file.mkdirs();
            }
            //如果目录不存在就创建
            if(!temp.exists() && !temp.isDirectory()){
                file.mkdirs();
            }
            DiskFileItemFactory factory=new DiskFileItemFactory();
            //设置缓存区大小，当上传文件超过缓存区大小时候就会存储到临时文件中,缓存区默认大小事10kb
            factory.setSizeThreshold(1024*100);//设置缓存区为100kb
            //设置临时文件保存目录
            factory.setRepository(temp);
            ServletFileUpload upload=new ServletFileUpload(factory);
            //如果上传文件是中文，解决上传文件名的中文乱码
            upload.setHeaderEncoding("GBK");
            //监听上传文件进度
            upload.setProgressListener(new ProgressListener(){
                @Override
                public void update(long arg0, long arg1, int arg2) {
                    System.out.println("当前已经上传"+arg0);
                    System.out.println("总大小"+arg1);
                }
            });
            //设置上传一个文件的最大容量
            upload.setFileSizeMax(1024*1000);
            //设置上传文件的总容量
            upload.setSizeMax(1024*1000*10);
            List<FileItem> list=null;
            try {
                list=upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }

            for (FileItem item:list){
                if (item.isFormField()){
                    newInfo.put(item.getFieldName(),item.getString("utf-8"));
                    System.out.println(item.getFieldName());
                }
                else{
                    //上传文件的文件名称
                    String fileName=item.getName();
                    //System.out.println(fileName);
                    //去掉文件路径，取得文件名
                    fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                    //获取文件的扩展名
                    String fileExt=fileName.substring(fileName.lastIndexOf(".")+1);
                    System.out.println(fileExt);
                    //生成唯一的文件名
                    trueName= UUID.randomUUID().toString()+fileName;
                    InputStream is=item.getInputStream();
                    System.out.println(path+File.separator+fileName);
                    OutputStream os=new FileOutputStream(path+File.separator+trueName);
                    byte[] flush=new byte[1024];
                    int len=0;
                    while( (len=is.read())!=-1 ){
                        os.write(len);
                    }
                    os.flush();
                    os.close();
                    is.close();
                    item.delete();
                }
            }
        }
        newInfo.put("photo",trueName);
        new modifyInfoService().updateInfo(userId, newInfo);
        request.getRequestDispatcher("/resources/html/resume.html").forward(request, response);
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
