package com.kvs.hynamic.sync.service;

import com.kvs.hynamic.sync.model.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class StorageService {

    @Autowired
    SyncService syncService;

    private FileInputStream in = null;
    private ObjectInputStream ois = null;
    private FileOutputStream out = null;
    private ObjectOutputStream oos = null;

    public StorageService() throws IOException {
    }


    public String commit(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("maindbb.xml"));
        writer.write(content);
        writer.close();
        syncService.sync();
        return "OK";
    }


    public String fetch() throws FileNotFoundException {

        String content = "";
        File file = new File("maindbb.xml");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            content += sc.nextLine();
        }
        return content;
    }


    public Hashtable<String, Value> fetchAsObj() throws FileNotFoundException {
        Hashtable<String, Value> tmpHashTable = null;
        try {
            in = new FileInputStream("maindbb.xml");
            ois = new ObjectInputStream(in);
            tmpHashTable = (Hashtable) ois.readObject();
            in.close();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return tmpHashTable;
        }
    }

    public String sendFile(MultipartFile file) throws Exception {

        try {
            Path copyLocation = Paths.get("maindb.xml");
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }

    return "";
    }

    public Resource syncFetch() throws MalformedURLException {
        Path file = Paths.get("maindbb.xml");
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }

    public String syncCommit(byte[] file) throws IOException {
        OutputStream os = new FileOutputStream(new File("maindbb.xml"));
        os.write(file);
       // Files.copy(file, Path.of("testdb.xml"), StandardCopyOption.REPLACE_EXISTING);
        return "OK";
    }

    public String sendFile(String filename) throws MalformedURLException {
        return "";
    }

    public void start() {
    }
}
