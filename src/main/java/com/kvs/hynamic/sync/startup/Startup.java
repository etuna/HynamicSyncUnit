package com.kvs.hynamic.sync.startup;

import com.kvs.hynamic.sync.service.StorageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class Startup implements InitializingBean {

    @Autowired
    public StorageService storageService;

    @Override
    public void afterPropertiesSet() throws Exception {
       // storageService.start();
    }
}
