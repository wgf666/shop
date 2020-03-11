package back.web.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@Controller
@RequestMapping("file")
public class FileController {
    @Autowired
    private FastFileStorageClient client;
    @Value("${image.server}")
    private String IMAGE_SERVER;

    @RequestMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file){
        System.out.println(file+"fffffffffff");
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //通过spring boot把文件保存到fastDFS中
        try {
            StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), suffix, null);
            //获取保存后的路径返回给前端
            String fullPath = storePath.getFullPath();
            String pathStr = new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
            return new ResultBean(200,pathStr);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean(500,"操作失败，请售后重新上传");
        }
    }

   /* @ResponseBody
    @RequestMapping("multiUpload")
    public MultiResultBean multiUpload(MultipartFile[] files){
        MultiResultBean resultBean = new MultiResultBean();
        String[] datas = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            //获取文件名称
            String originalFilename = files[i].getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //通过spring boot把文件保存到fastDFS中
            try {
                StorePath storePath = client.uploadFile(files[i].getInputStream(), files[i].getSize(), suffix, null);
                //获取保存后的路径返回给前端
                String fullPath = storePath.getFullPath();
                String pathStr = new StringBuilder(IMAGE_SERVER).append(fullPath).toString();
                datas[i] = pathStr;
            } catch (IOException e) {
                e.printStackTrace();
                resultBean.setErrno("-1");
                return resultBean;
            }
        }
        resultBean.setErrno("0");
        resultBean.setData(datas);
        return resultBean;
    }*/
}
