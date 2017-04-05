﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using DBC;
using System.Web.Script.Serialization;


namespace RestService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class Service1 : IService1
    {
        public string GetOrder(int value)
        {
            var serializer = new JavaScriptSerializer();
            var order = Connector.GetOrder(value);
            return serializer.Serialize(order).ToString();
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }

        public void ResetDatabase()
        {
            DatabaseSetupHelper.ResetDatabase();
        }

        public string GetNextOrder(int id)
        {
            var serializer = new JavaScriptSerializer();
            return serializer.Serialize(Connector.GetNextOrder(id));
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