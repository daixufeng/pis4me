using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Entity;
using MyRabbit.Entity.DBInteractions;

namespace MyRabbit.Entity.EntityRepositories
{
    public class UserRepository : EntityRepositoryBase<User>, IUserRepository
    {
        public UserRepository(IDBFactory databaseFactory)
            : base(databaseFactory)
        { 
        
        }
    }
}
