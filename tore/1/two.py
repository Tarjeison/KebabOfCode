#! /usr/bin/env python

import os

f = file('one.txt')

def calculate_fuel(fuel):
    divided = int(float(fuel)/3)-2
    if divided <= 0:
       return 0
    else:
       divided += calculate_fuel(divided)
    return divided
result=0

for line in f:
    before=int(line)
    divided = calculate_fuel(before)
    
    result += divided
    
    
    


print (result)
