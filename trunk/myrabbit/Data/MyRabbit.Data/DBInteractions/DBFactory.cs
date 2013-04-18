using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Entity;

namespace MyRabbit.Entity.DBInteractions
{
    public class DBFactory: Disposable, IDBFactory
    {
        private MyRabbitContext dataContext;

        public DBFactory()
        {
            Database.SetInitializer<MyRabbitContext>(null);
        }
        public MyRabbitContext Get()
        {
            return dataContext ?? (dataContext = new MyRabbitContext());
        }

        protected override void DisposeCore()
        {
            if (dataContext != null)
            {
                dataContext.Dispose();
            }
        }
    }
}
