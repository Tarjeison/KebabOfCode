using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AdventOfCode.Day2
{
    class Day2
    {
        [Test]
        public void CheckPasswordIntegrityFromInputFile()
        {
            string[] rawInput = System.IO.File.ReadAllLines(@"C:\\src\\local\\CodeWars\\Kata\\CodeWars\\Kata1\\AdventOfCode\\Day2\\input.txt");
            var input = rawInput.Select(x => x).ToList();
            int result = 0;
            foreach (string s in input)
            {
                if (IsThisPasswordAccepted(s))
                    result++;
            }

            var debugpoint = 0;
        }

        public bool IsThisPasswordAccepted(string line)
        {
            var lineSplit = line.Split(":");
            var policy = lineSplit[0];
            var password = lineSplit[1].Replace(" ", "");
            var letter = policy.Split(" ")[1].ToCharArray();
            var lowConstraint = int.Parse(policy.Split(" ")[0].Split("-")[0]);
            var highConstraint = int.Parse(policy.Split(" ")[0].Split("-")[1]);


            var positionOfPolicyLetter = password.IndexOf(letter.First()) + 1;
            var listOfPositions = new List<int>();
            while (positionOfPolicyLetter != 0)
            {
                listOfPositions.Add(positionOfPolicyLetter);
                positionOfPolicyLetter = password.IndexOf(letter.First(), positionOfPolicyLetter) + 1;
            }

            if (listOfPositions.Any(x => x == lowConstraint) && listOfPositions.Any(x => x == highConstraint))
                return false;
            else if (listOfPositions.Any(x => x == lowConstraint))
                return true;
            else if (listOfPositions.Any(x => x == highConstraint))
                return true;

            return false;
        }
    }
}
