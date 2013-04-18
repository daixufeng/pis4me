using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Entity.DBInteractions;
using MyRabbit.Entity;

namespace MyRabbit.Entity.EntityRepositories
{
    public interface IUserRepository : IEntityRepository<User>
    {
    }
}
