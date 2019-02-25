package com.sunny.gitpulls.utils;

public class EndpointDetails {
    private static String owner="facebook";
    private static String repo="react";
    private static final String apiRoot = "https://api.github.com/repos/";

    public static String getRequestEndpoint(){
        return apiRoot+owner+"/"+repo+"/"+"pulls";
    }

    public static String getOwner() {
        return owner;
    }

    public static void setOwner(String owner) {
        EndpointDetails.owner = owner;
    }

    public static String getRepo() {
        return repo;
    }

    public static void setRepo(String repo) {
        EndpointDetails.repo = repo;
    }
}
