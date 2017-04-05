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
        public static void ResetDatabase()
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
                    new Article
                    {
                        ArticleNumber = 1,
                        ArticleName = "HP L2245w"
                    },
                    new Article
                    {
                        ArticleNumber = 2,
                        ArticleName = "LG L194WT"
                    }
                };
                
                Pallet[] pallets =
                {
                    new Pallet
                    {
                        Article = articles[0],
                        PalletNumber = 1,
                        Quantity = 5,
                        StorageLocation = "1A32"
                    },
                    new Pallet
                    {
                        Article = articles[1],
                        PalletNumber = 2,
                        Quantity = 10,
                        StorageLocation = "1A31"
                    }
                };

                OrderLine[] orderLines =
                {
                    new OrderLine
                    {
                        Pallet = pallets[0],
                        OrderLineNumber = 1,
                        Quantity = 2
                    },
                    new OrderLine
                    {
                        Pallet = pallets[1],
                        OrderLineNumber = 2,
                        Quantity = 3
                    }
                };

                Order[] orders =
                {
                    new Order
                    {
                        OrderNumber = 1,
                        OrderLines = orderLines,
                        OrderPacked = false
                    }
                };

                Customer[] customers =
                {
                    new Customer
                    {
                        CustomerNumber = 1,
                        Orders = orders,
                        AdditionalWishes = "All the Bubble Wrap.",
                        CustomerAddress = "Abbey Road 3"
                    }
                };

                Worker[] workers =
                {
                    new Worker
                    {
                        WorkerNumber = 1,
                        Orders = orders
                    },
                    new Worker
                    {
                        WorkerNumber = 2,
                        Orders = orders,
                    }
                };

                orders[0].Customer = customers[0];
                orders[0].Workers = workers;


                Console.WriteLine("Adding new Entities to database.");
                context.Orders.AddRange(orders);
                
                context.SaveChanges();

                connection.Close();
                Console.WriteLine("Done.");
            }
        }
    }
}
