using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.SessionState;
using MyRabbit.Service;

namespace MyRabbit.WebUI
{
    public class Global : System.Web.HttpApplication
    {
        UserService userService = new UserService(); 

        void Application_Start(object sender, EventArgs e)
        {
            // Code that runs on application startup
            log4net.Config.XmlConfigurator.Configure();
        }

        void Application_End(object sender, EventArgs e)
        {
            //  Code that runs on application shutdown
        }

        void Application_Error(object sender, EventArgs e)
        {
            // Code that runs when an unhandled error occurs
        }

        void Session_Start(object sender, EventArgs e)
        {
            // Code that runs when a new session is started
        }

        void Session_End(object sender, EventArgs e)
        {
            //MyRabbit.Entity.User user = Session[MyRabbit.WebUI.GlobalKeys.LogonUser] 
            //    as MyRabbit.Entity.User;

            //Application.Lock();
            //IList<MyRabbit.Entity.User> users = Application[MyRabbit.WebUI.GlobalKeys.TotalLogonUsers] 
            //    as List<MyRabbit.Entity.User>;
            //if (user != null && !user.Id.Equals(0))
            //{
            //    user.IsLogon = 0;
            //    userService.Update(user);
            //    users.Remove(user);
            //    Application[MyRabbit.WebUI.GlobalKeys.LogonUser] = users;
            //}
            //Application.UnLock();
        }
    }
}
