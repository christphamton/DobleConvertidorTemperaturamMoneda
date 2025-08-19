// Importamos librerías necesarias
import javax.swing.*;
import java.awt.*;

// Clase principal
public class ConversorUniversal extends JFrame {

    // Pantallas principales (historial y resultado)
    private JTextField campoHistorico;
    private JTextField campoResultado;

    // Constructor
    public ConversorUniversal() {
        // Configuración de la ventana
        setTitle("Conversor Universal");
        setSize(420, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Pantallas de historial y resultado
        campoHistorico = new JTextField();
        campoHistorico.setEditable(false);
        campoHistorico.setFont(new Font("Arial", Font.ITALIC, 14));
        campoHistorico.setForeground(Color.GRAY);
        campoHistorico.setHorizontalAlignment(SwingConstants.RIGHT);

        campoResultado = new JTextField();
        campoResultado.setEditable(false);
        campoResultado.setFont(new Font("Arial", Font.BOLD, 26));
        campoResultado.setForeground(new Color(39, 174, 96)); // Verde profesional
        campoResultado.setHorizontalAlignment(SwingConstants.RIGHT);

        // Panel superior
        JPanel panelPantallas = new JPanel(new GridLayout(2, 1));
        panelPantallas.add(campoHistorico);
        panelPantallas.add(campoResultado);

        add(panelPantallas, BorderLayout.NORTH);

        // Pestañas: Monedas y Temperatura
        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("💵 Monedas", crearPanelMonedas());
        pestañas.addTab("🌡 Temperatura", crearPanelTemperatura());

        add(pestañas, BorderLayout.CENTER);
    }

    // Panel para conversor de monedas
    private JPanel crearPanelMonedas() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField campoCantidad = new JTextField();
        JComboBox<String> comboDe = new JComboBox<>(new String[]{"USD", "EUR", "PEN"});
        JComboBox<String> comboA = new JComboBox<>(new String[]{"USD", "EUR", "PEN"});
        JButton botonConvertir = new JButton("Convertir 💱");

        // Estilos
        campoCantidad.setFont(new Font("Arial", Font.PLAIN, 16));
        botonConvertir.setFont(new Font("Arial", Font.BOLD, 16));
        botonConvertir.setBackground(new Color(52, 152, 219));
        botonConvertir.setForeground(Color.WHITE);
        botonConvertir.setFocusPainted(false);
        botonConvertir.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Labels
        JLabel labelCantidad = new JLabel("Cantidad:");
        JLabel labelDe = new JLabel("De:");
        JLabel labelA = new JLabel("A:");
        labelCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        labelDe.setFont(new Font("Arial", Font.BOLD, 14));
        labelA.setFont(new Font("Arial", Font.BOLD, 14));

        // Posiciones
        gbc.gridx = 0; gbc.gridy = 0; panel.add(labelCantidad, gbc);
        gbc.gridx = 1; panel.add(campoCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(labelDe, gbc);
        gbc.gridx = 1; panel.add(comboDe, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(labelA, gbc);
        gbc.gridx = 1; panel.add(comboA, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(botonConvertir, gbc);

        // Acción
        botonConvertir.addActionListener(e -> {
            try {
                double cantidad = Double.parseDouble(campoCantidad.getText());
                String monedaDe = (String) comboDe.getSelectedItem();
                String monedaA = (String) comboA.getSelectedItem();

                double resultado = cantidad;

                if (monedaDe.equals("USD") && monedaA.equals("EUR")) resultado = cantidad * 0.91;
                else if (monedaDe.equals("EUR") && monedaA.equals("USD")) resultado = cantidad * 1.1;
                else if (monedaDe.equals("USD") && monedaA.equals("PEN")) resultado = cantidad * 3.7;
                else if (monedaDe.equals("PEN") && monedaA.equals("USD")) resultado = cantidad / 3.7;

                campoHistorico.setText(cantidad + " " + monedaDe + " → " + monedaA);
                campoResultado.setText(String.format("%.2f %s", resultado, monedaA));

            } catch (NumberFormatException ex) {
                campoHistorico.setText("⚠ Ingresa un número válido");
                campoResultado.setText("");
            }
        });

        return panel;
    }

    // Panel para conversor de temperatura
    private JPanel crearPanelTemperatura() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField campoGrados = new JTextField();
        JComboBox<String> comboDe = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        JComboBox<String> comboA = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});
        JButton botonConvertir = new JButton("Convertir 🌡");

        // Estilos
        campoGrados.setFont(new Font("Arial", Font.PLAIN, 16));
        botonConvertir.setFont(new Font("Arial", Font.BOLD, 16));
        botonConvertir.setBackground(new Color(231, 76, 60));
        botonConvertir.setForeground(Color.WHITE);
        botonConvertir.setFocusPainted(false);
        botonConvertir.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Labels
        JLabel labelGrados = new JLabel("Grados:");
        JLabel labelDe = new JLabel("De:");
        JLabel labelA = new JLabel("A:");
        labelGrados.setFont(new Font("Arial", Font.BOLD, 14));
        labelDe.setFont(new Font("Arial", Font.BOLD, 14));
        labelA.setFont(new Font("Arial", Font.BOLD, 14));

        // Posiciones
        gbc.gridx = 0; gbc.gridy = 0; panel.add(labelGrados, gbc);
        gbc.gridx = 1; panel.add(campoGrados, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(labelDe, gbc);
        gbc.gridx = 1; panel.add(comboDe, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(labelA, gbc);
        gbc.gridx = 1; panel.add(comboA, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(botonConvertir, gbc);

        // Acción
        botonConvertir.addActionListener(e -> {
            try {
                double grados = Double.parseDouble(campoGrados.getText());
                String de = (String) comboDe.getSelectedItem();
                String a = (String) comboA.getSelectedItem();

                double resultado = grados;

                if (de.equals("Celsius") && a.equals("Fahrenheit")) resultado = (grados * 9/5) + 32;
                else if (de.equals("Fahrenheit") && a.equals("Celsius")) resultado = (grados - 32) * 5/9;

                campoHistorico.setText(grados + "° " + de + " → " + a);
                campoResultado.setText(String.format("%.2f °%s", resultado, a.equals("Celsius") ? "C" : "F"));

            } catch (NumberFormatException ex) {
                campoHistorico.setText("⚠ Ingresa un número válido");
                campoResultado.setText("");
            }
        });

        return panel;
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConversorUniversal ventana = new ConversorUniversal();
            ventana.setVisible(true);
        });
    }
}
