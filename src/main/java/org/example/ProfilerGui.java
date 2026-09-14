package org.example;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfilerGui extends JFrame {
    private final Profiler profiler = new Profiler();
    private final Map<String, SortLibrary> libraries = SortLibrary.all();

    private final JComboBox<String> libraryComboBox = new JComboBox<>(libraries.keySet().toArray(new String[0]));
    private final JTextField dataField = new JTextField("5, 2, 1, 3, 1, 2, 6, 9");
    private final JTextArea resultArea = new JTextArea(8, 30);

    public ProfilerGui() {
        super("Sort Library Profiler");

        resultArea.setEditable(false);

        JButton runButton = new JButton("Run");
        runButton.addActionListener(e -> onRun());

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.add(new JLabel("Sort Library:"));
        topPanel.add(libraryComboBox);
        topPanel.add(new JLabel("Input Data (comma separated):"));
        topPanel.add(dataField);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(runButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void onRun() {
        List<Integer> data;
        try {
            data = parseData(dataField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "숫자를 쉼표로 구분해서 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SortLibrary library = libraries.get((String) libraryComboBox.getSelectedItem());
        profiler.setData(data);
        profiler.setLib(library);
        profiler.runLib();
        resultArea.setText(profiler.getResultSummary());
    }

    private List<Integer> parseData(String text) {
        List<Integer> data = new ArrayList<>();
        for (String token : text.split(",")) {
            data.add(Integer.parseInt(token.trim()));
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfilerGui().setVisible(true));
    }
}
