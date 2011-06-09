using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernateTest.App.DAL;
using NHibernateTest.App.Model;

namespace NHibernate.App.Test.DAL
{
    public class DepartmentDALTest:AbstractSupport
    {
        public void Save()
        {
            Department department = new Department()
            {
                CreateDate = DateTime.Now,
                Name = "DIT",
                Manager = new Employee() { Id = 1 },
                Parent = new Department() { Id = 1 }
            };
            DepartmentDAL dal = new DepartmentDAL();
            dal.Save(department);            
        }

        public void GetById()
        {
            DepartmentDAL dal = new DepartmentDAL();
            Department department = dal.GetById(1);
            log.Debug(department);
        }

        public void Update()
        {
            DepartmentDAL dal = new DepartmentDAL();
            Department department = dal.GetById(1);

            department.Name = "DIT .NET";
            dal.Update(department);
            //department = dal.GetById(1);
        }
    }
}
