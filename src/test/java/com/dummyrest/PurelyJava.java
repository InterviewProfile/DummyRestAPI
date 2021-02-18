package com.dummyrest;

import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;


public class PurelyJava {

    private static HttpURLConnection connection;

    public static void getOneEmployee (){

        BufferedReader reader;
        String line;
        StringBuffer payload = new StringBuffer();


        try {
            URL url = new URL("http://dummy.restapiexample.com/api/v1/employee/1");
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();

            if(statusCode != 200){

                System.out.println("Error!");
                return;

            }else{

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    payload.append(line);
                }

                reader.close();

            }

            System.out.println("Status Code: " + statusCode);
            oneEmployeeResponsePayload(payload.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
    }

    public static String oneEmployeeResponsePayload (String responsePayload) {

        JSONObject payload = new JSONObject(responsePayload);

        String status = payload.getString("status");

        Object data = payload.getJSONObject("data");
        String[] dataArray = data.toString().split(",");

        String employee_name = dataArray[1];
                employee_name =  employee_name.replace(":", "").replace("\"employee_name\"", "");
        String employee_salary= dataArray[2];
                employee_salary =  employee_salary.replace(":", "").replace("\"employee_salary\"", "");
        String employee_age= dataArray[dataArray.length-1];
                employee_age =  employee_age.replace(":", "").replace("\"employee_age\"", "").replace("}","");
        System.out.println(payload);
        System.out.println("Employee relevant Information: " + "\n"+
                            "Name: " + employee_name + "\n"+
                            "Age: " + employee_age + "\n"+
                            "Salary: $" + employee_salary);
        return null;
    }







    public static void main(String[] args) {

        getOneEmployee();

    }

}