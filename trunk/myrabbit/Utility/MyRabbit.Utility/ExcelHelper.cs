using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.IO;

namespace MyRabbit.Utility
{
    public class ExcelHelper
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="tableInfo"></param>
        /// <returns></returns>
        public static bool CreateTemplate(IDictionary<string, IList<string>> tableInfo, string fileName)
        {
            return true;
            //object missing = System.Reflection.Missing.Value;

            ////http://social.msdn.microsoft.com/forums/en-US/Vsexpressvb/thread/4565361b-aeff-4d01-af92-d21a6eb631e3/
            //System.Globalization.CultureInfo oldCI = System.Threading.Thread.CurrentThread.CurrentCulture;

            //Microsoft.Office.Interop.Excel.Application xlsApp = null;
            //try
            //{
            //    xlsApp = new Microsoft.Office.Interop.Excel.Application();
            //    if (xlsApp == null)
            //        throw new Exception("can not create excel object,maybe you has not install excel 97-2003");

            //    //2. delete file
            //    if (File.Exists(fileName))
            //    {
            //        //delete file
            //        FileInfo fileInfo = new FileInfo(fileName);
            //        fileInfo.Delete();
            //        //File.Delete(fileName);
            //    }

            //    Microsoft.Office.Interop.Excel.Workbook workbook = null;
            //    workbook = xlsApp.Workbooks.Add(Microsoft.Office.Interop.Excel.XlWBATemplate.xlWBATWorksheet);

            //    //create excel obj
            //    int i = 0;
            //    foreach (var tblName in tableInfo.Keys)
            //    {
            //        //create sheet
            //        Microsoft.Office.Interop.Excel.Worksheet worksheet = (Microsoft.Office.Interop.Excel.Worksheet)workbook.Worksheets[i + 1];

            //        worksheet.Name = tblName;

            //        int colIndex = 1;
            //        IList<string> cols = tableInfo[tblName];
            //        foreach (var col in tableInfo[tblName])
            //        {
            //            //create cols in sheet
            //            worksheet.Cells[1, colIndex] = col;
            //            colIndex++;
            //        }

            //        //worksheet.SaveAs(fileName, missing, missing, missing,missing, missing, missing, missing, missing, missing);
            //        i++;
            //    }

            //    workbook.SaveAs(fileName, missing, missing, missing, missing, missing, Microsoft.Office.Interop.Excel.XlSaveAsAccessMode.xlShared, missing, missing, missing, missing, missing);

            //    //7.release
            //    workbook.Close(missing, missing, missing);
            //    //System.Runtime.InteropServices.Marshal.ReleaseComObject(worksheet);
            //    System.Runtime.InteropServices.Marshal.ReleaseComObject(workbook);
            //    //worksheet = null;
            //    workbook = null;

            //    return true;
            //}
            //catch
            //{
            //    throw;
            //}
            //finally
            //{
            //    if (xlsApp != null)
            //    {
            //        //release
            //        xlsApp.Quit();
            //        System.Runtime.InteropServices.Marshal.ReleaseComObject(xlsApp);
            //        xlsApp = null;

            //    }

            //    System.Threading.Thread.CurrentThread.CurrentCulture = oldCI;
            //}
        }
    }
}
