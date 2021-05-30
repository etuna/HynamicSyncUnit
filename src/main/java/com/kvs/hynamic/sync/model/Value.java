package com.kvs.hynamic.sync.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {
    private String val;
    private int reqCount;

    public Value(String val, int i) {
        setVal(val);
        setReqCount(i);
    }
}
