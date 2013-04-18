using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MyRabbit.Entity;
using System.Data.Entity;
using System.Collections;
using System.Data.Objects;
using MyRabbit.IService;
using System.Linq.Expressions;
using MyRabbit.Entity.EntityRepositories;
using MyRabbit.Entity.DBInteractions;

namespace MyRabbit.Service
{
    public class UserService : IUserService
    {

        private readonly IUserRepository _userRepository;
        private readonly IUnitOfWork _unitOfWork;

        public UserService(IUserRepository userRepository, IUnitOfWork unitOfWork) 
        {
            this._userRepository = userRepository;
            this._unitOfWork = unitOfWork;
        }

        //public PageData<User> GetPageData(int pageNo, int pageSize, User criteria)
        //{
        //    PageData<User> data = new PageData<User>();
        //    using (Entities context = new Entities())
        //    {
        //        criteria = criteria == null ? new User() : criteria;
                

        //        data.Entities = context.User
        //            .Where(p => p.Email.Contains(criteria.Email == null ? "" : criteria.Email)
        //            //.Where<User>(p => p.Email.Contains(criteria.Email) 
        //            || p.LoginName.Contains(criteria.LoginName == null ? "" : criteria.LoginName)
        //            || p.NickName.Contains(criteria.NickName == null ? "" : criteria.NickName))
        //            .OrderBy(p => p.NickName)
        //            .Skip((pageNo - 1) * pageSize)
        //            .Take(pageSize)
        //            .ToList<User>();
        //        data.Count = context.User.Count<User>();
        //        //int skip = (pageNo - 1) * pageSize;
        //        //var q = (from user in context.User
        //        //         from role in context.Role
        //        //         where user.NickName.Contains(criteria.NickName) && user.RoleId == role.Id
        //        //         select new User()
        //        //         {
        //        //             Id = user.Id,
        //        //             NickName = user.NickName,
        //        //             LoginName = user.LoginName,
        //        //             Email = user.Email,
        //        //             RoleId = user.RoleId,
        //        //             RoleName = role.Name
        //        //         }).Skip(skip).Take(pageSize);

        //        //List<User> users = q.ToList<User>();
        //        //data.Entities = users;
                
                
        //    }
        //    return data;
        //}

        public bool UserAuthenticate(String LoginName, String password)
        {
            return true;
        }

        //public User GetByLoginName(String loginName)
        //{
        //    User user = null;
        //    using (Entities context = new Entities())
        //    {
        //        List<User> users = context.User.Where<User>(p => p.LoginName.Equals(loginName)
        //            || p.Email.Equals(loginName)).ToList<User>();
        //        user = users.Count > 0 ? users[0] : null;
        //    }
        //    return user;
        //}

        //public User GetByName(string userName)
        //{
        //    User user = null;
        //    using (Entities context = new Entities())
        //    {
        //        List<User> users = context.User.Where<User>(p => p.NickName.Equals(userName)
        //            || p.Email.Equals(userName)).ToList<User>();
        //        user = users.Count > 0 ? users[0] : null;
        //    }
        //    return user;
        //}

        public User GetById(int id)
        {
          return _userRepository.GetById(id);
        }

        public List<User> GetAll()
        {
            return _userRepository.GetAll();
        }

        public List<User> GetMany(Func<User, Boolean> where)
        {
            return _userRepository.GetMany(where);
        }

        public PageData<User> GetPageData(Int32 pageNo, Int32 pageSize, Func<User, Boolean> where, Func<User, Object> sort)
        {
            return _userRepository.GetPageData(pageNo, pageSize, where, sort);
        }

        public User GetByLoginName(string loginName)
        {
            throw new NotImplementedException();
        }

        public User GetByName(string userName)
        {
            throw new NotImplementedException();
        }

        public void Create(User user)
        {
            _userRepository.Create(user);
        }

        public void Update(User user)
        {
            _userRepository.Update(user);
        }

        public void Delete(User user)
        {
            _userRepository.Delete(user);
        }

        public void Delete(IList<User> users)
        {
            throw new NotImplementedException();
        }
    }
}
