using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyRabbit.Entity.DBInteractions
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly IDBFactory _databaseFactory;
        private MyRabbitContext _dataContext;

        public UnitOfWork(IDBFactory databaseFactory)
        {
            this._databaseFactory = databaseFactory;
        }

        protected MyRabbitContext DataContext
        {
            get { return _dataContext ?? (_dataContext = new MyRabbitContext()); }
        }

        public void Commit()
        {
            DataContext.Commit();
        }
    }
}
