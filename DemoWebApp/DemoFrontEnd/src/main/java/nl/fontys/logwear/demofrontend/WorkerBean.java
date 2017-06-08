/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
@SessionScoped
@Named("WorkerBean")
public class WorkerBean implements Serializable {

    private int workerID;
    private String currentOrder;
    private URL baseUrl;

    /**
     * Creates a new instance of WorkerBean
     */
    public WorkerBean() {
        System.out.println(this + "created");
        try {
            baseUrl = new URL("http://wms-logwear.azurewebsites.net/RestService.svc/NextOrder/?id=");
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

    public String getCurrentOrder() {
        HttpURLConnection connection = null;
        URL restUrl;
        try {
            restUrl = new URL(baseUrl.toString() + workerID);
       
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
            return response.toString();
        } catch (IOException ex) {
            Logger.getLogger(WorkerBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return currentOrder;
    }

    public void setCurrentOrder(String currentOrder) {
        this.currentOrder = currentOrder;
    }

}
