package com.kvs.hynamic.sync.task;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SyncTask implements Runnable{
    String syncEndPointUrl="";

    public SyncTask(String syncEndPointUrl){
        this.syncEndPointUrl = syncEndPointUrl;
    }

    @SneakyThrows
    @Override
    public void run() {
        HttpClient httpClient = HttpClient.newHttpClient();
        Path file = Paths.get("maindbb.xml");
        Resource resource = new UrlResource(file.toUri());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(syncEndPointUrl))
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("maindbb.xml")))
                //.headers(HttpHeaders.CONTENT_TYPE, "multipart/form-data")
                .build();

        java.net.http.HttpResponse res = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
    }
}
