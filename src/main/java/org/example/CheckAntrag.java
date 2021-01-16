package org.example;



import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static org.camunda.spin.Spin.JSON;

public class CheckAntrag implements JavaDelegate {



        @Override
        public void execute (DelegateExecution delegateExecution) throws Exception {

            String surname = "Alford";
            String prename = "Colleen";

            try {

                URL url = new URL("http://localhost:3000/customers");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
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

                SpinJsonNode json = JSON(inline);
                SpinJsonNode customerProperty = json.prop("customers");
                SpinList customers = customerProperty.elements();

                //SpinJsonNode customer = customers.get(0);
                //String customerName = customer.stringValue();

                for (int i = 0; i < customers.size(); i++) {
                    if(customers.get(i))
                }

               /* for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                }*/


                //Close the scanner
                scanner.close();


                conn.disconnect();

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }}

