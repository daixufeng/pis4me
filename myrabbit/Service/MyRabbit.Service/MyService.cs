using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Objects.DataClasses;

namespace MyRabbit.Service
{
    public abstract class MyService<T> : IDisposable where T : EntityObject
    {
        public abstract T GetById(Int32 id);

        public T Create(T entity)
        {
            using (Entity.Entities context = new Entity.Entities())
            {
                String entitySetName = typeof(T).Name;
                context.AddObject(entitySetName, entity);
                context.SaveChanges();
            }
            return entity;
        }

        public T Update(T entity)
        {
            using (Entity.Entities context = new Entity.Entities())
            {
                String entitySetName = typeof(T).Name;
                context.AttachTo(entitySetName, entity);
                context.SaveChanges();
            }
            return entity;
        }

        public void Delete(IList<T> entities)
        {
            using (Entity.Entities context = new Entity.Entities())
            {
                foreach (var o in entities)
                    context.Detach(o);
                context.SaveChanges();
            }
        }

        public void Delete(T entity)
        {
            using (Entity.Entities context = new Entity.Entities())
            {
                context.Detach(entity);
                context.SaveChanges();
            }
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }
    }
}
