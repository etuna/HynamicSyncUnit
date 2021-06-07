package com.kvs.hynamic.sync.startup;

import com.kvs.hynamic.sync.service.StorageService;
import com.kvs.hynamic.sync.service.SyncService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Startup implements InitializingBean {

    @Autowired
    public StorageService storageService;

    @Autowired
    public SyncService syncService;

    @Override
    public void afterPropertiesSet() throws Exception {
        storageService.start();
        syncService.start();
    }
}
