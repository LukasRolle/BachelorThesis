/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nl.fontys.logwear.demofrontend.Model.Order;
import nl.fontys.logwear.demofrontend.Model.Order.OrderLine;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
@SessionScoped
@Named("WorkerBean")
public class WorkerBean implements Serializable {

    private int workerID;
    private Order currentOrder;
    private URL baseUrl;

    /**
     * Creates a new instance of WorkerBean
     */
    public WorkerBean() {
        System.out.println(this + "created");
        try {
            baseUrl = new URL("http://wms-logwear.azurewebsites.net/RestService.svc/");
        } catch (MalformedURLException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getWorkerID() {

        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public Order getCurrentOrder() {
        HttpURLConnection connection = null;
        URL restUrl;
        String orderJson = "";
        try {
            restUrl = new URL(baseUrl.toString() + "NextOrder/?id=" + workerID);

            connection = (HttpURLConnection) restUrl.openConnection();
            connection.setRequestMethod("GET");

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            orderJson = response.toString();
            orderJson = jsonToCamelCase(orderJson);
            orderJson = "{\"Order\":" + orderJson + "}";
        } catch (JsonMappingException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        if (!orderJson.equals("")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

            try {
                currentOrder = mapper.readValue(orderJson, Order.class);
            } catch (JsonMappingException ex) {
                Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentOrder;
    }

    public Order getStoredOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getCurrentOrderLines() {
        String orderLines = "";
        for (Order.OrderLine orderLine : currentOrder.getOrderLines()) {
            orderLines += getOrderLineString(orderLine);
        }
        return orderLines;
    }

    public String getNextOrderLine() {
        for (Order.OrderLine orderLine : currentOrder.getOrderLines()) {
            if (!orderLine.isAcknowledgement()) {
                return getOrderLineString(orderLine);
            }
        }
        return "";
    }

    public String getOrderLineString(OrderLine orderLine) {
        String orderLineString = "";
        orderLineString += "Article: " + orderLine.getPallet().getArticle().getArticleName();
        orderLineString += "Quantitiy: " + orderLine.getQuantity();
        orderLineString += "Location: " + orderLine.getPallet().getStorageLocation();
        orderLineString += " <input type=\"checkbox\" name=\"the_checkbox\" value=\""
                + (orderLine.isAcknowledgement() ? "1" : "0") + "\" /> Acknowledgement \n";
        return orderLineString;
    }

    public void ConfirmOrder() {
        HttpURLConnection connection = null;
        URL restUrl;
        try {
            restUrl = new URL(baseUrl.toString() + "ConfirmOrder/?orderId=" + currentOrder.getOrderNumber());
            connection = (HttpURLConnection) restUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("Resource content");
            out.close();
            connection.getInputStream();
        } catch (MalformedURLException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        currentOrder.setOrderPacked(true);
    }

    public void ConfirmOrderLine() {
        HttpURLConnection connection = null;
        URL restUrl;
        try {
            restUrl = new URL(baseUrl.toString() + "ConfirmOrderLine/?orderId="
                    + currentOrder.getOrderNumber() + "&orderLineId=" + getNextOrderLineNumber());
            connection = (HttpURLConnection) restUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("Resource content");
            out.close();
            connection.getInputStream();
        } catch (MalformedURLException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        for (Order.OrderLine orderLine : currentOrder.getOrderLines()) {
            if (!orderLine.isAcknowledgement()) {
                orderLine.setAcknowledgement(true);
                break;
            }
        }
    }

    public void resetDataBase() {
        HttpURLConnection connection = null;
        URL restUrl;
        try {
            restUrl = new URL(baseUrl.toString() + "ResetDatabase");
            connection = (HttpURLConnection) restUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.connect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public int getNextOrderLineNumber() {
        for (Order.OrderLine orderLine : currentOrder.getOrderLines()) {
            if (!orderLine.isAcknowledgement()) {
                return orderLine.getOrderLineNumber();
            }
        }
        return 0;
    }

    public String jsonToCamelCase(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        for (int i = 0; i < (input.length() - 1); i++) {
            char c = input.charAt(i);
            if (c == '"') {
                char nextChar = input.charAt(i + 1);
                if (nextChar >= 65 && nextChar <= 90) {
                    stringBuilder.setCharAt(i + 1, (char) (nextChar + 32));
                }
            }
        }
        return stringBuilder.toString();
    }

}
