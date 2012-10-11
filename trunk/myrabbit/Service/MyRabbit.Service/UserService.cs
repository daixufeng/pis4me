using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Entity;
using System.Data.Entity;
using System.Collections;
using System.Data.Objects;

namespace MyRabbit.Service
{
    public class UserService : MyService<User>
    {

        public override User GetById(int id)
        {
            User user = null;
            using (Entities context = new Entities())
            {
                List<User> users = context.User.Where<User>(p => p.Id == id).ToList<User>();
                user = users.Count > 0 ? users[0] : user;
            }
            return user;
        }

        public List<User> GetAll()
        {
            List<User> users = new List<User>();
            using (Entities context = new Entities())
            {
                var objects = context.User;
                foreach (User o in objects)
                {
                    users.Add(o);
                }
            }
            return users;
        }

        public PageData<User> GetPageData(int pageNo, int pageSize, User criteria)
        {
            PageData<User> data = new PageData<User>();
            using (Entities context = new Entities())
            {
                criteria = criteria == null ? new User() : criteria;
                data.Entities = context.User
                    //.Where<User>(p => p.Email.Contains(criteria.Email) 
                    //|| p.LoginName.Contains(criteria.LoginName)
                    //|| p.NickName.Contains(criteria.NickName))
                    .OrderBy(p => p.NickName)
                    .Skip((pageNo - 1) * pageSize)
                    .Take(pageSize)
                    .ToList<User>();
                data.Count = context.User.Count<User>();
                //int skip = (pageNo - 1) * pageSize;
                //var q = (from user in context.User
                //         from role in context.Role
                //         where user.NickName.Contains(criteria.NickName) && user.RoleId == role.Id
                //         select new User()
                //         {
                //             Id = user.Id,
                //             NickName = user.NickName,
                //             LoginName = user.LoginName,
                //             Email = user.Email,
                //             RoleId = user.RoleId,
                //             RoleName = role.Name
                //         }).Skip(skip).Take(pageSize);

                //List<User> users = q.ToList<User>();
                //data.Entities = users;
            }
            return data;
        }

        public bool UserAuthenticate(String LoginName, String password)
        {
            return true;
        }

        public User GetByLoginName(String loginName)
        {
            User user = null;
            using (Entities context = new Entities())
            {
                List<User> users = context.User.Where<User>(p => p.LoginName.Equals(loginName)
                    || p.Email.Equals(loginName)).ToList<User>();
                user = users.Count > 0 ? users[0] : null;
            }
            return user;
        }

        public User GetByName(string userName)
        {
            User user = null;
            using (Entities context = new Entities())
            {
                List<User> users = context.User.Where<User>(p => p.NickName.Equals(userName)
                    || p.Email.Equals(userName)).ToList<User>();
                user = users.Count > 0 ? users[0] : null;
            }
            return user;
        }
    }
}
