package com.bsuir.lagunovskaya.ui;

import com.bsuir.lagunovskaya.generators.AbstractGenerator;
import com.bsuir.lagunovskaya.utils.ExcelWriterUtils;
import com.bsuir.lagunovskaya.utils.GeneratorTypes;
import com.bsuir.lagunovskaya.utils.GeneratorUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MyFrame extends JFrame {

    private String[] generationTypes = {
            GeneratorTypes.GENERATOR_1,
            GeneratorTypes.GENERATOR_2,
            GeneratorTypes.GENERATOR_3,
            GeneratorTypes.GENERATOR_4,
            GeneratorTypes.GENERATOR_5,
            GeneratorTypes.GENERATOR_6
    };
    private Integer[] amountOfGeneratedSignals = {
            50,
            100,
            150
    };

    private DefaultListModel<Double> firstArrayListModel = new DefaultListModel<>();
    private DefaultListModel<Double> secondArrayListModel = new DefaultListModel<>();


    public MyFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        JPanel toolPanel = new JPanel(new FlowLayout()); // верхняя панель инструментов для выпадающих списков и тп
        JLabel selectGenerationTypeLabelFirstArr = new JLabel("Generation type for 1st arrays: "); // кусок текста
        toolPanel.add(selectGenerationTypeLabelFirstArr); // добавляем лейбел на выделенную панель инструментов
        final JComboBox<String> selectGenerationTypeFirstArrayComboBox = new JComboBox<>(generationTypes); // выпадающий список для выбора типа генерации для массива 1
        toolPanel.add(selectGenerationTypeFirstArrayComboBox);// добавляем выпадающий список на панель инструментов для массива один
        JLabel selectGenerationTypeLabelSecondArr = new JLabel("Generation type for 2nd array: ");// лейбел для выбора типа генерации для массива 2
        toolPanel.add(selectGenerationTypeLabelSecondArr);// добавляем созданный лейбел на панель инструментов
        final JComboBox<String> selectGenerationTypeSecondArrayComboBox = new JComboBox<>(generationTypes);// создаём выпадающий спиоок для выбора типа генерации для массив 2
        toolPanel.add(selectGenerationTypeSecondArrayComboBox);// добавляем выпадающий список на панель инструментов
        JLabel selectAmountOfGeneratedSignalsLabel = new JLabel("Amount of generated signals");
        toolPanel.add(selectAmountOfGeneratedSignalsLabel);
        final JComboBox<Integer> selectAmountOfGeneratedSignalComboBox = new JComboBox<>(amountOfGeneratedSignals);
        toolPanel.add(selectAmountOfGeneratedSignalComboBox);
        add(BorderLayout.NORTH, toolPanel);

        JPanel westPanel = new JPanel(new FlowLayout());
        add(BorderLayout.WEST, westPanel);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));//сеточно расположение 1 строка 2 столбца
        JList firstArrayList = new JList(firstArrayListModel);// создаём список для отображения сигналов для первого массива
        final JList secondArrayList = new JList(secondArrayListModel);// создаём список для отображения сигналов для второго массива
        centerPanel.add(new JScrollPane(firstArrayList));
        centerPanel.add(new JScrollPane(secondArrayList));
        add(BorderLayout.CENTER, centerPanel);

        JPanel eastPanel = new JPanel(new FlowLayout());
        JButton generateBtn = new JButton("Generate signals");
        eastPanel.add(generateBtn);
        JButton saveToExcelBtn = new JButton("Save to excel");
        eastPanel.add(saveToExcelBtn);
        add(BorderLayout.EAST, eastPanel);


        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                firstArrayListModel.clear();//перед заполнением, чистим списки, которые отоброжают массивы
                secondArrayListModel.clear();
                //берём значения с выпадающих списов (модель - это как база данных для айного компонента)
                Integer amountOfGeneratedSignals = (Integer) selectAmountOfGeneratedSignalComboBox.getModel().getSelectedItem();// берём значение сы выпадающего списка для выбора кол-ва элементов для генерации
                String firstArrayGenerationType = (String) selectGenerationTypeFirstArrayComboBox.getModel().getSelectedItem();// берём значение с выпадающего спиская для определения типа  генерации массива 1
                String secondArrayGenerationType = (String) selectGenerationTypeSecondArrayComboBox.getModel().getSelectedItem();// берём значение с выпадающего спиская для определения типа  генерации массива 2
                fillPresentationListModels(amountOfGeneratedSignals, firstArrayGenerationType,  secondArrayGenerationType);// вызываем метод, который заполнит списки, которые отоброжают массивы
            }
        });
        saveToExcelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //заполняем первый массив по данным из первого листа с числами
                ArrayList<Double> firstArray = new ArrayList<>();
                for (int i = 0; i < firstArrayListModel.getSize(); i++) {
                    firstArray.add(firstArrayListModel.getElementAt(i));
                }
                //заполняем второй массив по данным из второго листа с числами
                ArrayList<Double> secondArray = new ArrayList<>();
                for (int i = 0; i < secondArrayListModel.getSize(); i++) {
                    secondArray.add(secondArrayListModel.getElementAt(i));
                }
                //сохраняем в эксель файл
                String resultFilePath = ExcelWriterUtils.writeToExcelFile("result.xlsx", firstArray, secondArray);
                //показываем диалог, чтобы открыть файл
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Data was successfully saved. Would you like to open created file?", "Open file", dialogButton);
                //dialogResult == 0, если нажали 'Yes'
                if (dialogResult == 0) {
                    try {
                        Desktop.getDesktop().open(new File(resultFilePath));
                    } catch (IOException e) {
                        // если возникнут ошибки с открытием файла и тп, то просто напишет в консоль(обычно все будет хорошо и в логи писать не будет)
                        e.printStackTrace();
                    }
                }

            }
        });



        setVisible(true);
    }

    private void fillPresentationListModels(Integer amountOfGeneratedSignals, String firstArrayGenerationType, String secondArrayGenerationType) {
        AbstractGenerator firstArrayGenerator = GeneratorUtils.getGeneratorByGenerationType(firstArrayGenerationType);//получаем генератор через вызов йнукции для получения генератора по типу
        AbstractGenerator secondArrayGenerator = GeneratorUtils.getGeneratorByGenerationType(secondArrayGenerationType);//получаем генератор аналогично для массива 2

        List<Double> firstArrayGeneratedValues = firstArrayGenerator.generate(amountOfGeneratedSignals); // генерируем с помощью подсунотого генератора, переданное кол-во сигналов
        //заполняем модель(как база данных) для списка, отображающего элементы первого массива
        for (Double firstArrayGeneratedValue : firstArrayGeneratedValues) {
            firstArrayListModel.addElement(firstArrayGeneratedValue);
        }
        // тоже самое делаем для второго массива(определяем генератор по типу, генерируем значения и заполняем модель для второго отображающего списка)
        List<Double> secondArrayGeneratedValues = secondArrayGenerator.generate(amountOfGeneratedSignals);
        for (Double secondArrayGeneratedValue : secondArrayGeneratedValues) {
            secondArrayListModel.addElement(secondArrayGeneratedValue);
        }
    }
}