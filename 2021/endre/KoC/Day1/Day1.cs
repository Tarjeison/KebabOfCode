using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace KoC.Day1
{
    [TestClass]
    public class Day1
    {
        [TestMethod]
        public void Part1()
        {
            var input = GetInput(@"C:\src\private\KebabOfCode\2021\endre\KoC\Day1\input1.txt");
            var transformedInput = input is null
                ? new List<int>()
                : input.Select(x => int.Parse(x)).ToList();

            int prev = 0;
            int counter = 0;
            foreach (var item in transformedInput)
            {
                if (prev == 0)
                {
                    prev = item;
                    continue;
                }

                if (item > prev)
                {
                    counter++;
                }

                prev = item;
            }
            // Tøyseassert for debug spot
            Assert.AreEqual(counter, prev);
        }

        [TestMethod]
        public void Part2()
        {
            var input = GetInput(@"C:\src\private\KebabOfCode\2021\endre\KoC\Day1\input2.txt");
            var transformedInput = input is null
                ? new List<int>()
                : input.Select(x => int.Parse(x)).ToList();

            // A = 0-1-2, B = 1-2-3, C = 2-3-4, A = 3-4-5 osv
            int counter = 0;
            int prevSum = 0;

            for (int i = 0; i < transformedInput.Count - 2; i++)
            {
                var item1 = transformedInput[i];
                var item2 = transformedInput[i+1];
                var item3 = transformedInput[i+2];
                var sum = item1 + item2 + item3;

                if (prevSum == 0)
                {
                    prevSum = sum;
                    continue;
                }

                if (sum > prevSum)
                {
                    counter++;
                }
                prevSum = sum;
            }
            // Tøyseassert for debug spot
            Assert.IsTrue(prevSum > 0);
        }

        private string[]? GetInput(string absPath)
        {
            var path = Path.GetFullPath(absPath);
            var input = File.ReadAllLines(path);
            if (input is null)
                return null;

            return input;
        }
    }
}