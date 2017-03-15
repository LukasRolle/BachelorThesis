// C# , ADO.NET  
using System;
using QC = System.Data.SqlClient;  // System.Data.dll  

namespace ProofOfConcept_SQL_CSharp
{
    public class Program
    {
        static public void Main()
        {
            using (var connection = new QC.SqlConnection(
                "Server=tcp:logweardemo.database.windows.net,1433;Initial Catalog=MockWMS;Persist Security Info=False;User ID=logweardemo;Password=logwearAdmin1;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;"
                ))
            {
                connection.Open();
                Console.WriteLine("Connected successfully.");

                Console.WriteLine("Press any key to finish...");
                Console.ReadKey(true);
            }
        }
    }
}
/**** Actual output:  
Connected successfully.  
Press any key to finish...  
****/
