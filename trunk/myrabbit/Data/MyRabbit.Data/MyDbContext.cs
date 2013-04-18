using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using MyRabbit.Entity;

namespace MyRabbit.Entity
{
    public class MyRabbitContext : DbContext
    {
        public DbSet<User> User { get; set; }

        public DbSet<Role> Role { get; set; }

        public virtual void Commit()
        {
            base.SaveChanges();
        }
    }
}
