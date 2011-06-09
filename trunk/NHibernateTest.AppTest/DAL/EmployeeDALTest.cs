using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernateTest.App.Model;
using NHibernateTest.App.DAL;

namespace NHibernate.App.Test.DAL
{
    public class EmployeeDALTest
    {
        public void Save()
        {
            Employee employee = new Employee()
            {
                Email = "xufeng.dai@hp.com",
                Name = "Dai Xu-feng",
                NickName = "Xufeng"
            };
            EmployeeDAL dal = new EmployeeDAL();
            dal.Save(employee);
        }

        public void GetById()
        {
            EmployeeDAL dal = new EmployeeDAL();
            Employee employee = dal.GetById(2);            
        }
    }
}
