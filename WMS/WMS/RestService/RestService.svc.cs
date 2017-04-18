using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using DBC;
using System.Web.Script.Serialization;
using System.Web;

namespace RestService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class RestService : IRestService
    {
        public void GetOrder(int value)
        {
            var serializer = new JavaScriptSerializer();
            var order = Connector.GetOrder(value);
            HttpContext.Current.Response.ContentType = "text/HTML";
            HttpContext.Current.Response.Write(serializer.Serialize(order));
        }
        
        public void ResetDatabase()
        {
            var helper = new DatabaseSetupHelper();
            helper.ResetDatabase();
        }

        public void GetNextOrder(int id)
        {
            var serializer = new JavaScriptSerializer();
            HttpContext.Current.Response.ContentType = "text/HTML";
            HttpContext.Current.Response.Write(serializer.Serialize(Connector.GetNextOrder(id)));
        }

        public void ConfirmOrderLine(int orderId, int orderLineId)
        {
            Connector.ConfirmOrderLine(orderId, orderLineId);
        }

        public void ConfirmOrder(int id)
        {
            Connector.OrderSend(id);
        }
    }
}
