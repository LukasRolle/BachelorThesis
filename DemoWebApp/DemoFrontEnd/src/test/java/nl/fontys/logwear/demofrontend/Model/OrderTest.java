/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.logwear.demofrontend.Model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Rolle(LukasRolleSE@gmail.com)
 */
public class OrderTest {

    Order order;
    Order.OrderLine orderLine1;
    Order.OrderLine orderLine2;
    Order.OrderLine orderLine3;
    Order.OrderLine.Pallet pallet1;
    Order.OrderLine.Pallet pallet2;
    Order.OrderLine.Pallet pallet3;
    Order.OrderLine.Pallet.Article article1;
    Order.OrderLine.Pallet.Article article2;
    Order.OrderLine.Pallet.Article article3;
    Order.Worker worker1;
    Order.Worker worker2;
    Order.Worker worker3;
    
    public OrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        order = new Order();
        article1 = new Order.OrderLine.Pallet.Article(1, "article1");
        article2 = new Order.OrderLine.Pallet.Article(2, "article2");
        article3 = new Order.OrderLine.Pallet.Article(3, "article3");
        pallet1 = new Order.OrderLine.Pallet("location1", 1, article1, 16, 1);
        pallet2 = new Order.OrderLine.Pallet("location2", 2, article1, 10, 2);
        pallet3 = new Order.OrderLine.Pallet("location3", 3, article1, 13, 3);
        orderLine1 = new Order.OrderLine(1, 2, pallet1, false, 1, 1);
        orderLine2 = new Order.OrderLine(2, 3, pallet2, false, 1, 2);
        orderLine3 = new Order.OrderLine(3, 2, pallet3, false, 1, 3);
        worker1 = new Order.Worker(1);
        worker2 = new Order.Worker(2);
        worker3 = new Order.Worker(3);
    }

    @After
    public void tearDown() {
        order = null;
        article1 = null;
        article2 = null;
        article3 = null;
        pallet1 = null;
        pallet2 = null;
        pallet3 = null;
        orderLine1 = null;
        orderLine2 = null;
        orderLine3 = null;
        worker1 = null;
        worker2 = null;
        worker3 = null;
    }

    /**
     * Test of getCustomerNumber method, of class Order.
     */
    @org.junit.Test
    public void testGetCustomerNumber() {
        int expResult = 1;
        order.setCustomerNumber(1);
        int result = order.getCustomerNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCustomerNumber method, of class Order.
     */
    @org.junit.Test
    public void testSetCustomerNumber() {
        order.setCustomerNumber(1);
        int expResult = 1;
        int result = order.getCustomerNumber();
        assertEquals(expResult, result);
        
        order.setCustomerNumber(5);
        expResult = 5;
        result = order.getCustomerNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomer method, of class Order.
     */
    @org.junit.Test
    public void testGetCustomer() {
        Order.Customer expResult = new Order.Customer(1, "customerAddress", "additionalWishes");
        order.setCustomer(expResult);
        Order.Customer result = order.getCustomer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCustomer method, of class Order.
     */
    @org.junit.Test
    public void testSetCustomer() {
        Order.Customer expResult = new Order.Customer(1, "customerAddress", "additionalWishes");
        order.setCustomer(expResult);
        Order.Customer result = order.getCustomer();
        assertEquals(expResult, result);
        
        expResult = new Order.Customer(2, "otherAddress", "otherWishes");
        order.setCustomer(expResult);
        result = order.getCustomer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderLines method, of class Order.
     */
    @org.junit.Test
    public void testGetOrderLines() {
        List<Order.OrderLine> expResult = new ArrayList<>();
        expResult.add(orderLine1);
        expResult.add(orderLine2);
        expResult.add(orderLine3);
        order.setOrderLines(expResult);
        List<Order.OrderLine> result = order.getOrderLines();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderLines method, of class Order.
     */
    @org.junit.Test
    public void testSetOrderLines() {
        List<Order.OrderLine> expResult = new ArrayList<>();
        expResult.add(orderLine1);
        expResult.add(orderLine2);
        order.setOrderLines(expResult);
        List<Order.OrderLine> result = order.getOrderLines();
        assertEquals(expResult, result);
        
        expResult = new ArrayList<>();
        expResult.add(orderLine2);
        expResult.add(orderLine3);
        order.setOrderLines(expResult);
        result = order.getOrderLines();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorkers method, of class Order.
     */
    @org.junit.Test
    public void testGetWorkers() {
        List<Order.Worker> expResult = new ArrayList<>();
        expResult.add(worker1);
        expResult.add(worker2);
        expResult.add(worker3);
        order.setWorkers(expResult);
        List<Order.Worker> result = order.getWorkers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWorkers method, of class Order.
     */
    @org.junit.Test
    public void testSetWorkers() {
        List<Order.Worker> expResult = new ArrayList<>();
        expResult.add(worker1);
        expResult.add(worker2);
        order.setWorkers(expResult);
        List<Order.Worker> result = order.getWorkers();
        assertEquals(expResult, result);
        
        expResult = new ArrayList<>();
        expResult.add(worker2);
        expResult.add(worker3);
        order.setWorkers(expResult);
        result = order.getWorkers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderNumber method, of class Order.
     */
    @org.junit.Test
    public void testGetOrderNumber() {
        int expResult = 1;
        order.setOrderNumber(1);
        int result = order.getOrderNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderNumber method, of class Order.
     */
    @org.junit.Test
    public void testSetOrderNumber() {
        int expResult = 1;
        order.setOrderNumber(1);
        int result = order.getOrderNumber();
        assertEquals(expResult, result);
        
        expResult = 3;
        order.setOrderNumber(3);
        result = order.getOrderNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of isOrderPacked method, of class Order.
     */
    @org.junit.Test
    public void testIsOrderPacked() {
        boolean expResult = false;
        boolean result = order.isOrderPacked();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderPacked method, of class Order.
     */
    @org.junit.Test
    public void testSetOrderPacked() {
        boolean expResult = true;
        boolean result = order.isOrderPacked();
        assertFalse(result);
        order.setOrderPacked(true);
        result = order.isOrderPacked();
        assertEquals(expResult, result);
    }

}
