/**
 * 
 */
package com;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

/**
 * @author akshat
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String filename = "/tmp/Swagger/AstroGoShop-AGSS_CA_Widgets-1.json";
            JSONObject object = new JSONObject(new String(Files.readAllBytes(Paths.get(filename))));
            System.out.println(object.getJSONObject("definitions"));
        } catch (Exception e) {}
    }
}
