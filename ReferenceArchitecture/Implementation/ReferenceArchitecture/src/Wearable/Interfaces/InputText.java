/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class InputText implements InputInterface<String>{

    @Override
    public String inputData() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = "";
        try {
            result = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(InputText.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
