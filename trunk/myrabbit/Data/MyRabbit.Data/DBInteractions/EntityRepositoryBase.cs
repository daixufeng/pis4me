using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;

namespace MyRabbit.Entity.DBInteractions
{
    public abstract class EntityRepositoryBase<T> where T : class
    {
        private MyRabbitContext _dataContext;
        private readonly IDbSet<T> dbSet;
        protected EntityRepositoryBase(IDBFactory databaseFactory)
        {
            DataBaseFactory = databaseFactory;
            dbSet = DataContext.Set<T>();
        }
        protected IDBFactory DataBaseFactory
        {
            get;
            private set;
        }

        protected MyRabbitContext DataContext
        {
            get
            {
                return _dataContext ?? (_dataContext = DataBaseFactory.Get());
            }
        }

        public virtual void Create(T entity)
        {
            dbSet.Add(entity);
        }

        public virtual void Update(T entity)
        {
            _dataContext.Entry(entity).State = System.Data.EntityState.Modified;
        }

        public virtual void Delete(T entity)
        {
            dbSet.Remove(entity);
        }

        public virtual void Delete(Func<T, Boolean> where)
        {
            IList<T> objects = dbSet.Where<T>(where).ToList<T>();
            foreach (T o in objects)
                dbSet.Remove(o);
        }

        public virtual T GetById(long id)
        {
            return dbSet.Find(id);
        }

        public virtual List<T> GetAll()
        {
            return dbSet.ToList<T>();
        }

        public virtual T Get(Func<T, Boolean> where)
        {
            return dbSet.Where<T>(where).FirstOrDefault<T>();
        }

        public virtual List<T> GetMany(Func<T, Boolean> where)
        {
            return dbSet.Where<T>(where).ToList<T>();
        }

        public virtual PageData<T> GetPageData(Int32 pageNo, Int32 pageSize, Func<T, Boolean> where, Func<T, Object> sort)
        {
            List<T> records = dbSet.Where<T>(where)
                .Skip<T>((pageNo - 1) * pageSize)
                .Take<T>(pageSize)
                .OrderBy(sort)
                .ToList<T>();
            return new PageData<T>(records, dbSet.Where<T>(where).Count<T>(), pageNo, pageSize);
        }
    }
}
