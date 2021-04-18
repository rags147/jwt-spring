package io.javaprojects.jwtspring.model;

public class AppAuthRes {
 
    private String jwtToken;

    
    public AppAuthRes(String jwtToken) {
        this.jwtToken = jwtToken;
    }


    public String getJwtToken() {
        return jwtToken;
    }
    
}
