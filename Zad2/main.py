import pandas as pd
import matplotlib.pyplot as plt

def read_file(filename,sheetname,columns):
    return pd.read_excel(filename, sheet_name=sheetname, usecols=columns)


if __name__ == '__main__':
    train_dataset = []
    train_dataset_meas = []
    train_dataset_ref = []
    for i in range(0,12,1):
        train_dataset.append(read_file('pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i + 1) + '.xlsx', "measurement", "E:H"))
        train_dataset[i] = train_dataset[i].dropna()
        train_dataset_ref.append(train_dataset[i][['reference x', 'reference y']])
        train_dataset_meas.append(train_dataset[i][['measurement x','measurement y']])

    compare_dataset = read_file('pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx','pomiar',"E:H")
    compare_dataset = compare_dataset.dropna()
    compare_dataset_ref = compare_dataset[['reference x', 'reference y']]
    compare_dataset_meas = compare_dataset[['measurement x','measurement y']]