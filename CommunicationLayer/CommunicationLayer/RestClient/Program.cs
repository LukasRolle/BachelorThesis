using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

namespace RestClient
{
    public class Class1
    {
        private const string URL = "http://localhost:53268/RestService.svc/NextOrder/";
        private const string urlParameters = "?id=1";

        static void Main(string[] args)
        {
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(URL);

            // Add an Accept header for JSON format.
            client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));

            // List data response.
            HttpResponseMessage response = client.GetAsync(urlParameters).Result;  // Blocking call!
            if (response.IsSuccessStatusCode)
            {
                // Parse the response body. Blocking!
                Console.WriteLine(response.Content.ReadAsStringAsync().Result);
                Console.ReadLine();
                //var dataObjects = response.Content.ReadAsAsync<IEnumerable<Order>>().Result;
                //foreach (var d in dataObjects)
                //{
                //    Console.WriteLine("{0}", d.OrderNumber);
                //}
            }
            else
            {
                Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
            }
        }
    }
}