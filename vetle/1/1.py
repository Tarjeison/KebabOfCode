import math

def fuel_required(mass):
  return fuel (math.floor(int(remaining_mass)/3) -2)

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