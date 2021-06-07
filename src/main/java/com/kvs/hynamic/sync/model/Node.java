package com.kvs.hynamic.sync.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    int id;
    String syncEndpointUrl;

    public Node(int id, String syncEndpointUrl) {
        setId(id);
        setSyncEndpointUrl(syncEndpointUrl);
    }
}
