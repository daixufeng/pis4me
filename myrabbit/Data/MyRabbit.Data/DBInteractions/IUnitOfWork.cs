using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyRabbit.Entity.DBInteractions
{
    public interface IUnitOfWork
    {
        void Commit();
    }
}
