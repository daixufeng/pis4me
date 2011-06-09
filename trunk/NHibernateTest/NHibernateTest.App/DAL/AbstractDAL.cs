using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernate.Cfg;
using NHibernate;

namespace NHibernateTest.App.DAL
{
    public abstract class AbstractDAL
    {
        public ISessionFactory SessionFactory
        {
            get
            {
                return new Configuration().Configure().BuildSessionFactory();
            }
        }
    }
}
