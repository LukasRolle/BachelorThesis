using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RestService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IRestService
    {
        
        [OperationContract]
        [WebGet(UriTemplate = "Order/?id={id}")]
        void GetOrder(int id);
        
        [OperationContract]
        [WebGet(UriTemplate = "NextOrder/?id={id}")]
        void GetNextOrder(int id);

        [OperationContract]
        [WebInvoke(Method = "PATCH", UriTemplate = "ResetDatabase")]
        void ResetDatabase();

        [OperationContract]
        [WebInvoke(Method = "PUT", UriTemplate = "ConfirmOrderLine/?orderId={orderId}&orderLineId={orderLineId}")]
        void ConfirmOrderLine(int orderId, int orderLineId);

        [OperationContract]
        [WebInvoke(Method = "PUT", UriTemplate = "ConfirmOrder/?orderId={id}")]
        void ConfirmOrder(int id);

    }
}
