using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using MyRabbit.Entity;

namespace SINOMA.WebUI
{
    public partial class ProductSalesDetail : AuthBasePage
    {
        #region fields
        private readonly string CATETOGORIES = "Categories";
        private readonly string PRODUCT = "product";
        private IList<ProductCategory> categories = new List<ProductCategory>();
        private IList<Product> product = new List<Product>();
        #endregion

        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (!LogonUser.Role.Equals(UserRole.Admin) && !LogonUser.Role.Equals(UserRole.Salesman)
                    && !LogonUser.Role.Equals(UserRole.SeniorSalesman) && !LogonUser.Role.Equals(UserRole.Administration))
                {
                    ShowMessage("你没有权限进行此操作！", "Default.aspx");
                }

                if (!IsPostBack)
                {
                    categories = categoryManager.GetCategoryByParentId(0);
                    ViewState[CATETOGORIES] = categories;
                    InitTreeview();
                    btnSearch_Click(sender, e);
                }
                else
                {
                    categories = ViewState[CATETOGORIES] as List<ProductCategory>;
                }
               
            }
            catch (Exception ex)
            {
                if (!ex.GetType().Equals(typeof(System.Threading.ThreadAbortException)))
                    Log.Error(ex);
            }
        }

       protected void btnClear_Click(object sender, EventArgs e)
        {
            try
            {
                lblSalesSum.Text = "";
                txtBegDate.Text = "";
                txtEndDate.Text = "";

                while (tvwCategory.CheckedNodes.Count > 0)
                {
                    tvwCategory.CheckedNodes[0].Checked = false;
                }
                gvSalesDetail.DataBind();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                //RedirectToErrorPage();
            }
        }

        protected void btnSearch_Click(object sender, EventArgs e)
        {
            try
            {
                DateTime begDate = new DateTime();
                DateTime endDate = new DateTime();
                begDate = DateTime.TryParse(txtBegDate.Text, out begDate) ? begDate : new DateTime(1900, 1, 1);
                endDate = DateTime.TryParse(txtEndDate.Text, out endDate) ? endDate.AddDays(1) : new DateTime(9999, 12, 31);

                begDate = new DateTime(begDate.Year, begDate.Month, begDate.Day, 0, 0, 0, DateTimeKind.Utc);
                endDate = new DateTime(endDate.Year, endDate.Month, endDate.Day, 0, 0, 0, DateTimeKind.Utc);

                IList<ProductCategory> category = new List<ProductCategory>();
                ProductCategory pc;
                foreach (TreeNode node in tvwCategory.CheckedNodes)
                {
                    foreach (var ca in categories)
                    {
                        pc = ca.Children.Where(c => c.Id.Equals(int.Parse(node.Value))).FirstOrDefault();
                        if (pc!= null)
                        category.Add(pc);
                    }
                }
                product = productManager.GetByCondition(category, begDate, endDate);
                Cache[PRODUCT] = product;
                gvSalesDetail.DataSource = GetDisplay(0, product);
                gvSalesDetail.DataBind();

                int sum = 0;
                foreach (var p in product)
                {
                    sum += p.SaleDetail.Where(s=>s.SignDate>begDate && s.SignDate<endDate).ToList().Select(s => s.SaleCount).Sum();
                }
                lblSalesSum.Text = sum.ToString();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                //RedirectToErrorPage();
            }
        }

        protected void gvSalesDetail_PageIndexChanged(object sender, GridViewPageEventArgs e)
        {
            try
            {
                int start = e.NewPageIndex * gvSalesDetailPager.PageSize;
                product = Cache[PRODUCT] as List<Product>;
                gvSalesDetail.DataSource = GetDisplay(start, product);
                gvSalesDetail.DataBind();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                //RedirectToErrorPage();
            }
        }

        protected void gvSalesDetail_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            try
            {
                DateTime begDate = new DateTime();
                DateTime endDate = new DateTime();
                begDate = DateTime.TryParse(txtBegDate.Text, out begDate) ? begDate : new DateTime(1900, 1, 1);
                endDate = DateTime.TryParse(txtEndDate.Text, out endDate) ? endDate.AddDays(1) : new DateTime(9999, 12, 31);

                begDate = new DateTime(begDate.Year, begDate.Month, begDate.Day, 0, 0, 0, DateTimeKind.Utc);
                endDate = new DateTime(endDate.Year, endDate.Month, endDate.Day, 0, 0, 0, DateTimeKind.Utc);

                if (e.Row.RowType.Equals(DataControlRowType.DataRow))
                {
                    Product p = e.Row.DataItem as Product;
                    e.Row.Cells[4].Text = p.SaleDetail.Where(s=>s.SignDate>begDate && s.SignDate<endDate).ToList().Select(s=>s.SaleCount).Sum().ToString();
                }
            }
            catch (Exception ex)
            {
                Log.Error(ex);
               // RedirectToErrorPage();
            }
        }

        #region private method
        private IList<Product> GetDisplay(int start, IList<Product> product)
        {
            IList<Product> displayList = new List<Product>();

            for (int i = 0; i < gvSalesDetailPager.PageSize && start < product.Count; start++, i++)
            {
                displayList.Add(product[start]);
            }
            gvSalesDetailPager.CurrentPageIndex = start == 0 ? 0 : gvSalesDetailPager.CurrentPageIndex;
            gvSalesDetailPager.RecordCount = product.Count;
            return displayList;
        }

        private void InitTreeview()
        {
            TreeNode root = new TreeNode("中材产品", "0");
            tvwCategory.Nodes.Clear();
            tvwCategory.Nodes.Add(root);

            AddChildNode(root, categories);
            tvwCategory.ExpandAll();
        }

        private void AddChildNode(TreeNode node, IList<ProductCategory> children)
        {
            foreach (var o in children)
            {
                TreeNode child = new TreeNode(o.Name, o.Id.ToString());
                node.ChildNodes.Add(child);
                IList<ProductCategory> cc = new List<ProductCategory>();
                if (o.Children != null)
                {
                    foreach (var c in o.Children)
                    {
                        cc.Add(c);
                    }
                }
                AddChildNode(child, cc);
            }
        }
        #endregion
    }
}