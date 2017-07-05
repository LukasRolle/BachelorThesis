using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DT = System.Data;            // System.Data.dll  
using QC = System.Data.SqlClient;  // System.Data.dll  
using CS = System.Configuration;
using System.Data.Linq;

namespace DBC
{
    public class DatabaseSetupHelper
    {
        public DatabaseSetupHelper ()
        {

        }
        /// <summary>
        /// This methods resets the database to a state that is was before the demo was run and the state 
        /// of the orders and order lines has been changed.
        /// </summary>
        public void ResetDatabase()
        {
            using (var connection = new QC.SqlConnection(CS.ConfigurationManager.ConnectionStrings["connectionString"].ConnectionString))
            {
                Console.WriteLine("Resetting Database:");
                Console.WriteLine("Connecting to Database.");
                connection.Open();

                var context = new WMSEntities();

                Console.WriteLine("Deleting Old Entries.");
                context.Database.ExecuteSqlCommand(
                    "DELETE FROM OrderLines;" +
                    "DELETE FROM Pallets;" +
                    "DELETE FROM Articles;" +
                    "DELETE FROM OrderWorker;" +
                    "DELETE FROM Workers;" +
                    "DELETE FROM Orders;" +
                    "DELETE FROM Customers;");

                Console.WriteLine("Creating New Entities.");
                Article[] articles = {
                    CreateNewArticle(1, "HP L2245w"),
                    CreateNewArticle(2, "LG L194WT"),
                    CreateNewArticle(3, "Acer R240HY"),
                    CreateNewArticle(4, "HP Pavilion 22cwa"),
                    CreateNewArticle(5, "LG 32MA68HY-P")
                };
                
                Pallet[] pallets =
                {
                    CreateNewPallet(articles[0], 1, 15, "1A32"),
                    CreateNewPallet(articles[1], 2, 20, "1A31"),
                    CreateNewPallet(articles[2], 3, 18, "1B31"),
                    CreateNewPallet(articles[3], 4, 16, "1B32"),
                    CreateNewPallet(articles[4], 5, 22, "1A30")
                };

                OrderLine[] orderLines1 =
                {
                    CreateNewOrderLine(pallets[0], 1, 2),
                    CreateNewOrderLine(pallets[1], 2, 3),
                };
                OrderLine[] orderLines2 =
                {
                    CreateNewOrderLine(pallets[0], 1, 1),
                    CreateNewOrderLine(pallets[1], 2, 2),
                    CreateNewOrderLine(pallets[3], 3, 1),
                    CreateNewOrderLine(pallets[4], 4, 1),
                };
                OrderLine[] orderLines3 =
                {

                    CreateNewOrderLine(pallets[1], 1, 1),
                    CreateNewOrderLine(pallets[2], 2, 1),
                    CreateNewOrderLine(pallets[4], 3, 3),
                };
                OrderLine[] orderLines4 =
                {

                    CreateNewOrderLine(pallets[3], 1, 3),
                };
                OrderLine[] orderLines5 =
                {

                    CreateNewOrderLine(pallets[0], 1, 1),
                    CreateNewOrderLine(pallets[1], 2, 2),
                    CreateNewOrderLine(pallets[2], 3, 2),
                    CreateNewOrderLine(pallets[3], 4, 1),
                    CreateNewOrderLine(pallets[4], 5, 3),
                };

                Customer[] customers =
                {
                    CreateNewCustomer(1, "All the Bubble Wrap.", "Abbey Road 3"),
                    CreateNewCustomer(2, "Package in as small as possible packages.", "Great Ocean Road 13"),
                    CreateNewCustomer(3, "Put everything on a pallet without further packaging.", "Lombard Street 286"),
                };

                Worker[] workers1 = CreateNewWorkers(1, 2);
                Worker[] workers2 = CreateNewWorkers(3, 4);
                Worker[] workers3 =
                {
                    workers1[0],
                    workers2[1]
                };

                Order[] orders =
                {
                    CreateNewOrder(1, orderLines1, customers[0], workers1),
                    CreateNewOrder(2, orderLines2, customers[1], workers2),
                    CreateNewOrder(3, orderLines3, customers[2], workers3),
                    CreateNewOrder(4, orderLines4, customers[0], workers1),
                    CreateNewOrder(5, orderLines5, customers[1], workers2)
                };


                Console.WriteLine("Adding new Entities to database.");
                context.Orders.AddRange(orders);
                
                context.SaveChanges();

                connection.Close();
                Console.WriteLine("Done.");
            }

            
        }

        /// <summary>
        /// Helper method to create new orders more simply.
        /// </summary>
        /// <param name="orderNumber">A number that this order is supposed to have.</param>
        /// <param name="orderLines">The order lines that will be a part of this order.</param>
        /// <param name="customer">The customer that has initiated the order.</param>
        /// <param name="workers">The workers that are allocated for this order.</param>
        /// <returns>Return the newly created order.</returns>
        private Order CreateNewOrder(int orderNumber, OrderLine[] orderLines, Customer customer, Worker[] workers)
        {
            return new Order
            {
                OrderNumber = orderNumber,
                OrderLines = orderLines,
                Customer = customer,
                Workers = workers,
                OrderPacked = false,
            };
        }

        /// <summary>
        /// Helper method to create a new worker.
        /// </summary>
        /// <param name="workerNumber">The id of the worker being created.</param>
        /// <returns>Return the newly created worker.</returns>
        private Worker CreateNewWorker(int workerNumber)
        {
            return new Worker
            {
                WorkerNumber = workerNumber,
                Orders = new List<Order>(),
            };
        }

        /// <summary>
        /// Helper method to create a multitude of workers more easily.
        /// Workers are created using numbers and creates all workers between two number.
        /// </summary>
        /// <param name="startingNumber">The starting id of a worker to be created.</param>
        /// <param name="endNumber">The last worker that will be created with specified id.</param>
        /// <returns>Returns multiple workers.</returns>
        private Worker[] CreateNewWorkers(int startingNumber, int endNumber)
        {
            var workers = new Worker[endNumber - startingNumber + 1];
            for (var i = startingNumber; i <= endNumber; i++)
            {
                workers[i - startingNumber] = CreateNewWorker(i);
            }
            return workers;
        }
       
        /// <summary>
        /// Helper method to create a new customer.
        /// </summary>
        /// <param name="customerNumber">An id that is used to address this customer.</param>
        /// <param name="additionalWishes">Additional wishes that can be entered for a customer.</param>
        /// <param name="address">Adds an address for a customer.</param>
        /// <returns>Returns a newly created customer.</returns>
        private Customer CreateNewCustomer(int customerNumber, string additionalWishes, string address)
        {
            return new Customer
            {
                CustomerNumber = customerNumber,
                AdditionalWishes = additionalWishes,
                CustomerAddress = address,
                Orders = new List<Order>(),
            };
        }

        /// <summary>
        /// Helper method to create a new order line.
        /// </summary>
        /// <param name="pallet">A pallet where the article can be found that is needed for this order line.</param>
        /// <param name="orderLineNumber">The order line number for this order line.</param>
        /// <param name="quantity">The quantity needed of the article for this order line.</param>
        /// <returns>Returns a newly created order line.</returns>
        private OrderLine CreateNewOrderLine(Pallet pallet, int orderLineNumber, int quantity)
        {
            return new OrderLine
            {
                Pallet = pallet,
                OrderLineNumber = orderLineNumber,
                Quantity = quantity,
                Acknowledgement = false,
            };
        }

        /// <summary>
        /// Helper method to create a new pallet.
        /// </summary>
        /// <param name="article">The article that is stored on this pallet.</param>
        /// <param name="palletNumber">The unique identifier for this pallet.</param>
        /// <param name="quantity">The quantity that is still stored on this pallet.</param>
        /// <param name="storageLocation">The location where the pallet is placed in the warehouse.</param>
        /// <returns>Returns a newly created pallet.</returns>
        private Pallet CreateNewPallet(Article article, int palletNumber, int quantity, string storageLocation)
        {
            return new Pallet
            {
                Article = article,
                PalletNumber = palletNumber,
                Quantity = quantity,
                StorageLocation = storageLocation
            };
        }

        /// <summary>
        /// Helper method to create a new article.
        /// </summary>
        /// <param name="articleNumber">The id for this article.</param>
        /// <param name="articleName">The name of this article.</param>
        /// <returns>Returns a newly created article.</returns>
        private Article CreateNewArticle(int articleNumber, string articleName)
        {
            return new Article
            {
                ArticleNumber = articleNumber,
                ArticleName = articleName
            };
        }
    }
}
