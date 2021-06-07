package com.kvs.hynamic.sync.service;

import com.kvs.hynamic.sync.model.Node;
import com.kvs.hynamic.sync.task.SyncTask;
import com.kvs.hynamic.sync.util.Constant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SyncService {

    private ArrayList<Node> nodes = null;

    public void start(){
        nodes = getNodes();
    }

    public void sync(){
        for(Node n : nodes){
            SyncTask syncTask = new SyncTask(n.getSyncEndpointUrl());
            Thread thread = new Thread(syncTask);
            thread.start();
        }

    }

    public ArrayList<Node> getNodes(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0, Constant.NODE0_SYNC_ENDPOINT_URL));
        nodes.add(new Node(1, Constant.NODE1_SYNC_ENDPOINT_URL));
        return nodes;
    }

}
