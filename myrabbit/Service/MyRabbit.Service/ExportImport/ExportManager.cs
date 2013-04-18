using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OfficeOpenXml.Style;
using OfficeOpenXml;
using System.Drawing;

namespace MyRabbit.Service.ExportImport
{
    public class ExportManager : IExportManager
    {
        public void ExportUserToXlsx(System.IO.Stream stream, IList<Entity.User> users)
        {
            if (stream == null)
                throw new ArgumentNullException("stream");

            // ok, we can run the real code of the sample now
            using (var xlPackage = new ExcelPackage(stream))
            {
                // uncomment this line if you want the XML written out to the outputDir
                //xlPackage.DebugMode = true; 

                // get handle to the existing worksheet
                var worksheet = xlPackage.Workbook.Worksheets.Add("Products");
                //Create Headers and format them 
                var properties = new string[]
                {
                    "NickName",
                    "LoginName",
                    "Email"
                };
                for (int i = 0; i < properties.Length; i++)
                {
                    worksheet.Cells[1, i + 1].Value = properties[i];
                    worksheet.Cells[1, i + 1].Style.Fill.PatternType = ExcelFillStyle.Solid;
                    worksheet.Cells[1, i + 1].Style.Fill.BackgroundColor.SetColor(Color.FromArgb(184, 204, 228));
                    worksheet.Cells[1, i + 1].Style.Font.Bold = true;
                }


                int row = 2;
                foreach (var u in users)
                {
                    int col = 1;

                    worksheet.Cells[row, col].Value = u.NickName;
                    col++;

                    worksheet.Cells[row, col].Value = u.LoginName;
                    col++;

                    worksheet.Cells[row, col].Value = u.Email;
                    col++;

                    row++;
                }


                // we had better add some document properties to the spreadsheet 

                // set some core property values
                //xlPackage.Workbook.Properties.Title = string.Format("{0} users", _storeInformationSettings.StoreName);
                //xlPackage.Workbook.Properties.Author = _storeInformationSettings.StoreName;
                //xlPackage.Workbook.Properties.Subject = string.Format("{0} products", _storeInformationSettings.StoreName);
                //xlPackage.Workbook.Properties.Keywords = string.Format("{0} products", _storeInformationSettings.StoreName);
                xlPackage.Workbook.Properties.Category = "Users";
                //xlPackage.Workbook.Properties.Comments = string.Format("{0} products", _storeInformationSettings.StoreName);

                // set some extended property values
                //xlPackage.Workbook.Properties.Company = _storeInformationSettings.StoreName;
                //xlPackage.Workbook.Properties.HyperlinkBase = new Uri(_storeInformationSettings.StoreUrl);

                // save the new spreadsheet
                xlPackage.Save();
            }
        }
    }
}
