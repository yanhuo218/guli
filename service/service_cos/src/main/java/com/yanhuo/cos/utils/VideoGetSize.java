package com.yanhuo.cos.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosResponse;
import com.yanhuo.servicebase.exceptionhandler.GuliException;

import static com.yanhuo.cos.utils.ConstantPropertiesUtil.ACCESS_KEY;
import static com.yanhuo.cos.utils.ConstantPropertiesUtil.SECRET_KEY;

public class VideoGetSize {
    private static final Credential cred;
    private static final VodClient client;
    private static final DescribeMediaInfosRequest req;
    private static DescribeMediaInfosResponse resp;
    static {
        try {
            cred = new Credential(ACCESS_KEY, SECRET_KEY);
            client = new VodClient(cred, "");
            req = new DescribeMediaInfosRequest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSize(String id) {
        try {
            req.setFileIds(new String[]{id});
            req.setFilters(new String[]{"metaData"});
            resp = client.DescribeMediaInfos(req);
            return resp.getMediaInfoSet()[0].getMetaData().getSize().toString();
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDuration(String id) {
        try {
            req.setFileIds(new String[]{id});
            req.setFilters(new String[]{"metaData"});
            resp = client.DescribeMediaInfos(req);
            return resp.getMediaInfoSet()[0].getMetaData().getDuration().toString();
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
    }
}
