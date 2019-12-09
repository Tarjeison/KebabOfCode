#! /usr/bin/env python

import os

f = file('one.txt')
result=0

for line in f:
    before=int(line)
    divided=int(float(before)/3)-2
    result += divided
print (result)
