/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.Interfaces;

import Communication.MockConnector;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class OutputRest implements OutputInterface<String> {

    OutputText outputText;

    public OutputRest(OutputText outputText) {
        this.outputText = outputText;
    }

    @Override
    public void outputData(String data) {
        String testData = MockConnector.GetNextOrder(data);
        outputText.outputData(testData);
    }

}
