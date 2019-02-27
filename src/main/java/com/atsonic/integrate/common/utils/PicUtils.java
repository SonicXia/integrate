package com.atsonic.integrate.common.utils;

import com.atsonic.integrate.common.cache.Maps;
import com.atsonic.integrate.common.config.MyConfig;
import com.atsonic.integrate.common.pojo.BaseLogger;
import com.atsonic.integrate.common.pojo.Rsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sonic
 */
//@EnableConfigurationProperties(MyConfig.class)
@Component
public class PicUtils implements BaseLogger {

    @Autowired
    private MyConfig myConfig;

//    public static PicUtils picUtils;
//
//    public PicUtils() {
//    }
//
//    @PostConstruct
//    public void init() {
//        picUtils = this;
//        picUtils.myConfig = this.myConfig;
//    }

    /**
     * 上传图片
     *
     * @param request
     * @param picFile
     * @param id
     * @return
     */
    public Rsp uploadPic(HttpServletRequest request, MultipartFile picFile, String id) {
        Rsp rsp = new Rsp();
        try {
            String picFileFormat = picFile.getOriginalFilename().split("\\.")[1];
            String[] formats = myConfig.getPicFormat().split(",|，");
            boolean isOk = false;
            for (String f : formats) {
                if (f.equalsIgnoreCase(picFileFormat)) {
                    isOk = true;
                }
            }
            if (!isOk) {
                logger.info("图片格式不符合要求");
                return rsp.fail("图片格式不符合要求");
            }
            String picName = id + "_" + System.currentTimeMillis() + "." + picFileFormat;
            // 缓存上传的证件图片文件名
            Maps.saveUploadPic(id, picName);

            String certificationUrl = myConfig.getCertificationUrl();
            String path = request.getServletContext().getRealPath(certificationUrl) + picName;
            String returnPath = certificationUrl + picName;
            // 根据路径上传文件
            FileUtil.exportFile(path, picFile);
            logger.info("上传成功");
            return rsp.success(returnPath);
        } catch (Exception e) {
            e.printStackTrace();
            return rsp.fail("上传失败");
        }
    }

    /**
     * 删除图片
     *
     * @param request
     * @param id
     * @return
     */
    public Rsp deletePic(HttpServletRequest request, String id) {
        Rsp rsp = new Rsp();
        String certificationUrl = myConfig.getCertificationUrl();
        File picFile = new File(request.getServletContext().getRealPath(certificationUrl));
        File[] tempFile = picFile.listFiles();
        boolean deleteSuccess = true;
        List<String> failList = new ArrayList();
        List<String> successList = new ArrayList();
        int length = tempFile.length;

        // 当前缓存的图片名称（待删的）
        List<String> picNameList = Maps.getPicNameListMapById(id);

        for (int i = 0; i < length; i++) {
            String picName = tempFile[i].getName();

            if (picNameList.contains(picName)) {
                if (picName.startsWith(id)) {
                    deleteSuccess = deleteFile(request.getServletContext().getRealPath(certificationUrl) + picName);
                    if (deleteSuccess) {
                        successList.add(picName);
                    } else {
                        failList.add(picName);
                    }
                }
            }


        }
        for (String picName : successList) {
            Maps.removeUploadPicByPicName(id, picName);
            logger.info("图片 " + picName + " 删除成功");
        }
        for (String picName : failList) {
            logger.info("图片 " + picName + " 删除失败");
        }
        if (failList != null && !failList.isEmpty()) {
            return rsp.fail();
        } else {
            return rsp.success();
        }
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    private static boolean deleteFile(String path) {
        boolean del = false;
        File file = new File(path);
        if (file.isFile()) {
            file.delete();
            del = true;
        }
        return del;
    }




}
