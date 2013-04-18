using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.Practices.Unity;
using MyRabbit.Entity.DBInteractions;
using MyRabbit.IService;
using MyRabbit.Entity.EntityRepositories;
using MyRabbit.Service;

namespace MyRabbit.WebUI.IoC
{
    public static class Bootstrapper
    {
        public static void Configure(IUnityContainer container)
        {
            container
                //.RegisterType<ICategoryService, CategoryService>()
                //.RegisterType<ICustomerService, CustomerService>()
                //.RegisterType<IOrderService, OrderService>()
                //.RegisterType<IOrderDetailService, OrderDetailService>()
                //.RegisterType<IProductService, ProductService>()
                //.RegisterType<ICategoryDao, SqlServerCategoryDao>()
                //.RegisterType<ICustomerDao, SqlServerCustomerDao>()
                //.RegisterType<IOrderDao, SqlServerOrderDao>()
                //.RegisterType<IOrderDetailDao, SqlServerOrderDetailDao>()
                .RegisterType<IDBFactory, DBFactory>()
               .RegisterType<IUnitOfWork, UnitOfWork>()
               .RegisterType<IUserRepository, UserRepository>()
               .RegisterType<IUserService, UserService>();
        }
    }
}