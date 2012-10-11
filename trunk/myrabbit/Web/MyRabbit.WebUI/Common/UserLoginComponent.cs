using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyRabbit.WebUI
{
    public class UserLoginComponent : AuthBasePage
    {
        public string UserAuthenticate(string loginName, string password)
        {
            if (userService.UserAuthenticate(loginName, password))
            {
                MyRabbit.Entity.User user = userService.GetByLoginName(loginName);
                
                if (SingleLogin)
                {
                    Context.Application.Lock();
                    IList<MyRabbit.Entity.User> users = Context.Application[GlobalKeys.TotalLogonUsers] as List<MyRabbit.Entity.User>;
                    if (users == null)
                        users = new List<MyRabbit.Entity.User>();

                    if (users.Contains(user))
                        return "该用户已经登录，不能再次登录！";
                    users.Add(user);
                    Context.Application[GlobalKeys.TotalLogonUsers] = users; //记录到Application中
                }

                LogonUser = user;
                return string.Empty;
            }
            else
            {
                return "用户名或密码错误，请重试。";
            }
        }

        public void UserLogout()
        {
            if (SingleLogin)
            {
                Context.Application.Lock();
                IList<MyRabbit.Entity.User> users = Context.Application[GlobalKeys.TotalLogonUsers] as List<MyRabbit.Entity.User>;

                LogonUser.IsLogon = 0;
                userService.Update(LogonUser);
                users.Remove(LogonUser);
                Context.Application[GlobalKeys.TotalLogonUsers] = users;

                Context.Application.UnLock();
            }
        }
    }
}