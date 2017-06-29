/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend;

import java.util.ArrayList;
import java.util.List;
import nl.fontys.logwear.demofrontend.Model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class WorkerBeanTest {

    WorkerBean workerBean;
    Order order1;
    Order order2;
    Order.OrderLine orderLine1;
    Order.OrderLine orderLine2;
    Order.OrderLine orderLine3;
    Order.OrderLine.Pallet pallet1;
    Order.OrderLine.Pallet pallet2;
    Order.OrderLine.Pallet pallet3;
    Order.OrderLine.Pallet.Article article1;
    Order.OrderLine.Pallet.Article article2;
    Order.OrderLine.Pallet.Article article3;
    List<Order.OrderLine> orderLines1 = new ArrayList<>();
    List<Order.OrderLine> orderLines2 = new ArrayList<>();
    
    
    public WorkerBeanTest() {
    }

    @Before
    public void setUp() {
        workerBean = new WorkerBean();
        workerBean.resetDataBase();
        workerBean.setWorkerID(1);
        
        order1 = new Order();
        order1.setOrderNumber(1);
        
        order2 = new Order();
        order2.setOrderNumber(1);
        
        article1 = new Order.OrderLine.Pallet.Article(1, "article1");
        article2 = new Order.OrderLine.Pallet.Article(2, "article2");
        article3 = new Order.OrderLine.Pallet.Article(3, "article3");
        pallet1 = new Order.OrderLine.Pallet("location1", 1, article1, 16, 1);
        pallet2 = new Order.OrderLine.Pallet("location2", 2, article1, 10, 2);
        pallet3 = new Order.OrderLine.Pallet("location3", 3, article1, 13, 3);
        orderLine1 = new Order.OrderLine(1, 2, pallet1, false, 1, 1);
        orderLine2 = new Order.OrderLine(2, 3, pallet2, false, 1, 2);
        orderLine3 = new Order.OrderLine(3, 2, pallet3, false, 1, 3);
        
        orderLines1.add(orderLine1);
        orderLines1.add(orderLine2);
        orderLines2.add(orderLine2);
        orderLines2.add(orderLine3);
        
        order1.setOrderLines(orderLines1);
        order2.setOrderLines(orderLines2);
    }

    @After
    public void tearDown() {
        workerBean = null;
        order1 = null;
        order2 = null;
        article1 = null;
        article2 = null;
        article3 = null;
        pallet1 = null;
        pallet2 = null;
        pallet3 = null;
        orderLine1 = null;
        orderLine2 = null;
        orderLine3 = null;
    }

    /**
     * Test of getWorkerID method, of class WorkerBean.
     */
    @Test
    public void testGetWorkerID() {
        int expResult = 1;
        int result = workerBean.getWorkerID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWorkerID method, of class WorkerBean.
     */
    @Test
    public void testSetWorkerID() {
        int expResult = 1;
        workerBean.setWorkerID(1);
        int result = workerBean.getWorkerID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentOrder method, of class WorkerBean.
     */
    @Test
    public void testGetCurrentOrder() {
        Order result = workerBean.getCurrentOrder();
        assertNotNull(result);
    }

    /**
     * Test of setCurrentOrder method, of class WorkerBean.
     */
    @Test
    public void testSetCurrentOrder() {
        workerBean.setCurrentOrder(order1);
        Order expResult = workerBean.getStoredOrder();
        assertEquals(expResult, order1);
    }

    /**
     * Test of getCurrentOrderLines method, of class WorkerBean.
     */
    @Test
    public void testGetCurrentOrderLines() {
        workerBean.setCurrentOrder(order1);
        
        String orderLineString = workerBean.getOrderLineString(orderLine1);
        orderLineString += workerBean.getOrderLineString(orderLine2);
                
        String result = workerBean.getCurrentOrderLines();
        assertEquals(orderLineString, result);
    }

    /**
     * Test of getNextOrderLine method, of class WorkerBean.
     */
    @Test
    public void testGetNextOrderLine() {
        workerBean.setCurrentOrder(order1);
        String orderLineString = workerBean.getOrderLineString(orderLine1);
        String result = workerBean.getNextOrderLine();
        assertEquals(orderLineString, result);
        
        orderLineString = workerBean.getOrderLineString(orderLine2);
        workerBean.ConfirmOrderLine();
        result = workerBean.getNextOrderLine();
        assertEquals(orderLineString, result);
    }

    /**
     * Test of getOrderLineString method, of class WorkerBean.
     */
    @Test
    public void testGetOrderLineString() {
        String orderLineString = "";
        orderLineString += "Article: " + orderLine1.getPallet().getArticle().getArticleName();
        orderLineString += "Quantitiy: " + orderLine1.getQuantity();
        orderLineString += "Location: " + orderLine1.getPallet().getStorageLocation();
        orderLineString += " <input type=\"checkbox\" name=\"the_checkbox\" value=\""
                + (orderLine1.isAcknowledgement() ? "1" : "0") + "\" /> Acknowledgement \n";
        String result = workerBean.getOrderLineString(orderLine1);
        assertEquals(orderLineString, result);
    }

    /**
     * Test of jsonToCamelCase method, of class WorkerBean.
     */
    @Test
    public void testJsonToCamelCase() {
        String expResult = "{\"order:{\"orderLine\" : 1}\"}";
        String result = workerBean.jsonToCamelCase("{\"Order:{\"OrderLine\" : 1}\"}");
        assertEquals(expResult, result);
    }

    /**
     * Test of ConfirmOrder method, of class WorkerBean.
     */
    @Test
    public void testConfirmOrder() {
        Order order = workerBean.getCurrentOrder();
        workerBean.ConfirmOrder();
        Order nextOrder = workerBean.getCurrentOrder();
        assertNotEquals(order, nextOrder);
        
        assertEquals(1, order.getOrderNumber());
        assertEquals(2, nextOrder.getOrderNumber());
    }

    /**
     * Test of ConfirmOrderLine method, of class WorkerBean.
     */
    @Test
    public void testConfirmOrderLine() {
        workerBean.setCurrentOrder(order1);
        workerBean.ConfirmOrderLine();
        String result = workerBean.getNextOrderLine();
        assertEquals(workerBean.getOrderLineString(orderLine2), result);
        
        workerBean.getCurrentOrder();
        assertEquals(2, workerBean.getNextOrderLineNumber());
    }

}
