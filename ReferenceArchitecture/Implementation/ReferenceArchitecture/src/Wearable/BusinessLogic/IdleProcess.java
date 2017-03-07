/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearable.BusinessLogic;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class IdleProcess implements Process {

    private InterpretProcess interpretProcess;

    public IdleProcess() {
        interpretProcess = new InterpretProcess();
    }

    @Override
    public void run() {
        while (true) {
            interpretProcess.run();
        }
    }

}
