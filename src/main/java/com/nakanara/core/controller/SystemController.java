package com.nakanara.core.controller;


import com.nakanara.core.annotation.ApiDescr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class SystemController {

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    public void setRequestMappingHandlerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @RequestMapping("/endpoint")
    @ApiDescr(descr = "EndPoint 출력")
    public List getEndPoint(){

        List<Map<String, Object>> pages = new ArrayList<>();
        List<Map<String, String>> result = new ArrayList<>();

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        map.forEach((key, value) -> log.info("{} {}", key, value));

        for (Map.Entry<RequestMappingInfo, HandlerMethod> elem : map.entrySet()) {

            RequestMappingInfo key = elem.getKey();

            HandlerMethod method = elem.getValue();

            ApiDescr apiDescr = method.getMethod().getAnnotation(ApiDescr.class);



            Map<String, Object> item = new HashMap<>();

            item.put("path", key.toString());
            item.put("cls", method.getMethod().getDeclaringClass().getSimpleName());
            item.put("method", method.getMethod().getName());

            StringBuffer sb = new StringBuffer();
            for (MethodParameter param : method.getMethodParameters()) {
                sb.append(param.getParameterType().getSimpleName()).append(", ");
            }

            if (sb.toString().length() > 0) {
                item.put("param", sb.toString().substring(0, sb.toString().length() - 2));
            } else {
                item.put("param", "");
            }

            if(StringUtils.hasLength(method.getMethod().getReturnType().getSimpleName())) {
                item.put("rtn", method.getMethod().getReturnType().getSimpleName());
            }

            if(apiDescr != null) {
                log.info("{}", apiDescr.descr());
                item.put("descr", apiDescr.descr());
            }

            pages.add(item);

        }



        return pages;
    }
}
