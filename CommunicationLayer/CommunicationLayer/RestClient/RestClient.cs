using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading;
using System.Web;
using System.Web.Script.Serialization;

namespace RestClient
{
    public class RestClient
    {
        private const string URL = "http://localhost:53268/RestService.svc/";

        static void Main(string[] args)
        {
            var client = new RestClient();
            

            for (int j = 0; j < 1000; j++)
            {

                client.ResetDatabase();
                for (int i = 1; i < 5; i++)
                {
                    var order = client.GetNextOrder(i);
                    Console.WriteLine("Order number for next order worker number "
                        + i + ": " + order.OrderNumber);
                    order = client.GetOrder(i);
                    Console.WriteLine("Order number for order "
                        + i + ": " + order.OrderNumber);
                    Console.WriteLine("Confirmed: " + order.OrderPacked);
                    foreach (var orderLine in order.OrderLines)
                    {
                        client.ConfirmOrderLine(order.OrderNumber, orderLine.OrderLineNumber);
                    }
                    client.ConfirmOrder(i);
                    order = client.GetOrder(i);
                    Console.WriteLine("Confirmed: " + order.OrderPacked);
                }

            }
            Console.ReadKey();
        }

        public Order GetNextOrder(int workerId)
        {
            var response = GetResponse("NextOrder/?id=" + workerId, HttpMethod.Get);
            return DeserializeJson<Order>(response);
        }
        public Order GetOrder(int orderId)
        {
            var response = GetResponse("Order/?id=" + orderId, HttpMethod.Get);
            return DeserializeJson<Order>(response);
        }

        public void ConfirmOrderLine(int orderId, int orderLineId)
        {
            GetResponse("ConfirmOrderLine/?orderId=" + orderId +
                "&orderLineId=" + orderLineId, HttpMethod.Put);
        }

        public void ConfirmOrder(int orderId)
        {
            GetResponse("ConfirmOrder/?orderId=" + orderId, HttpMethod.Put);
        }

        public void ResetDatabase()
        {
            GetResponse("ResetDatabase", new HttpMethod("PATCH"));
        }

        private HttpResponseMessage GetResponse(string urlParameters, HttpMethod method)
        {
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(URL);

            // Add an Accept header for JSON format.
            client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));
            var request = new HttpRequestMessage(method, URL + urlParameters);
            // List data response.
            HttpResponseMessage response = client.SendAsync(request).Result;  // Blocking call!
            if (response.IsSuccessStatusCode)
            {
                return response;
            }
            else
            {
                throw new HttpException("Invalid Status Code");
            }
        }

        private T DeserializeJson<T>(HttpResponseMessage response)
        {
            T instance = new JavaScriptSerializer().Deserialize<T>(response.Content.ReadAsStringAsync().Result);
            return instance;
        }
    }
}