/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.BusinessLogic;

import Wearable.Interfaces.InputText;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class InterpretProcess implements Process{

    public InterpretProcess() {
    }

    @Override
    public void run() {
        InputText inputText = new InputText();
        String input = inputText.inputData();
        String inputLower = input.toLowerCase();
        switch (inputLower) {
            case "abc":
                System.out.println("Could be interpreted:" + input);
                break;
            case "end":
                System.out.println("Goodbye:");
                System.exit(0);
                break;
            default:
                System.out.println("Could not be interpreted:" + input);
                break;
        }  
    }
}
