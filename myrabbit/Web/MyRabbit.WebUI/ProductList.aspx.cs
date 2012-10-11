using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Data.Entity;
using MyRabbit.Data.BLL;
using System.Web.Script.Serialization;

namespace SINOMA.WebUI
{
    public partial class ProductList : BasePage
    {
        #region Fileds
        private readonly string CATEGORYID = "ProductList_CategoryId";
        private readonly string ATTRIBUTECONDITION = "ProductList_AttributeCondition";
        private readonly string CATEGORIES = "ProductList_Categories";
        private readonly string PRODUCTS = "ProductList_Products";
        private readonly string SELECTEDCONDITION = "ProductList_SelectedCondition";
        private readonly string CHILDRENCATEGORIES = "ProductList_ChildrenCategories";

        private string SearchType
        {
            get { return ViewState["SearchType"].ToString(); }
            set { ViewState["SearchType"] = value; }
        }

        private int categoryId = 0;

        protected bool isParentCategory
        {
            get
            {
                string val = ViewState["IsParentCategory"].ToString();
                return !string.IsNullOrEmpty(val) && val.ToLower().Equals("true");
            }
            set { ViewState["IsParentCategory"] = value; }
        }

        protected string keyWord
        {
            get { return Session[GlobalKeys.ProductSearchKey] != null 
                ? Session[GlobalKeys.ProductSearchKey].ToString() : string.Empty; }
            set { Session[GlobalKeys.ProductSearchKey] = value; }
        }

        protected bool isConditionListShow
        {
            get
            {
                return !(!isParentCategory && selectedConditions.Count == 1 && selectedConditions[0].Key.Equals("Category"));
            }
        }

        protected IList<Product> displayList = new List<Product>();

        protected IList<Product> products = new List<Product>();

        protected IList<ExtendedAttribute> attributeCondition = new List<ExtendedAttribute>();

        protected IList<ProductCategory> categories = new List<ProductCategory>();

        protected IList<ProductCategory> childCategories = new List<ProductCategory>();

        protected IList<SelectedCondition> selectedConditions = new List<SelectedCondition>();

        protected string lblPictureNO = string.Empty;
        protected string lblSpec = string.Empty;
        #endregion

        #region protected methods
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                if (!IsPostBack)
                {
                    string strCategoryId = Request.QueryString["categoryId"];
                    string strSearchKey = string.Empty;

                    //search by category
                    if (!string.IsNullOrEmpty(strCategoryId))
                    {
                        categoryId = int.TryParse(strCategoryId, out categoryId) ? categoryId : 0;

                        //check the type of categoryId 
                        isParentCategory = !string.IsNullOrEmpty(Request.QueryString["isParent"]);

                        ViewState[CATEGORYID] = categoryId;

                        SearchType = "Category";

                        panelCdnAttribute.Visible = true;                       

                        GetAttributeCondition();

                        ViewState[ATTRIBUTECONDITION] = attributeCondition;

                        //InitCementAbility();

                        if (!isParentCategory)
                        {
                            selectedConditions.Add(new SelectedCondition() { Key = "Category", ValueMember = categoryId.ToString() });
                        }
                        ViewState[SELECTEDCONDITION] = selectedConditions;

                        GetProductByCategory();

                        SetTitle();

                        if (isParentCategory)
                        {
                            Iesi.Collections.Generic.ISet<ProductCategory> Children = categoryManager.GetCategoryById(categoryId).Children;
                            foreach (var o in Children)
                                childCategories.Add(o);
                        }
                        ViewState[CHILDRENCATEGORIES] = childCategories;

                        keyWord = null;
                    }
                    else if (keyWord != null)//fuzzy query
                    {
                        SearchType = "FuzzyQuery";

                        panelCdnCategory.Visible = true;

                        GetProductByFuzzyQuery();

                        lblTitle.Text = "包含<font color=\"red\">\"" + keyWord + "\"</font>的查询结果";
                    }
                    else//error
                    {
                        ScriptRedirect("Default.aspx");
                    }
                }
                else
                {
                    if (SearchType.Equals("Category"))
                    {
                        childCategories = ViewState[CHILDRENCATEGORIES] as List<ProductCategory>;
                        attributeCondition = ViewState[ATTRIBUTECONDITION] as List<ExtendedAttribute>;
                        selectedConditions = ViewState[SELECTEDCONDITION] as List<SelectedCondition>;
                        categoryId = int.TryParse(ViewState[CATEGORYID].ToString(), out categoryId) ? categoryId : 0;
                        lblPictureNO = Request.Form["txtPictureNO"];
                        lblSpec = Request.Form["txtSpec"];
                    }
                    else
                    {
                        categories = ViewState[CATEGORIES] as List<ProductCategory>;
                    }
                }
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void ListPageIndexChange(object sender, GridViewPageEventArgs e)
        {
            try
            {
                int start = e.NewPageIndex * listPager.PageSize;
                products = ViewState[PRODUCTS] as List<Product>;
                GetDisplayList(start);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void AttributePostBack(object sender, EventArgs e)
        {
            try
            {
                listPager.CurrentPageIndex = 0;

                if (string.IsNullOrEmpty(hdfValue.Value))
                {
                    if (!string.IsNullOrEmpty(lblSpec) || !string.IsNullOrEmpty(lblPictureNO))
                    {
                        SelectedCondition sltPic = new SelectedCondition() { Key = "PictureNO", ValueMember = lblPictureNO, DisplayMember = lblPictureNO };
                        selectedConditions.Remove(sltPic);
                        if (!string.IsNullOrEmpty(lblPictureNO))
                            selectedConditions.Add(sltPic);

                        SelectedCondition sltSpec = new SelectedCondition() { Key = "SpecificationType", ValueMember = lblSpec, DisplayMember = lblSpec };
                        selectedConditions.Remove(sltSpec);
                        if (!string.IsNullOrEmpty(lblSpec))
                            selectedConditions.Add(sltSpec);
                    }
                    else
                    {
                        selectedConditions = new List<SelectedCondition>();
                        if (!isParentCategory)
                        {
                            selectedConditions.Add(new SelectedCondition()
                            {
                                Key = "Category",
                                ValueMember = categoryId.ToString()
                            });
                        }
                    }
                }
                else
                {

                    JavaScriptSerializer jsSerializer = new JavaScriptSerializer();
                    SelectedCondition sltCondition = jsSerializer.Deserialize<SelectedCondition>(hdfValue.Value);

                    if (hdfValue.Value.Contains(","))
                    {
                        if (selectedConditions.Contains(sltCondition))
                        {
                            SelectedCondition o = selectedConditions
                                .Single<SelectedCondition>(p => p.Key.Equals(sltCondition.Key));
                            selectedConditions.Remove(o);
                        }
                        selectedConditions.Add(sltCondition);
                    }
                    else
                    {
                        selectedConditions.Remove(sltCondition);

                        if (sltCondition.Key.Equals("PictureNO"))
                            lblPictureNO = string.Empty;
                        if (sltCondition.Key.Equals("SpecificationType"))
                            lblSpec = string.Empty;
                    }
                    hdfValue.Value = string.Empty;
                }

                ViewState[SELECTEDCONDITION] = selectedConditions;

                products = productManager.GetProductByCondition(categoryId, selectedConditions);

                ViewState[PRODUCTS] = products;

                GetDisplayList(0);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void dplCementAbility_SelectedIndexChanged(object sender, EventArgs e)
        {
            //try
            //{
            //    if (dplCementAbility.SelectedIndex.Equals(0))
            //        hdfValue.Value = "{Key:\"CementAbility\"}";
            //    else
            //        hdfValue.Value = "{Key:\"CementAbility\",ValueMember:\"" + dplCementAbility.SelectedValue
            //            + "\",DisplayMember:\"" + dplCementAbility.SelectedValue + "\"}";
            //    AttributePostBack(sender, e);
            //}
            //catch (Exception ex)
            //{
            //    Log.Error(ex);
            //    RedirectToErrorPage();
            //}
        }
        #endregion

        #region private methods
        private void GetProductByFuzzyQuery()
        {
            products = productManager.GetProductByFuzzyQuery(categoryId, keyWord);

            categories = new List<ProductCategory>();
            foreach (var o in products)
            {
                categories.Add(o.Category);
            }
            categories = categories.Distinct<ProductCategory>().ToList<ProductCategory>();

            ViewState[CATEGORIES] = categories;

            ViewState[PRODUCTS] = products;

            GetDisplayList(0);
        }

        private void GetProductByCategory()
        {
            products = productManager.GetProductByCategory(categoryId, isParentCategory);

            ViewState[PRODUCTS] = products;

            GetDisplayList(0);
        }

        private void GetDisplayList(int start)
        {
            displayList = new List<Product>();
            for (int i = 0; start < products.Count && i < listPager.PageSize; start++, i++)
            {
                displayList.Add(products[start]);
            }
            listPager.CurrentPageIndex = start == 0 ? 0 : listPager.CurrentPageIndex;
            listPager.RecordCount = products.Count;
        }

        private void SetTitle()
        {
            string strCategory = string.Empty;
            ProductCategory category = productCategoryManager.GetById(categoryId);
            if (isParentCategory)
            {
                strCategory = " > <a href=\"ProductList.aspx?categoryId={0}&isParent=true\">{1}</a>";
                strCategory = string.Format(strCategory, category.Id, category.Name);
            }
            else
            {
                ProductCategory parentCategory = productCategoryManager.GetById(category.ParentId);
                strCategory = " > <a href=\"ProductList.aspx?categoryId={0}&isParent=true\">{1}</a>"
                    + "> <a href=\"ProductList.aspx?categoryId={2}\">{3}</a>";
                strCategory = string.Format(strCategory, parentCategory.Id, parentCategory.Name, category.Id, category.Name);
            }

            lblTitle.Text = "<a href=\"Default.aspx\">中材装备产品分类</a>" + strCategory;
        }

        private void GetAttributeCondition()
        {
            attributeCondition = extendedAttributeManager.GetByCategory(categoryId, isParentCategory);

            attributeCondition.Single<ExtendedAttribute>(p => p.Name.Equals("CementAbility")).Values
                = systemProfileManager.GetById("CementPlantAbility").Value.Split(',');

            string[] keys = new string[] { "ProduceAbility", "MotorPower"};
            foreach (var o in keys)
            {
                ExtendedAttribute attribute = attributeCondition.Single<ExtendedAttribute>(p => p.Name.Equals(o));
                IList<double> dblVal = new List<double>();
                foreach (var val in attribute.Values)
                {
                    dblVal.Add(double.Parse(val.ToString()));
                }

                IList<object> objVal = new List<object>();
                if (dblVal.Count < 10)
                {
                    for (int i = 0; i < dblVal.Count; i++)
                    {
                        if (i == 5)
                        {
                            objVal[4] = objVal[4] + "以上";
                            break;
                        }
                        objVal.Add(Remove0FromEnd(dblVal[i]));
                    }
                }
                else
                {
                    int start = dblVal.Count / 5;
                    objVal.Add(Remove0FromEnd(dblVal[start]) + "以下");
                    objVal.Add(Remove0FromEnd(dblVal[start]) + "~" + Remove0FromEnd(dblVal[start * 2]));
                    objVal.Add(Remove0FromEnd(dblVal[start * 2]) + "~" + Remove0FromEnd(dblVal[start * 3]));
                    objVal.Add(Remove0FromEnd(dblVal[start * 3]) + "~" + Remove0FromEnd(dblVal[start * 4]));
                    objVal.Add(Remove0FromEnd(dblVal[start * 4]) + "以上");
                }
                attribute.Values = objVal;
            }
        }

        private string Remove0FromEnd(double dblVal)
        {
            return dblVal.ToString("0.000").TrimEnd(new char[] { '0', '0' }).TrimEnd(new char[] { '0' }).TrimEnd(new char[] { '.' });
        }

        private void InitCementAbility()
        {
            //string[] values = systemProfileManager.GetById("CementPlantAbility").Value.Split(',');

            //dplCementAbility.Items.Add(new ListItem("请选择水泥厂配套生产能力", "0"));

            //foreach (string o in values)
            //{
            //    ListItem item = new ListItem(o, o);
            //    dplCementAbility.Items.Add(item);
            //}
        }
        #endregion
    }
}