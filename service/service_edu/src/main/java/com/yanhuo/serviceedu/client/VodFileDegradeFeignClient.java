package com.yanhuo.serviceedu.client;

import com.yanhuo.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VideoClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R removeVideoList(List<String> sourceIdList) {
        return R.error().message("time out");
    }


}