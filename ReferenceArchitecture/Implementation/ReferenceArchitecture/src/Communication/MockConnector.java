/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class MockConnector implements DataConnector {
    
    /**
     *
     * @return
     */
    public static String GetNextOrder(String testData) {
        return (testData + " <- has been received");
    }
}
