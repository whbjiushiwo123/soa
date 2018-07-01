package com.whb.spring.configBean;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseConfigBean implements Serializable {
    private static  final  long seriaVersionUID = 1234234234234L;
    private String id;
}
