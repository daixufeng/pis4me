using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Service;
using MyRabbit.Entity;
using NUnit.Framework;

namespace MyRabbit.ServiceTest
{
    public class UserServiceTest:TestSupport
    {
        [Test]
        public void GetAll() {
            UserService service = new UserService();
            IList<User> users = service.GetAll();
            Log.Debug(users.Count);
        }

        [Test]
        public void Update()
        {
            UserService service = new UserService();
            
        }

        [Test]
        public void GetById()
        {
            UserService service = new UserService();
           User user =  service.GetById(1);
           Log.Debug(user.NickName);
        }
    }
}
