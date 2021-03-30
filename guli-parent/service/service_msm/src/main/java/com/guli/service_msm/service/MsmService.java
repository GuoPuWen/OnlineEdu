package com.guli.service_msm.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface MsmService {

    boolean send(String phone, Map<String, String> param);
}
