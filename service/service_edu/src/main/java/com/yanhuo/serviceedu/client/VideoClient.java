package com.yanhuo.serviceedu.client;

import com.yanhuo.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-cos", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VideoClient {
    @DeleteMapping("/edu_cos/file/deleteVideo/{id}")
    R removeVideo(@PathVariable("id") String id);

    @DeleteMapping("/edu_cos/file/deleteListVideo")
    R removeVideoList(@RequestParam("sourceIdList") List<String> sourceIdList);
}
