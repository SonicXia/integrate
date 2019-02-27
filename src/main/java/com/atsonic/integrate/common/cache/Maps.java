package com.atsonic.integrate.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sonic
 */
public class Maps {

    /**
     * 证件图片文件名缓存map
     * <sid/agentId, List<picName>>
     */
    private static Map<String, List<String>> UPLOAD_PIC_MAP = new HashMap();


    /**
     * 缓存上传的证件图片文件名
     *
     * @param id
     * @param picName
     */
    public static void saveUploadPic(String id, String picName) {
        if (UPLOAD_PIC_MAP.containsKey(id)) {
            UPLOAD_PIC_MAP.get(id).add(picName);
        } else {
            List picNameList = new ArrayList();
            picNameList.add(picName);
            UPLOAD_PIC_MAP.put(id, picNameList);
        }
    }

    /**
     * 根据id获取缓存的证件图片文件名list
     * @param id
     * @return
     */
    public static List<String> getPicNameListMapById(String id) {
        if(UPLOAD_PIC_MAP.containsKey(id)) {
            return UPLOAD_PIC_MAP.get(id);
        }
        return null;
    }

    /**
     * 删除缓存上传的证件图片文件名list
     *
     * @param id
     */
    public static void removeUploadPicListMapById(String id) {
        if (UPLOAD_PIC_MAP.containsKey(id)) {
            UPLOAD_PIC_MAP.remove(id);
        }
    }

    /**
     * 删除指定的缓存上传的证件图片文件名
     *
     * @param id
     * @param picName
     */
    public static void removeUploadPicByPicName(String id, String picName) {
        if (UPLOAD_PIC_MAP.containsKey(id)) {
            UPLOAD_PIC_MAP.get(id).remove(picName);
        }
    }

}
