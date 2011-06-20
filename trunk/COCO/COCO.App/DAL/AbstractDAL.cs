using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernate;
using NHibernate.Cfg;

namespace COCO.App.DAL
{
    public abstract class AbstractDAL<T>
    {
        public readonly log4net.ILog log = log4net.LogManager.GetLogger(typeof(AbstractDAL<T>));

        protected ISessionFactory SessionFactory
        {
            get
            {
                return new Configuration().Configure().BuildSessionFactory();
            }
        }

        public T GetById(object id)
        {
            T entity;
            using (ISession session = SessionFactory.OpenSession())
            {
              entity = session.Get<T>(id);
            }
            return entity;
        }

        public IList<T> GetAll()
        {
            IList<T> entities;
            using (ISession session = SessionFactory.OpenSession())
            {
                entities = session.CreateCriteria(typeof(T)).List<T>();
            }
            return entities;
        }

        public T Save(T entity)
        {
            using (ISession session = SessionFactory.OpenSession())
            {
                session.Save(entity);
            }
            return entity;
        }

        public T Update(T entity)
        {
            using (ISession session = SessionFactory.OpenSession())
            {
                session.Update(entity);
            }
            return entity;
        }

        public T SaveOrUpdate(T entity)
        {
            using (ISession session = SessionFactory.OpenSession())
            {
                session.SaveOrUpdate(entity);
                session.Flush();
            }
            return entity;
        }

        public T Delete(T entity)
        {
            using (ISession session = SessionFactory.OpenSession())
            {
                session.Delete(entity);
                session.Flush();
            }
            return entity;
        }
    }
}
