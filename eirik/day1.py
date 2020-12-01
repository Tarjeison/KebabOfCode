
def get_product_of_two_entries_that_equal_sum(expence_report, sum_to=2020):
    expence_report.sort()
    for i, expense_1 in enumerate(expence_report):
        for expense_2 in expence_report[i:]:
            if expense_1 + expense_2 == sum_to:
                return expense_1 * expense_2


with open('input1.txt', 'r') as file:
    expense_report = [int(line) for line in file]
print(get_product_of_two_entries_that_equal_sum(expense_report))


def get_product_of_three_entries_that_equal_sum(expence_report, sum_to=2020):
    expence_report.sort()
    for i, expense_1 in enumerate(expence_report):
        for j, expense_2 in enumerate(expence_report[i:]):
            for expense_3 in expence_report[j:]:
                if expense_1 + expense_2 + expense_3 == sum_to:
                    return expense_1 * expense_2 * expense_3


print(get_product_of_three_entries_that_equal_sum(expense_report))

