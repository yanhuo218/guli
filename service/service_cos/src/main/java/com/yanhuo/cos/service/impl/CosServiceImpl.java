package com.yanhuo.cos.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.yanhuo.cos.entity.VideoInfoVo;
import com.yanhuo.cos.service.CosService;
import com.yanhuo.cos.utils.VideoGetSize;
import com.yanhuo.servicebase.exceptionhandler.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static com.yanhuo.cos.utils.ConstantPropertiesUtil.*;

@Slf4j
@Service
public class CosServiceImpl implements CosService {

    @Override
    public boolean updateFileAvatar(File file, String url) {
        try {
            BasicCOSCredentials credentials = new BasicCOSCredentials(APPID, ACCESS_KEY, SECRET_KEY);
            ClientConfig clientConfig = new ClientConfig(new Region(REGION_NAME));
            COSClient client = new COSClient(credentials, clientConfig);
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, url, file);
            client.putObject(putObjectRequest);
            file.delete();
            log.info("add_img---"+url);
            return true;
        } catch (Exception e) {
            throw new GuliException(20001,"上传图片错误:" + e);
        }
    }

    @Override
    public VideoInfoVo UploadFileVideo(String file) {
        try {
            VodUploadClient client = new VodUploadClient(ACCESS_KEY, SECRET_KEY);
            VodUploadRequest request = new VodUploadRequest();
            request.setMediaFilePath(file);
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            String fileId = response.getFileId();
            VideoInfoVo infoVo = new VideoInfoVo();
            infoVo.setVideoId(response.getFileId());
            infoVo.setDuration(VideoGetSize.getDuration(fileId));
            infoVo.setSize(VideoGetSize.getSize(fileId));
            return infoVo;
        } catch (Exception e) {
            throw new GuliException(20001, "上传失败:" + e);
        }
    }

    @Override
    public boolean deleteVideo(String id) {
        try {
            log.info("DeleteVideoId = " + id);
            Credential cred = new Credential(ACCESS_KEY, SECRET_KEY);
            VodClient client = new VodClient(cred, "ap-guangzhou");
            DeleteMediaRequest reqDelete = new DeleteMediaRequest();
            reqDelete.setFileId(id);
            client.DeleteMedia(reqDelete);
            return true;
        } catch (Exception e) {
            throw new GuliException(20001, "删除失败");
        }
    }

    @Override
    public boolean removeVideoList(List<String> sourceIdList) {
        try {
            for (String listId : sourceIdList) {
                this.deleteVideo(listId);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
