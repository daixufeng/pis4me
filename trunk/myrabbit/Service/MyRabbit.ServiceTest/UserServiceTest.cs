using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Entity;
using NUnit.Framework;
using MyRabbit.IService;
using Microsoft.Practices.Unity;
using MyRabbit.Service;
using MyRabbit.Entity.EntityRepositories;
using MyRabbit.Entity.DBInteractions;

namespace MyRabbit.ServiceTest
{
    public class UserServiceTest: TestSupport
    {
        [Test]
        public void GetAll() {
           
            IUserService userService = container.Resolve<IUserService>();
            //IEnumerable<User> users = userService.GetAll();

            IEnumerable<User>  users = userService
                .GetMany(p => p.NickName.Contains("xu")).AsEnumerable<User>();


            Log.Debug("Count:" + users.Count<User>());
        }

        [Test]
        public void Update()
        {
            IUserService userService = container.Resolve<IUserService>();
            User user = userService.GetById(1);
            Log.Debug(user.NickName);
        }

        [Test]
        public void GetById()
        {

        }
    }
}
