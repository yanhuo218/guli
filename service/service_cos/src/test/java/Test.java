import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosResponse;
import com.tencentcloudapi.vod.v20180717.models.MediaInfo;


public class Test {
    public static void main(String[] args) {
        try {
            Credential cred = new Credential("AKID73mIYZNxHK9i2b7QWQ4VvIXlhZCdqr5D", "1tZ9Wt56vVs1uWvCH8k5FAqWyDE2Zqsl");
            VodClient client = new VodClient(cred, "");
            DescribeMediaInfosRequest req = new DescribeMediaInfosRequest();
            String[] fileIds1 = {"243791579866067643"};
            req.setFileIds(fileIds1);
            String[] filters1 = {"metaData"};
            req.setFilters(filters1);
            DescribeMediaInfosResponse resp = client.DescribeMediaInfos(req);
            System.out.println(resp.getMediaInfoSet()[0].getMetaData().getSize());
            System.out.println(DescribeMediaInfosResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
