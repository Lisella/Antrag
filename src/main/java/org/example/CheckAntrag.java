package org.example;



import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONException;
import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static org.camunda.spin.Spin.JSON;

public class CheckAntrag implements JavaDelegate{



        @Override
        public void execute (DelegateExecution delegateExecution) throws Exception {

            String reqSurname = delegateExecution.getVariable("prename").toString();
            String reqPrename = delegateExecution.getVariable("surname").toString();
            Boolean exists = false;

            try {

            // connect to json server and call the RestApi with db.json
            URL url = new URL("http://localhost:3000/customers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            // Scan the JSON output and write it in a String
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            //Using the JSON simple library parse the string into a json array
            JSONParser parse = new JSONParser();
            JSONArray array = (JSONArray) parse.parse(inline);



            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = null;
                try {
                    obj = (JSONObject) array.get(i);
                    if (obj.get("prename").equals(reqPrename) && obj.get("surname").equals(reqSurname)) {
                        exists = true;

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }


            scanner.close();


            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }

            delegateExecution.setVariable("Kunde existiert", exists);
    }





    }

