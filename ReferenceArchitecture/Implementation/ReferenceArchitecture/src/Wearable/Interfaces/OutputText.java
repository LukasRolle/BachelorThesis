/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.Interfaces;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class OutputText implements OutputInterface<String> {

    @Override
    public void outputData(String data) {
        System.out.println(data);
    }
    
}
