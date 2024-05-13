package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static void main( String[] args )
    {
        CrptApi api = new CrptApi(TimeUnit.SECONDS, 1);

        String json = "";
        try
        {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/data.json")), StandardCharsets.UTF_8);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        Gson gson = new Gson();
        CrptApi.CrptDocument document = gson.fromJson(json, CrptApi.CrptDocument.class);

        String signature = "signature";

        try {
            api.createDocument(document, signature);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        api.shutdown();
    }
}
