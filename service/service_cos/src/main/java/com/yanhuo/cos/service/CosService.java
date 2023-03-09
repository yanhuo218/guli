package com.yanhuo.cos.service;

import com.yanhuo.cos.entity.VideoInfoVo;

import java.io.File;
import java.util.List;

public interface CosService {
    boolean updateFileAvatar(File file, String url);

    VideoInfoVo UploadFileVideo(String file);

    boolean deleteVideo(String id);

    boolean removeVideoList(List<String> sourceIdList);
}
