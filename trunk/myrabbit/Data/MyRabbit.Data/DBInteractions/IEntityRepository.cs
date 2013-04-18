using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyRabbit.Entity.DBInteractions
{
    public interface IEntityRepository<T> where T:class
    {
        void Create(T entity);
        void Update(T entity);
        void Delete(T entity);
        void Delete(Func<T, Boolean> predicate);
        T GetById(long id);
        T Get(Func<T, Boolean> predicate);
        List<T> GetAll();
        List<T> GetMany(Func<T, Boolean> predicate);
        PageData<T> GetPageData(Int32 pageNo, Int32 pageSize, Func<T, Boolean> where, Func<T, Object> sort);
    }
}
