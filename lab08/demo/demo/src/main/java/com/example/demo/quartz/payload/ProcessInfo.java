package com.example.demo.quartz.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessInfo {
    int ramUsed;
    int executionTime;
}