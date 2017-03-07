/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.BusinessLogic;

import Wearable.Interfaces.InputText;
import Wearable.Interfaces.OutputText;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class LoginProcess implements Process {

    private final OutputText output;
    private final InputText input;

    public LoginProcess(OutputText output, InputText input) {
        this.output = output;
        this.input = input;
    }
    
    
    
    @Override
    public void run() {
        output.outputData("Validate your identity, input your name here:");
        String inputData;
        inputData = input.inputData();
        output.outputData(inputData + " is your name.");
    }
    
}
