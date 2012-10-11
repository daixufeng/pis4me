using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Data.BLL;
using MyRabbit.Data.Entity;

namespace SINOMA.WebUI
{
    public partial class ProductDetail : BasePage
    {
        #region Fields
        protected Product product = new Product();
        protected string rootPath;
        protected IList<ProductExtendedAttributeValue> commonExAttrValues = new List<ProductExtendedAttributeValue>();
        protected IList<string> commonGroup  ;
        protected IList<ProductExtendedAttributeValue> designExAttrValues = new List<ProductExtendedAttributeValue>();
        protected IList<string> designGroup ;
        protected IList<ProductExtendedAttributeValue> produceExAttrValues = new List<ProductExtendedAttributeValue>();
        protected IList<string> produceGroup  ;
        protected IList<ProductExtendedAttributeValue> spareExAttrValues = new List<ProductExtendedAttributeValue>();
        protected IList<string> spareGroup  ;
        #endregion
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                AccessControl();

                if (!string.IsNullOrEmpty(Request.QueryString["productId"]))
                {
                    int id = int.TryParse(Request.QueryString["productId"], out id) ? id : 0;

                    if (id == 0)
                        ShowMessage("该产品不存在！", "Default.aspx");

                    product = productManager.GetProductById(id);
                    if (product == null)
                        ShowMessage("该产品不存在！", "Default.aspx");
                    else
                    {
                        IList<ExtendedAttribute> exAttribute = extendedAttributeManager.GetExtendedAttributeByCategoryId(product.Category.Id);
                        IList<ProductExtendedAttributeValue> exAttributeValue = product.ExtendedAttributes.ToList();

                        foreach(var ea in exAttribute)
                        {
                            if (exAttributeValue.Where(c => c.Attribute.Id == ea.Id).ToList().Count() > 0)
                                continue;
                            exAttributeValue.Add(new ProductExtendedAttributeValue() { ProductId = product.Id, Attribute = ea, Value = "" });
                        }

                        commonExAttrValues = exAttributeValue
                            .Where<ProductExtendedAttributeValue>(p => p.Attribute.AttributeCategory.Equals(ExtendedAttributeCategory.Common))
                            .ToList<ProductExtendedAttributeValue>();
                        commonGroup = commonExAttrValues.Select(p => p.Attribute.Group).ToList().Distinct().Where(c => c!=null).ToList();

                        designExAttrValues = exAttributeValue
                            .Where<ProductExtendedAttributeValue>(p => p.Attribute.AttributeCategory.Equals(ExtendedAttributeCategory.Design))
                            .ToList<ProductExtendedAttributeValue>();
                        designGroup = designExAttrValues.Select(p => p.Attribute.Group).ToList().Distinct().Where(c => c != null).ToList();

                        produceExAttrValues = exAttributeValue
                            .Where<ProductExtendedAttributeValue>(p => p.Attribute.AttributeCategory.Equals(ExtendedAttributeCategory.Produce))
                            .ToList<ProductExtendedAttributeValue>();
                        produceGroup = produceExAttrValues.Select(p => p.Attribute.Group).ToList().Distinct().Where(c => c != null).ToList();

                        spareExAttrValues = exAttributeValue
                            .Where<ProductExtendedAttributeValue>(p => p.Attribute.AttributeCategory.Equals(ExtendedAttributeCategory.Spare))
                            .ToList<ProductExtendedAttributeValue>();
                        spareGroup = spareExAttrValues.Select(p => p.Attribute.Group).ToList().Distinct().Where(c => c != null).ToList();
                    }
                }
                else
                {
                    Response.Redirect(Request.RawUrl, true);
                }
                rootPath = this.RootPath + System.Configuration.ConfigurationManager.AppSettings["AttachmentFolderName"] + "/";
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        private void AccessControl()
        {
            pnlCommon.Visible = true;
            pnlDesign.Visible = false;
            pnlSale.Visible = false;
            pnlProduce.Visible = false;
            pnl.Visible = false;
            pnlAttachment.Visible = false;

            lbtnCommon.Visible = true;
            lbtnDesign.Visible = false;
            lbtnSale.Visible = false;
            lbtnProduce.Visible = false;
            lbtn.Visible = false;
            lbtnAttachment.Visible = false;

            if (!Online)
                return;
            else if (LogonUser.Role.Equals(UserRole.Designer))
            {
                pnlDesign.Visible = true;
                pnl.Visible = true;
                lbtnDesign.Visible = true;
                lbtn.Visible = true;
            }
            else if (LogonUser.Role.Equals(UserRole.SeniorDesigner))
            {
                pnlDesign.Visible = true;
                pnl.Visible = true;
                pnlAttachment.Visible = true;
                lbtnDesign.Visible = true;
                lbtn.Visible = true;
                lbtnAttachment.Visible = true;
            }
            else if (LogonUser.Role.Equals(UserRole.Operator) || LogonUser.Role.Equals(UserRole.SeniorOperator))
            {
                pnlProduce.Visible = true;
                lbtnProduce.Visible = true;
            }
            else if (LogonUser.Role.Equals(UserRole.Salesman) || LogonUser.Role.Equals(UserRole.SeniorSalesman))
            {
                pnlDesign.Visible = true;
                pnlSale.Visible = true;
                pnl.Visible = true;
                pnlAttachment.Visible = true;

                lbtnDesign.Visible = true;
                lbtnSale.Visible = true;
                lbtn.Visible = true;
                lbtnAttachment.Visible = true;
            }
            else if (LogonUser.Role.Equals(UserRole.Admin) || LogonUser.Role.Equals(UserRole.Administration))
            {
                pnlDesign.Visible = true;
                pnlSale.Visible = true;
                pnlProduce.Visible = true;
                pnl.Visible = true;
                pnlAttachment.Visible = true;

                lbtnDesign.Visible = true;
                lbtnSale.Visible = true;
                lbtnProduce.Visible = true;
                lbtn.Visible = true;
                lbtnAttachment.Visible = true;
            }
        }
    }
}