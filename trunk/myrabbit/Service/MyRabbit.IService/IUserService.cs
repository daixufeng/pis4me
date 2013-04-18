using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Entity;

namespace MyRabbit.IService
{

    public interface IUserService
    {
        User GetById(Int32 id);
        List<User> GetAll();
        List<User> GetMany(Func<User, Boolean> where);
        PageData<User> GetPageData(Int32 pageNo, Int32 pageSize, Func<User, Boolean> where, Func<User, Object> sort);
        //PageData<User> GetPageData(int pageNo, int pageSize, User criteria);
        bool UserAuthenticate(String LoginName, String password);
        User GetByLoginName(String loginName);
        User GetByName(string userName);
        void Create(User user);
        void Update(User user);
        void Delete(User user);
        void Delete(IList<User> users);
    }
}
