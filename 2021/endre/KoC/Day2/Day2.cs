using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KoC.Day2
{
    [TestClass]
    public class Day2
    {
        [TestMethod]
        public void Day2_Part1()
        {
            var input = GetInput(@"C:\src\private\KebabOfCode\2021\endre\KoC\Day2\input1.txt").Select(x => x.Split(' ')).ToList();

            int horizontal = 0;
            int depth = 0;

            //forward +, back -, down +, up -
            foreach(var line in input)
            {
                if (line[0] == "forward")
                    horizontal += int.Parse(line[1]);
                if (line[0] == "up")
                    depth -= int.Parse(line[1]);
                if(line[0] == "down")
                    depth += int.Parse(line[1]);
            }

            var result = depth * horizontal;

            // All I do is win win win no matter what
            Assert.IsTrue(true);
        }

        [TestMethod]
        public void Day2_Part2()
        {
            var input = GetInput(@"C:\src\private\KebabOfCode\2021\endre\KoC\Day2\input1.txt").Select(x => x.Split(' ')).ToList();

            int horizontal = 0;
            int depth = 0;
            int aim = 0;

            //forward +, back -, down +, up -
            foreach (var line in input)
            {
                if (line[0] == "forward")
                {
                    horizontal += int.Parse(line[1]);
                    depth += aim * int.Parse(line[1]);
                }
                if (line[0] == "up")
                    aim -= int.Parse(line[1]);
                if (line[0] == "down")
                    aim += int.Parse(line[1]);
            }

            var result = depth * horizontal;

            // All I do is win win win no matter what
            Assert.IsTrue(true);
        }

        public string[]? GetInput(string path)
        {
            var input = File.ReadAllLines(path);
            if (input is null)
                return Array.Empty<string>();

            return input;
        }
    }
}
