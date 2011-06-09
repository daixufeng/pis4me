using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernateTest.App.Model;
using NHibernate;

namespace NHibernateTest.App.DAL
{
    public class EmployeeDAL:AbstractDAL
    {
        public Employee Save(Employee employee)
        {
            using (ISession session = SessionFactory.OpenSession()) {
                session.Save(employee);
            }
            return employee;
        }

        public Employee GetById(int id)
        {
            Employee employee = null;
            using (ISession session = SessionFactory.OpenSession())
            {
               employee = session.Get<Employee>(id);
            }
            return employee;
        }
    }
}
