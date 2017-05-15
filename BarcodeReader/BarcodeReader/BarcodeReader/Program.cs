using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using ZXing;

namespace BarcodeReader
{
    class Program
    {
        static void Main(string[] args)
        {
            var barcodeReader = new ZXing.BarcodeReader();


            // create an in memory bitmap
            var barcodeBitmap = new Bitmap(BarcodeReader.Properties.Resources.IMG_1472);
            barcodeBitmap = new Bitmap(barcodeBitmap, new Size(barcodeBitmap.Width / 50, barcodeBitmap.Height / 50));
            barcodeBitmap.Save(@"C:\Users\Lukas\Desktop\test.bmp");

            // decode the barcode from the in memory bitmap
            barcodeReader.Options.TryHarder = true;
            barcodeReader.Options.PureBarcode = false;
            barcodeReader.AutoRotate = true;

            
            var barcodeResult = barcodeReader.Decode(barcodeBitmap);

            // output results to console
            Console.WriteLine($"Decoded barcode text: {barcodeResult?.Text}");
            Console.WriteLine($"Barcode format: {barcodeResult?.BarcodeFormat}");
            Console.ReadLine();
        }
        public static Bitmap MedianFilter(Bitmap Image, int Size)
        {
            Bitmap NewBitmap = new Bitmap(Image,new Size(Image.Width / 4, Image.Height / 4));


            Random TempRandom = new Random();
            int ApetureMin = -(Size / 2);
            int ApetureMax = (Size / 2);
            for (int x = 0; x < NewBitmap.Width; ++x)
            {
                for (int y = 0; y < NewBitmap.Height; ++y)
                {
                    List<int> RValues = new List<int>();
                    List<int> GValues = new List<int>();
                    List<int> BValues = new List<int>();
                    for (int x2 = ApetureMin; x2 < ApetureMax; ++x2)
                    {
                        int TempX = x + x2;
                        if (TempX >= 0 && TempX < NewBitmap.Width)
                        {
                            for (int y2 = ApetureMin; y2 < ApetureMax; ++y2)
                            {
                                int TempY = y + y2;
                                if (TempY >= 0 && TempY < NewBitmap.Height)
                                {
                                    Color TempColor = NewBitmap.GetPixel(TempX, TempY);
                                    RValues.Add(TempColor.R);
                                    GValues.Add(TempColor.G);
                                    BValues.Add(TempColor.B);
                                }
                            }
                        }
                    }
                    RValues.Sort();
                    GValues.Sort();
                    BValues.Sort();
                    Color MedianPixel = Color.FromArgb(RValues[RValues.Count / 2],
                        GValues[GValues.Count / 2],
                        BValues[BValues.Count / 2]);
                    NewBitmap.SetPixel(x, y, MedianPixel);
                }
            }
            return NewBitmap;
        }
    }

}

