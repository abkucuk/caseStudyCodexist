package com.ahmetburak.codexist.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Search {
    private String latitude;
    private String longitude;
    private String radius;
    private String data;
}
