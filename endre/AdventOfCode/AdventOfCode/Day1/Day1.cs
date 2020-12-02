using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AdventOfCode.Day1
{
    [TestFixture]
    class Day1
    {
        public void Get2020ByTwoBinomialsFromList()
        {
            var wantedAnswer = 2020;
            string[] input = System.IO.File.ReadAllLines(@"C:\\src\\local\\CodeWars\\Kata\\CodeWars\\Kata1\\AdventOfCode\\Day1\\input.txt");
            var int_input = input.Select(x => int.Parse(x)).ToList();
            var resultList = new List<int>();

            for (int i = 0; i < int_input.Count; i++)
            {
                var currentValue = int_input[i];
                for (int j = i + 1; j < int_input.Count; j++)
                {
                    for (int k = j + 1; k < int_input.Count; k++)
                    {
                        var comparatorValueOne = int_input[j];
                        var comparatorValueTwo = int_input[k];
                        if (currentValue + comparatorValueOne + comparatorValueTwo == wantedAnswer)
                        {
                            resultList.Add(comparatorValueOne);
                            resultList.Add(comparatorValueTwo);
                            resultList.Add(currentValue);
                        }
                    }
                }
            }

            var result = resultList[0] * resultList[1] * resultList[2];
        }
    }
}
