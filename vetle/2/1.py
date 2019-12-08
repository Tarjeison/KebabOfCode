def clean_data():
  f = open("input.txt", "r")
  for line in f.readlines():
    fname = line.rstrip().split(",")
  return fname

def calculate_result(operation, first, second):
  if operation == 99:
    return -1
  elif operation == 1:
    return first + second
  elif operation == 2:
    return first*second


def main():
  data = clean_data()
  for count in range (0,len(data), 1):
  	data[count]=int(data[count])
  for number in range (0,len(data), 4):
  	operation = data[number]
  	result = calculate_result(operation,data[data[number+1]],data[data[number+2]])
  	if result == -1:
  	  break
  	else:
  	  data[data[number+3]] = result
  print (data)

main()