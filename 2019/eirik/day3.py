
def traverse_slope(right, down, file_name):
    x_position= 0
    tree_counter = 0
    with open(file_name, 'r') as file:
        for y_position, line in enumerate(file):
            if y_position % down == 0:
                line = line.rstrip('\n')
                if line[x_position % len(line)] == "#":
                    tree_counter += 1
                x_position += right
    return tree_counter


print(traverse_slope(right=3,down=1, file_name='day3.txt'))

print(
    traverse_slope(right=1, down=1, file_name='test_day3.txt'),
    traverse_slope(right=3, down=1, file_name='test_day3.txt'),
    traverse_slope(right=5, down=1, file_name='test_day3.txt'),
    traverse_slope(right=7, down=1, file_name='test_day3.txt'),
    traverse_slope(right=1, down=2, file_name='test_day3.txt'),
)
print(
    traverse_slope(right=1, down=1, file_name='day3.txt')*
    traverse_slope(right=3, down=1, file_name='day3.txt')*
    traverse_slope(right=5, down=1, file_name='day3.txt')*
    traverse_slope(right=7, down=1, file_name='day3.txt')*
    traverse_slope(right=1, down=2, file_name='day3.txt')
)

