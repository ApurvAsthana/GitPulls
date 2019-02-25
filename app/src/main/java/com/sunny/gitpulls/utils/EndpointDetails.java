package com.sunny.gitpulls.utils;

public class EndpointDetails {
    private static String owner="facebook";
    private static String repo="react";
    private static final String apiRoot = "https://api.github.com/repos/";

    public static String getRequestEndpoint(){
        return apiRoot+owner+"/"+repo+"/"+"pulls";
    }
}
