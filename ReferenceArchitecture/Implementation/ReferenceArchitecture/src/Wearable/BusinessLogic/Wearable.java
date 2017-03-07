/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.BusinessLogic;

import Wearable.Interfaces.InputInterface;
import Wearable.Interfaces.InputText;
import Wearable.Interfaces.OutputInterface;
import Wearable.Interfaces.OutputRest;
import Wearable.Interfaces.OutputText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class Wearable {
    List<InputInterface> inputInterfaces;
    List<OutputInterface> outputInterfaces;
    OutputRest networkInterface;
    
    List<Process> processes;

    public Wearable() {
        inputInterfaces = new ArrayList<>();
        outputInterfaces = new ArrayList<>();
        processes = new ArrayList<>();
        
        OutputText outputText = new OutputText();
        outputInterfaces.add(outputText);
        
        InputText inputText = new InputText();
        inputInterfaces.add(inputText);
        
        LoginProcess loginProcess = new LoginProcess(outputText,inputText);
        processes.add(loginProcess);
        
        processes.add(new IdleProcess());
        
        outputInterfaces.add(new OutputRest(outputText));
        
        initialize();
    }
    
    private void initialize() {
        processes.get(0).run();
        outputInterfaces.get(1).outputData("New Order and things");
        processes.get(1).run();
    }
}
