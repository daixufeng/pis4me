using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernateTest.App.Model;
using NHibernate;

namespace NHibernateTest.App.DAL
{
    public class DepartmentDAL : AbstractDAL
    {
        public Department Save(Department department)
        {
            using (ISession session = SessionFactory.OpenSession())
            {
                session.Save(department);
            }
            return department;
        }

        public Department GetById(int id)
        {
            Department department = new Department();
            using (ISession session = SessionFactory.OpenSession())
            {
                department = session.Get<Department>(id);
            }
            return department;
        }

        public Department Update(Department department)
        {
            using (ISession session = SessionFactory.OpenSession())
            {
                session.SaveOrUpdate(department);
            }
            return department;
        }
    }
}
