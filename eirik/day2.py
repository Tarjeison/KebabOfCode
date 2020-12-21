
def is_valid_password_old(password, char, min_count, max_count):
    return min_count <= password.count(char) <= max_count

def is_valid_password_new(password, char, pos_1, pos_2):
    return (password[pos_1-1] == char) ^ (password[pos_2-1] == char)

with open('day2.txt', 'r') as file:
    number_of_valid_passwords_old = 0
    number_of_valid_passwords_new = 0

    for line in file:
        line = line.rstrip('\n')
        min_count, rest = line.split("-")
        max_count, rest = rest.split(" ", maxsplit=1)
        char, password = rest.split(": ")

        if is_valid_password_old(password, char, int(min_count), int(max_count)):
            number_of_valid_passwords_old += 1
        if is_valid_password_new(password, char, int(min_count), int(max_count)):
            number_of_valid_passwords_new += 1

    print(number_of_valid_passwords_old)
    print(number_of_valid_passwords_new)
