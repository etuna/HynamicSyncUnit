package com.kvs.hynamic.sync.rest;


import com.kvs.hynamic.sync.model.Value;
import com.kvs.hynamic.sync.service.StorageService;
import org.apache.coyote.Response;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Hashtable;

@RestController
@RequestMapping("api")
public class Controller {

    @Autowired
    StorageService storageService;


    private static org.jboss.logging.Logger logger = LoggerFactory.logger(Controller.class);

    @GetMapping("test")
    public String test(){
        return "test successful";
    }

    @GetMapping("fetch")
    public Hashtable<String, Value>  fetch() throws FileNotFoundException {
        logger.info(String.format("New FETCH request arrived."));
        return storageService.fetchAsObj();
    }

    @GetMapping("syncfetch")
    public ResponseEntity<Resource> syncFetch() throws MalformedURLException {
        logger.info(String.format("New SYNC-FETCH request arrived."));
        Resource file = storageService.syncFetch();
        return ResponseEntity.ok().body(file);

    }


    @PostMapping(path="synccommit", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public String syncCommit(@RequestBody byte[] file) throws IOException {
        logger.info(String.format("New SYNC-COMMIT request arrived."));
        storageService.syncCommit(file);
        return "OK";

    }

    @GetMapping("commit")
    public String commit(@RequestParam String content) throws IOException {
        logger.info(String.format("New COMMIT request arrived."));
        return storageService.commit(content);
    }
}
