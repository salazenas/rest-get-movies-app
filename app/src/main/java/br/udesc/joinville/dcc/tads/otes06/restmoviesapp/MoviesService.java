package br.udesc.joinville.dcc.tads.otes06.restmoviesapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MoviesService{

    private String baseUrl;
    private String repositoryName;
    private String fullUrl;
    private URL url;
    private HttpURLConnection connection;

    public MoviesService(){
//        this.baseUrl = "http://10.0.2.2:8080";
         this.baseUrl = "http://192.168.1.200:8080";
        this.repositoryName = "movies";
        this.fullUrl = this.baseUrl+"/"+this.repositoryName;
    }

    public List<Movie> getAll(){

        StringBuffer content = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.url = new URL(this.fullUrl);
            this.connection = (HttpURLConnection) this.url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Movie> movies = null;
        try {
            movies = mapper.readValue(content.toString(), new TypeReference<List<Movie>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }


        return movies;

    }

    public Movie getById(String id){

        String url = this.baseUrl+"/"+this.repositoryName+"/"+id;

        System.out.println(url);

        StringBuffer content = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.url = new URL(url);
            this.connection = (HttpURLConnection) this.url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Movie movie = null;
        try {
            movie = mapper.readValue(content.toString(), new TypeReference<Movie>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(movie);

        return movie;

    }


}