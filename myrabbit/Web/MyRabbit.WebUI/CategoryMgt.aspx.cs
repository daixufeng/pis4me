using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MyRabbit.Data.Entity;

namespace SINOMA.WebUI.Admin
{
    public partial class CategoryMgt : AuthBasePage
    {
        #region Fileds
        private IList<ProductCategory> categories = new List<ProductCategory>();
        private ProductCategory selectedCategory = new ProductCategory();
        private IList<ExtendedAttribute> attributes = new List<ExtendedAttribute>();
        private IList<ExtendedAttribute> removedAttributes = new List<ExtendedAttribute>();
        private IList<ProductUnit> units = new List<ProductUnit>();

        private readonly string CATETOGORIES = "CategoryMgt_Categories";
        private readonly string PRODUCTUNIT = "CategoryMgt_ProductUnit";
        private readonly string ATTRIBUTES = "CategoryMgt_Attributes";
        private readonly string REMOVEDATTRIBUTES = "CategoryMgt_RemovedAttributes";
        private readonly string SELECTECATEGORY = "CategoryMgt_SelectedCategory";

        private readonly string ORIGINALSELECTECATEGORY = "CategoryMgt_OriginalSelectedCATEGORY";
        private readonly string ORIGINALATTRIBUTES = "CategoryMgt_OriginalAttributes";
        private bool IsAddAttr
        {
            get
            {
                if (Session["CategoryMgt_IsAddAttr"] == null || Session["CategoryMgt_IsAddAttr"].ToString().ToLower() == "false")
                    return false;
                else
                    return true;
            }
            set
            {
                Session["CategoryMgt_IsAddAttr"] = value;
            }
        }

        /// <summary>
        /// on edit status or on add new status
        /// </summary>
        private bool HasModify
        {
            get
            {
                if (Session["CategoryMgt_HasModify"] == null || Session["CategoryMgt_HasModify"].ToString().ToLower() == "false")
                    return false;
                else
                    return true;
            }
            set
            {
                Session["CategoryMgt_HasModify"] = value;
            }
        }
        #endregion

        #region Protected Methods
        protected void Page_Load(object sender, EventArgs e)
        {
            //Access Role
            if (!LogonUser.Role.Equals(UserRole.Admin))
            {
                ShowMessage("你没有权限进行此操作！", "Default.aspx");
            }

            try
            {
                if (!IsPostBack)
                {
                    lblTitle.Text = "产品类别管理 > 新增类别";

                    categories = categoryManager.GetCategoryByParentId(0);

                    units = productUnitMangager.GetAll();

                    Session[CATETOGORIES] = categories;
                    Session[PRODUCTUNIT] = units;
                    Session[SELECTECATEGORY] = selectedCategory;
                    Session[ATTRIBUTES] = attributes;
                    Session[REMOVEDATTRIBUTES] = removedAttributes;

                    InitTreeview();

                    InitGridView();
                    //if (Request.Cookies["Category-Help-Show"] == null || Request.Cookies["Category-Help-Show"].Value == "true")
                    //  modalHelp.Show();
                }
                else
                {
                    categories = Session[CATETOGORIES] as List<ProductCategory>;
                    units = Session[PRODUCTUNIT] as List<ProductUnit>;
                    selectedCategory = Session[SELECTECATEGORY] as ProductCategory;

                    attributes = Session[ATTRIBUTES] as List<ExtendedAttribute>;
                    if (attributes == null) attributes = new List<ExtendedAttribute>();
                    removedAttributes = Session[REMOVEDATTRIBUTES] as List<ExtendedAttribute>;
                    if (removedAttributes == null) removedAttributes = new List<ExtendedAttribute>();

                    lblCategoryChkMsg.Visible = false;
                    imgCategoryChkMsg.Visible = false;
                }
            }
            catch (Exception ex)
            {
                if (!ex.GetType().Equals(typeof(System.Threading.ThreadAbortException)))
                    Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void tvwCategory_SelectedNodeChanged(object sender, EventArgs e)
        {
            try
            {
                lblTitle.Text = "产品类别管理 > ";

                TreeNode node = tvwCategory.SelectedNode;

                if (node.Value.Equals("0"))
                {
                    ShowMessage("该类别不能进行编辑！");
                    btnAdd_Click(sender, e);
                    return;
                }

                //txtCategory.Enabled = false;

                //非莫级类别不能编辑扩展属性
                btnAddAttr.Visible = !node.Parent.Value.Equals("0");

                selectedCategory = productCategoryManager.GetById(int.Parse(node.Value));

                attributes = extendedAttributeManager.GetExtendedAttributeByCategoryId(selectedCategory.Id);

                InitGridView();

                dplParent.SelectedValue = node.Parent.Value;
                txtCategory.Text = node.Text;

                lblTitle.Text += node.Text;
                txtDescription.Text = selectedCategory.Description;
                Session[SELECTECATEGORY] = selectedCategory;

                Session[ORIGINALSELECTECATEGORY] = selectedCategory;
                Session[ORIGINALATTRIBUTES] = attributes;

                //check wether this category can be deleted
                hdfIsUsed.Value = HasProduct() ? "true" : "";
                
                HasModify = true;
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        #region GridView Events
        protected void gvwExtendedAttribute_RowEditing(object sender, GridViewEditEventArgs e)
        {
            try
            {
                GridViewRow row = gvwExtendedAttribute.Rows[e.NewEditIndex];
                gvwExtendedAttribute.EditIndex = e.NewEditIndex;
                gvwExtendedAttribute.DataSource = attributes;
                gvwExtendedAttribute.DataBind();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwExtendedAttribute_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            try
            {
                removedAttributes.Add(new ExtendedAttribute() { Id = attributes[e.RowIndex].Id, Category = attributes[e.RowIndex].Category });
                Session[REMOVEDATTRIBUTES] = removedAttributes;
                attributes.RemoveAt(e.RowIndex);
                Session[ATTRIBUTES] = attributes;
                gvwExtendedAttribute.DataSource = attributes;
                gvwExtendedAttribute.DataBind();

                hdfEditStatus.Value = "true";
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwExtendedAttribute_RowUpdating(object sender, GridViewUpdateEventArgs e)
        {
            try
            {
                bool isUpdated = false;
                IsAddAttr = false;

                ExtendedAttribute attribute = attributes[e.RowIndex];

                GridViewRow row = gvwExtendedAttribute.Rows[e.RowIndex];

                string groupName = ((TextBox)row.Cells[1].Controls[0]).Text.Trim();
                if (string.IsNullOrEmpty(groupName))
                {
                    ShowMessage("标签分组名称不能为空！");
                    return;
                }
                isUpdated = !isUpdated ? attribute.DisplayName.Equals(groupName) : false;
                attribute.Group = groupName;

                string displayName = ((TextBox)row.Cells[2].Controls[0]).Text.Trim();
                if (string.IsNullOrEmpty(displayName))
                {
                    ShowMessage("标签名称不能为空！");
                    return;
                }
                isUpdated = !isUpdated ? attribute.DisplayName.Equals(displayName) : false;
                attribute.DisplayName = displayName;

                attribute = GetExtendedAttributeFormRow(e.RowIndex, attribute);
                gvwExtendedAttribute.EditIndex = -1;

                for (int i = 0; i < attributes.Count; i++)
                    attributes[i].Order = i + 1;

                Session[ATTRIBUTES] = attributes;
                gvwExtendedAttribute.DataSource = attributes;
                gvwExtendedAttribute.DataBind();

                hdfEditStatus.Value = isUpdated ? "true" : "";
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwExtendedAttribute_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
        {
            try
            {
                if (attributes.Count == 1)
                    panelGridView.Visible = false;

                if (IsAddAttr)
                    attributes.RemoveAt(attributes.Count - 1);
                IsAddAttr = false;

                gvwExtendedAttribute.EditIndex = -1;
                gvwExtendedAttribute.DataSource = attributes;
                gvwExtendedAttribute.DataBind();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        protected void gvwExtendedAttribute_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            try
            {
                if (e.Row.RowType.Equals(DataControlRowType.DataRow))
                {
                    ExtendedAttribute attribute = e.Row.DataItem as ExtendedAttribute;
                    ((TextBox)(e.Row.Cells[1].FindControl("txtGroup"))).Text = attribute.Group;
                    ((TextBox)(e.Row.Cells[2].FindControl("txtDisplayName"))).Text = attribute.DisplayName;
                    ((CheckBox)(e.Row.Cells[3].FindControl("chkEnableEmpty"))).Checked = attribute.IsRequired;
                    ((DropDownList)(e.Row.Cells[4].FindControl("dplDataType"))).SelectedValue = attribute.DataType;

                    DropDownList dplDataUnit = e.Row.Cells[5].FindControl("dplDataUnit") as DropDownList;
                    dplDataUnit.Items.Clear();
                    foreach (var o in units)
                    {
                        ListItem item = new ListItem(o.UnitName, o.Id.ToString());
                        dplDataUnit.Items.Add(item);
                    }
                    dplDataUnit.SelectedValue = attribute.DataUnit.Id.ToString();

                    ((DropDownList)(e.Row.Cells[6].FindControl("dplCategory"))).SelectedValue = attribute.AttributeCategory.ToString();
                }
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }
        #endregion

        protected void txtCategory_TextChanged(object sender, EventArgs e)
        {
            CategoryNameValidate();
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            try
            {
                InitAdd();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        /// <summary>
        /// 保存类别
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                if (!CategoryNameValidate())
                {
                    ShowMessage("类别名称不能重复！");
                    return;
                }

                selectedCategory.Name = txtCategory.Text.Trim();
                selectedCategory.Description = txtDescription.Text.Trim();
                selectedCategory.ParentId = int.Parse(dplParent.SelectedValue);

                //update attribute
                for (int i = 0; i < gvwExtendedAttribute.Rows.Count; i++)
                {
                    attributes[i].Order = i + 1;
                    attributes[i] = GetExtendedAttributeFormRow(i, attributes[i]);
                }

                //TO Sort attributes
                IList<int> lstOrderChanged = new List<int>();
                int order = 1;
                for (int i = 0; i < attributes.Count; i++)
                {
                    ExtendedAttribute attr = attributes[i];
                    if (lstOrderChanged.Contains(attr.Id))
                    {
                        continue;
                    }
                    attr.Order = order;
                    order++;

                    for (int j = i + 1; j < attributes.Count; j++)
                    {
                        if (attributes[j].Group.Equals(attr.Group))
                        {
                            attributes[j].Order = order;
                            order++;
                            lstOrderChanged.Add(attributes[j].Id);
                        }
                    }
                }

                productCategoryManager.Save(selectedCategory, attributes, removedAttributes);

                //write operation log
                string message = selectedCategory.Id.Equals(0) ? "新增类别：" : "变更类别：";
                message += txtCategory.Text;
                OperationLogType type = selectedCategory.Id.Equals(0) ? OperationLogType.Insert : OperationLogType.Update;
                WriteOperationLog(type, message);

                categories = productCategoryManager.GetByParentId(0);

                TreeNode root = new TreeNode("中材产品类别", "0");
                tvwCategory.Nodes.Clear();
                tvwCategory.Nodes.Add(root);

                AddChildNode(root, categories);
                tvwCategory.ExpandAll();

                attributes = attributes.OrderBy<ExtendedAttribute, int>(p => p.Order).ToList<ExtendedAttribute>();
                InitGridView();

                ShowMessage("保存成功。");

                //For Edit Status
                HasModify = false;
                hdfEditStatus.Value = string.Empty;
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                RedirectToErrorPage();
            }
        }

        /// <summary>
        /// 添加扩展属性
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void btnAddAttr_Click(object sender, EventArgs e)
        {
            try
            {
                IsAddAttr = true;

                if (!panelGridView.Visible)
                    panelGridView.Visible = true;

                ExtendedAttribute attribute = new ExtendedAttribute()
                {
                    Name = "Extend" + (attributes.Count + 1).ToString(),
                    Group = "标签分组名",
                    DisplayName = "标签名称" + (attributes.Count + 1).ToString(),
                    Order = attributes.Count + 1,
                    DataType = string.Empty,
                    AttributeCategory = App.Entity.ExtendedAttributeCategory.Common,
                    DataUnit = new ProductUnit() { Id = 1 }
                };
                attributes.Add(attribute);
                Session[ATTRIBUTES] = attributes;
                gvwExtendedAttribute.DataSource = attributes;
                gvwExtendedAttribute.DataBind();
                //GridViewEditEventArgs evt = new GridViewEditEventArgs(attributes.Count - 1);
                //gvwExtendedAttribute_RowEditing(sender, evt);

                ScriptManager.RegisterClientScriptBlock(this.Page, typeof(CategoryMgt), "", "window.location.href='#gridviewbottom';", true);
            }
            catch (Exception ex)
            {
                Log.Error(ex);
            }
        }

        /// <summary>
        /// 删除类别
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                if (HasProduct())
                {
                    ShowMessage("类别已经在使用中，不能删除。若要删除，请先清除此类别下得所有产品。");
                    return;
                }

                productCategoryManager.Delete(selectedCategory, attributes);

                WriteOperationLog(OperationLogType.Delete, "删除类别：" + txtCategory.Text);

                selectedCategory = new ProductCategory();
                Session[SELECTECATEGORY] = selectedCategory;

                attributes = new List<ExtendedAttribute>();
                Session[ATTRIBUTES] = attributes;

                categories = categoryManager.GetCategoryByParentId(0);

                TreeNode node = tvwCategory.SelectedNode;
                node.Parent.ChildNodes.Remove(node);

                InitAdd();
                
                ShowMessage("删除成功。");
            }
            catch (Exception ex)
            {
                Log.Error(ex);
                GoToErrorPage();
            }
        }

        /// <summary>
        /// 取消扩展属性编辑
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected void btnCancel_Click(object sender, EventArgs e)
        {
            try
            {
                if (chkCookie.Checked)
                    Response.Cookies["Category-Help-Show"].Value = "false";
                else
                    Response.Cookies["Category-Help-Show"].Value = "true";
                modalHelp.Hide();
            }
            catch (Exception ex)
            {
                Log.Error(ex);
            }
        }
        #endregion

        #region Private Methods
        private void InitTreeview()
        {
            TreeNode root = new TreeNode("中材产品类别", "0");
            tvwCategory.Nodes.Clear();
            tvwCategory.Nodes.Add(root);

            AddChildNode(root, categories);
            tvwCategory.ExpandAll();

            InitDplParentCategory();
        }

        private void InitDplParentCategory()
        {
            dplParent.Items.Clear();
            ListItem rootItem = new ListItem("中材产品类别", "0");
            dplParent.Items.Add(rootItem);
            foreach (var o in categories)
            {
                ListItem item = new ListItem("|—" + o.Name, o.Id.ToString());
                dplParent.Items.Add(item);
            }
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

        private void InitGridView()
        {
            panelGridView.Visible = attributes.Count > 0;
            Session[ATTRIBUTES] = attributes;
            gvwExtendedAttribute.DataSource = attributes;
            gvwExtendedAttribute.DataBind();
        }

        private ExtendedAttribute GetExtendedAttributeFormRow(int index, ExtendedAttribute attribute)
        {
            bool isUpdated;
            return GetExtendedAttributeFormRow(index, attribute, out isUpdated);
        }

        private ExtendedAttribute GetExtendedAttributeFormRow(int index, ExtendedAttribute attribute, out bool isUpdated)
        {
            isUpdated = false;
            GridViewRow row = gvwExtendedAttribute.Rows[index];

            TextBox txtGroup = row.Cells[1].FindControl("txtGroup") as TextBox;
            isUpdated = !isUpdated ? !attribute.Group.Equals(txtGroup.Text) : true;
            attribute.Group = txtGroup.Text;

            TextBox txtDisplayName = row.Cells[2].FindControl("txtDisplayName") as TextBox;
            isUpdated = !isUpdated ? !attribute.DisplayName.Equals(txtDisplayName.Text) : true;
            attribute.DisplayName = txtDisplayName.Text;

            CheckBox checkBox = row.Cells[3].FindControl("chkEnableEmpty") as CheckBox;
            isUpdated = !isUpdated ? !attribute.IsRequired.Equals(checkBox.Checked) : true;
            attribute.IsRequired = checkBox.Checked;

            DropDownList dplDataType = row.Cells[4].FindControl("dplDataType") as DropDownList;
            isUpdated = !isUpdated ? !attribute.DataType.Equals(dplDataType.SelectedValue) : true;
            attribute.DataType = dplDataType.SelectedValue;

            DropDownList dplDataUnit = row.Cells[5].FindControl("dplDataUnit") as DropDownList;
            isUpdated = !isUpdated ? !attribute.DataUnit.Id.Equals(int.Parse(dplDataUnit.SelectedValue)) : true;
            attribute.DataUnit = new ProductUnit() { Id = int.Parse(dplDataUnit.SelectedValue) };

            DropDownList dplCategory = row.Cells[6].FindControl("dplCategory") as DropDownList;
            ExtendedAttributeCategory attrCategory = (MyRabbit.Data.Entity.ExtendedAttributeCategory)Enum
                .Parse(typeof(MyRabbit.Data.Entity.ExtendedAttributeCategory), dplCategory.SelectedValue, false);
            isUpdated = !isUpdated ? !attribute.AttributeCategory.Equals(attrCategory) : true;
            attribute.AttributeCategory = attrCategory;

            return attribute;
        }

        private bool IsAttributeUpdated()
        {
            if (!HasModify) return false;

            ProductCategory category = Session[ORIGINALSELECTECATEGORY] as ProductCategory;
            if (!int.Parse(dplParent.SelectedValue).Equals(category.ParentId))
                return true;
            if (!txtCategory.Text.Equals(category.Name))
                return true;
            if (!txtDescription.Text.Equals(category.Description))
                return true;

            IList<ExtendedAttribute> originalAttributes = Session[ORIGINALATTRIBUTES] as List<ExtendedAttribute>;

            if (originalAttributes.Count != gvwExtendedAttribute.Rows.Count)
                return true;

            for (int i = 0; i < gvwExtendedAttribute.Rows.Count; i++)
            {
                GridViewRow row = gvwExtendedAttribute.Rows[i];
                ExtendedAttribute attribute = originalAttributes[i];

                if (!attribute.Order.Equals(int.Parse(row.Cells[0].Text))) return true;

                if (!attribute.Name.Equals(row.Cells[1].Text)) return true;

                if (!attribute.DisplayName.Equals(row.Cells[2].Text)) return true;

                CheckBox checkBox = row.Cells[3].FindControl("chkEnableEmpty") as CheckBox;
                if (!attribute.IsRequired.Equals(checkBox.Checked)) return true;

                DropDownList dplDataType = row.Cells[4].FindControl("dplDataType") as DropDownList;
                if (!attribute.DataType.Equals(dplDataType.SelectedValue)) return true;

                DropDownList dplDataUnit = row.Cells[5].FindControl("dplDataUnit") as DropDownList;
                if (!attribute.DataUnit.Id.Equals(int.Parse(dplDataUnit.SelectedValue))) return true;

                DropDownList dplCategory = row.Cells[6].FindControl("dplCategory") as DropDownList;
                if (!attribute.AttributeCategory.Equals((MyRabbit.Data.Entity.ExtendedAttributeCategory)Enum
                    .Parse(typeof(MyRabbit.Data.Entity.ExtendedAttributeCategory), dplCategory.SelectedValue, false)))
                    return true;
            }

            return false;
        }

        private bool HasProduct()
        {
            int categoryId = int.TryParse(tvwCategory.SelectedNode.Value, out categoryId) ? categoryId : 0;
            bool isParent = tvwCategory.SelectedNode.Parent.Value.Equals("0");
            return productManager.HasProducts(categoryId, isParent);
        }

        private bool CategoryNameValidate()
        {
            //if (!selectedCategory.Id.Equals(0))
            //{
            //    lblCategoryChkMsg.Visible = false;
            //    imgCategoryChkMsg.Visible = false;
            //    return true;
            //}

            int parentId = int.TryParse(dplParent.SelectedValue, out parentId) ? parentId : 0;
            IList<ProductCategory> lst = categoryManager.GetCategoryByParentId(parentId);
            txtCategory.Text = txtCategory.Text.Trim();
            bool has = lst.Where<ProductCategory>(p => p.Name.Equals(txtCategory.Text) && !p.Id.Equals(selectedCategory.Id))
                .Count<ProductCategory>() > 0;
            
            if (has)
            {
                lblCategoryChkMsg.Visible = true;
                imgCategoryChkMsg.Visible = false;
                lblCategoryChkMsg.Text = "类别名称不能重复！";
                txtCategory.Focus();
                return false;
            }
            else
            {
                lblCategoryChkMsg.Visible = false;
                imgCategoryChkMsg.Visible = true;
                txtDescription.Focus();
                return true;
            }
        }

        private void InitAdd()
        {
            lblTitle.Text = "产品类别管理 > 新增类别";
            txtCategory.Text = string.Empty;
            //txtCategory.Enabled = true;
            txtDescription.Text = string.Empty;

            selectedCategory = new ProductCategory();
            Session[SELECTECATEGORY] = selectedCategory;
            InitDplParentCategory();

            attributes = new List<ExtendedAttribute>();
            Session[ATTRIBUTES] = attributes;
            InitGridView();

            HasModify = true;
        }
        #endregion
    }
}