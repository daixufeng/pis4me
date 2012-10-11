using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MyRabbit.Utility
{
    public class EnumHelper
    {
        public static string EnumToString<T>(T o)
        {
            Type objType = o.GetType();
            string s = o.ToString();
            ShowNameAttribute[] objStringAttributeAttribute = (ShowNameAttribute[])objType.GetField(s).GetCustomAttributes(typeof(ShowNameAttribute), false);
            if (objStringAttributeAttribute != null && objStringAttributeAttribute.Length == 1)
            {
                return objStringAttributeAttribute[0].StringName;
            }
            return s;
        }

        public static Dictionary<int, string> GetEnumValueShownameMap<EnumType>()
        {
            Type enmType = typeof(EnumType);
            Dictionary<int, string> dicEnumValueShowName = new Dictionary<int, string>();
            foreach (string s in Enum.GetNames(enmType))
            {
                dicEnumValueShowName.Add(int.Parse(Enum.Format(enmType, Enum.Parse(enmType, s), "d")), EnumToString<EnumType>((EnumType)Enum.Parse(enmType, s)));
            }
            return dicEnumValueShowName;
        }
    }
}
