package com.zain.robot.frontend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Objects;

public class CommonRestUtil {

    public static String parseJsonToString(Object jsonObject) throws JsonProcessingException {
        if (Objects.isNull(jsonObject)) return null;
        return new ObjectMapper().writeValueAsString(jsonObject);
    }

    public static HttpEntity<?> buildHttpEntityRequest(Object jsonObject, MediaType mediaType) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        String requestBody = parseJsonToString(jsonObject);
        return new HttpEntity<>(requestBody, httpHeaders);
    }

}
