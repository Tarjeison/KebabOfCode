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
  for noun in range(0,100,1):
    for verb in range(0,100,1):
      data = clean_data()
      for count in range (0,len(data), 1):
        data[count]=int(data[count])
      data[1]=noun
      data[2]=verb
      for number in range (0,len(data)-1, 4):
        print("noun is ", noun, "\n")
        print("verb is ", verb, "\n")
        print (number)
        operation = data[number]
        print("operation ", operation)
        result = calculate_result(operation,data[data[number+1]],data[data[number+2]])
        if result == -1 and data[0] == 19690720:
          print ("the correct noun is: ", noun,"\n")
          print ("the correct verb is: ", verb,"\n")
          break
        elif result == -1:
          print ("Sorry, wrong input, restarting")
        else:
          data[data[number+3]] = result
  print (data)

main()