/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend.Model;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class Worker {
    int workerNumber;

    public Worker(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }
}
