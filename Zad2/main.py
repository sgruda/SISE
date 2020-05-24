import pandas as pd
import matplotlib.pyplot as plt
from tensorflow import keras
from tensorflow.keras import layers
import seaborn as sns

import tensorflow_docs as tfdocs
import tensorflow_docs.plots
import tensorflow_docs.modeling


def read_file(filename,sheetname,columns):
    return pd.read_excel(filename, sheet_name=sheetname, usecols=columns)

def build_model():
  model = keras.Sequential([
    layers.Dense(8, activation='relu', input_shape=[len(train_dataset_meas[0].keys())]),
    layers.Dense(16, activation='relu'),
    layers.Dense(32, activation='relu'),
    layers.Dense(2)
  ])

  optimizer = keras.optimizers.RMSprop(0.001)

  model.compile(loss='mse',
                optimizer=optimizer,
                metrics=['mae', 'mse'])
  return model


if __name__ == '__main__':
    train_dataset = []
    train_dataset_meas = []
    train_dataset_ref = []
    for i in range(0,12,1):
        train_dataset.append(read_file('pozyxAPI_dane_pomiarowe/pozyxAPI_only_localization_measurement' + str(i + 1) + '.xlsx', "measurement", "E:H"))
        train_dataset[i] = train_dataset[i].dropna()
        train_dataset_ref.append(train_dataset[i][['reference x', 'reference y']])
        train_dataset_meas.append(train_dataset[i][['measurement x','measurement y']])

    merged_train_datasets_ref = pd.concat(train_dataset_ref,ignore_index=True)
    merged_train_datasets_meas = pd.concat(train_dataset_meas,ignore_index=True)

    compare_dataset = read_file('pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx','pomiar',"E:H")
    compare_dataset = compare_dataset.dropna()
    compare_dataset_ref = compare_dataset[['reference x', 'reference y']]
    compare_dataset_meas = compare_dataset[['measurement x','measurement y']]

    model = build_model()
    #Use the .summary method to print a simple description of the model
    model.summary()

    EPOCHS = 1000

    history = model.fit(merged_train_datasets_meas, merged_train_datasets_ref, epochs=EPOCHS, validation_split=0.2, verbose=0,callbacks=[tfdocs.modeling.EpochDots()])


    solution_predictions = model.predict(compare_dataset_meas)
    solution_predictions_df = pd.DataFrame(solution_predictions,columns=['x','y'])

    sns.relplot(x='x', y='y', data=solution_predictions_df)
    sns.relplot(x='reference x', y='reference y', data=compare_dataset_ref)
    sns.relplot(x='measurement x', y='measurement y', data=compare_dataset_meas)
    plt.show()

    solution_predictions_df.to_clipboard(excel=True)

