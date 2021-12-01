import math

def fuel_required(mass):
  fuel=0
  remaining_mass=mass
  while (math.floor(int(remaining_mass)/3) -2) >0:
    print (math.floor(int(remaining_mass)/3) -2)
    fuel += (math.floor(int(remaining_mass)/3) -2)
    remaining_mass = (math.floor(int(remaining_mass)/3) -2)
  return fuel

def main():
  counter=0
  total_fuel=0
  f = open("input.txt", "r")
  for line in f:
    total_fuel += (fuel_required(line))
    print(line)
    counter+=1
  print (counter)
  print("Total fuel required is", total_fuel)
main()