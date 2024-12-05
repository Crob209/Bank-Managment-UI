package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

public class BankComm {
    private static String baseUri = "http://localhost:8000/";

    // Sign-in method
    public static Boolean Signin(String email, String password) {
        try {
            URL uri = new URL(baseUri + "signin");
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

           
            OutputStream outStream = connection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            outStreamWriter.write(new SigninRequest(email, password).toString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();

            
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            
            SigninResponse loginResponse = new SigninResponse();
            loginResponse.Parse(new InputStreamReader(connection.getInputStream()));
            System.out.println(loginResponse.toString());

            return loginResponse.signinSuccess;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static Boolean Signup(String name, int age, String email, String phoneNumber, String password) {
        try {
            URL uri = new URL(baseUri + "signup");
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            
            OutputStream outStream = connection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            outStreamWriter.write(new SignupRequest(name, age, email, phoneNumber, password).toString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();

            
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            
            SignupResponse signupResponse = new SignupResponse();
            signupResponse.Parse(new InputStreamReader(connection.getInputStream()));
            System.out.println(signupResponse.toString());

            return signupResponse.signupSuccess;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static class SigninRequest {
        public String email;
        public String password;

        public SigninRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

    public static class SigninResponse {
        public boolean signinSuccess;
        public String errorMessage;
        public SigninRequest originalRequest;

        public void Parse(InputStreamReader stream) {
            Gson gson = new Gson();
            try (BufferedReader bufferedReader = new BufferedReader(stream)) {
                SigninResponse json = gson.fromJson(bufferedReader, SigninResponse.class);
                this.signinSuccess = json.signinSuccess;
                this.errorMessage = json.errorMessage;
                this.originalRequest = json.originalRequest;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

    
    public static class SignupRequest {
        public String name;
        public int age;
        public String email;
        public String phoneNumber;
        public String password;

        public SignupRequest(String name, int age, String email, String phoneNumber, String password) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.password = password;
        }

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

    public static class SignupResponse {
        public boolean signupSuccess;
        public String message;
        public UserInfo userInfo;

        public void Parse(InputStreamReader stream) {
            Gson gson = new Gson();
            try (BufferedReader bufferedReader = new BufferedReader(stream)) {
                SignupResponse json = gson.fromJson(bufferedReader, SignupResponse.class);
                this.signupSuccess = json.signupSuccess;
                this.message = json.message;
                this.userInfo = json.userInfo;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

    
    public static class UserInfo {
        public String name;
        public int age;
        public String email;
        public String phoneNumber;

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }
}
