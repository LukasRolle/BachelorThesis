/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class InputText implements InputInterface<String>{

    @Override
    public String InputData() {
        String input = System.console().readLine();
        return input;
    }
    
}
