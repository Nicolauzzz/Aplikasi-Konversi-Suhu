import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KonversiSuhu extends JFrame {
    private JTextField inputField;
    private JLabel hasilLabel;
    private JComboBox<String> dariComboBox;
    private JComboBox<String> keComboBox;

    public KonversiSuhu() {
        // Setup frame
        setTitle("Aplikasi Konversi Suhu");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Buat panel utama dengan padding yang lebih besar
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Panel input dengan spacing yang lebih baik
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JLabel inputLabel = new JLabel("Masukkan Suhu:");
        inputField = new JTextField(15);
        // Atur ukuran textfield agar lebih proporsional
        inputField.setPreferredSize(new Dimension(150, 25));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        // Panel pilihan konversi dengan spacing yang lebih baik
        JPanel konversiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        String[] pilihan = {"Celsius", "Fahrenheit", "Kelvin", "Reamur"};
        dariComboBox = new JComboBox<>(pilihan);
        keComboBox = new JComboBox<>(pilihan);
        // Atur ukuran combo box agar lebih proporsional
        Dimension comboSize = new Dimension(120, 25);
        dariComboBox.setPreferredSize(comboSize);
        keComboBox.setPreferredSize(comboSize);

        JLabel dariLabel = new JLabel("Dari:");
        JLabel keLabel = new JLabel("Ke:");
        konversiPanel.add(dariLabel);
        konversiPanel.add(dariComboBox);
        konversiPanel.add(keLabel);
        konversiPanel.add(keComboBox);

        // Panel tombol dengan spacing yang lebih baik
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton konversiButton = new JButton("Konversi");
        JButton resetButton = new JButton("Reset");
        // Atur ukuran tombol agar lebih proporsional
        Dimension buttonSize = new Dimension(100, 30);
        konversiButton.setPreferredSize(buttonSize);
        resetButton.setPreferredSize(buttonSize);
        buttonPanel.add(konversiButton);
        buttonPanel.add(resetButton);

        // Panel hasil dengan font yang lebih besar
        JPanel hasilPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hasilLabel = new JLabel("Hasil: -");
        hasilLabel.setFont(new Font(hasilLabel.getFont().getName(), Font.BOLD, 14));
        hasilPanel.add(hasilLabel);

        // Tambah panel kosong untuk spacing
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(1, 20));

        // Tambahkan semua panel ke panel utama
        mainPanel.add(inputPanel);
        mainPanel.add(konversiPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(spacerPanel);
        mainPanel.add(hasilPanel);

        // Event handler untuk tombol konversi
        konversiButton.addActionListener(e -> konversiSuhu());

        // Event handler untuk tombol reset
        resetButton.addActionListener(e -> {
            inputField.setText("");
            hasilLabel.setText("Hasil: -");
            dariComboBox.setSelectedIndex(0);
            keComboBox.setSelectedIndex(0);
        });

        // Tambahkan panel utama ke frame
        add(mainPanel);

        // Atur agar frame muncul di tengah layar
        setLocationRelativeTo(null);
    }

    private void konversiSuhu() {
        try {
            double input = Double.parseDouble(inputField.getText());
            String dari = (String) dariComboBox.getSelectedItem();
            String ke = (String) keComboBox.getSelectedItem();
            double hasil = 0;

            // Konversi ke Celsius terlebih dahulu
            double celsius = 0;
            switch (dari) {
                case "Celsius" -> celsius = input;
                case "Fahrenheit" -> celsius = (input - 32) * 5/9;
                case "Kelvin" -> celsius = input - 273.15;
                case "Reamur" -> celsius = input * 5/4;
            }

            // Konversi dari Celsius ke suhu tujuan
            switch (ke) {
                case "Celsius" -> hasil = celsius;
                case "Fahrenheit" -> hasil = (celsius * 9/5) + 32;
                case "Kelvin" -> hasil = celsius + 273.15;
                case "Reamur" -> hasil = celsius * 4/5;
            }

            hasilLabel.setText(String.format("Hasil: %.2f %s", hasil, ke));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Masukkan angka yang valid!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new KonversiSuhu().setVisible(true));
    }
}