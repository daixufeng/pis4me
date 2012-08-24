using System;
using System.Collections;
using System.Collections.Generic;
using System.Xml.Linq;

namespace MyMono
{
	public class Util
	{
		public Util ()
		{

		}

		public static void Output(){
			IList<String> list = new List<string>(){
				"A",
				"B",
				"C"
			};
			foreach(var o in list)
				Console.WriteLine(o);
		}
	}
}

