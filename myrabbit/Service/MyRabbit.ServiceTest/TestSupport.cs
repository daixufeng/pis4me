using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using Common.Logging;
using MyRabbit.IService;
using Microsoft.Practices.Unity;
using MyRabbit.Entity.DBInteractions;
using MyRabbit.Entity.EntityRepositories;
using MyRabbit.Service;

namespace MyRabbit.ServiceTest
{
    [TestFixture]
    public class TestSupport
    {
        private ILog _log = null;
        protected Spring.Context.IApplicationContext context;
        protected IUnityContainer container = null;
        public TestSupport()
        {
            _log = LogManager.GetLogger(this.GetType());
            container = new UnityContainer()
               .RegisterType<IDBFactory, DBFactory>()
               .RegisterType<IUnitOfWork, UnitOfWork>()
               .RegisterType<IUserService, UserService>()
               .RegisterType<IUserRepository, UserRepository>();
            //context = Spring.Context.Support.ContextRegistry.GetContext();
        }


        public ILog Log
        {
            get
            {
                if (_log == null) _log = LogManager.GetLogger(this.GetType());
                return _log;
            }
        }


        public void LogTest()
        {
            Log.Debug("Debug");
            Log.Error("Error");
        }
    }
}
