using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using log4net;
using MyRabbit.Entity;
using MyRabbit.WebUI;

namespace SINOMA.WebUI.Handlers
{
    /// <summary>
    /// Summary description for CategoryMgtHandler
    /// </summary>
    public class CategoryMgtHandler : IHttpHandler
    {
        #region Fields
        private ILog _log = null;
        private ILog Log
        {
            get
            {
                if (_log == null) _log = LogManager.GetLogger(this.GetType());

                return _log;
            }
        }

        public bool IsReusable
        {
            get
            {
                return false;
            }
        }

        ProductCategoryManager categoryManager = new ProductCategoryManager();
        #endregion
        public void ProcessRequest(HttpContext context)
        {
            try
            {
                User operateUser = context.Session[GlobalKeys.LogonUser] as User;

                string messageFormat = "requestUrl:{0},OperateUser:{1},Message:{2}";
                string logMessage = string.Empty;

                if (operateUser == null)
                {
                    logMessage = string.Format(messageFormat, context.Request.Url, "null", "Invalidate Request");
                }
                else if (!operateUser.RoleName.Equals("Admin"))
                {
                    logMessage = string.Format(messageFormat, context.Request.Url, operateUser.NickName, "Access denied");
                }
                else
                {
                    string categoryName = context.Request.QueryString["categoryName"];//用户账户
                    int parentId = int.TryParse(context.Request.QueryString["parentId"], out parentId) ? parentId : 0;
                    IList<ProductCategory> lst = categoryManager.GetCategoryByParentId(parentId);

                    ProductCategory selectedCategory = context.Session["CategoryMgt_SelectedCategory"] as ProductCategory;

                    bool has = lst.Where<ProductCategory>(p => p.Name.Equals(categoryName) && !p.Id.Equals(selectedCategory.Id))
                        .Count<ProductCategory>() > 0;                    

                    string respVal = has ? "{returnValue: false}" : "{returnValue: true}";
                    context.Response.Write(respVal);
                }

                if (!string.IsNullOrEmpty(logMessage))
                    Log.Debug(logMessage);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                context.Response.Write("{returnValue: false}");
            }
        }
    }
}