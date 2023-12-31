/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import bd.Conexion;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author alumnat
 */
public class NewMainPage extends javax.swing.JFrame {

    private final int idUser;
    private Conexion conexion;
    int creditos;

    public NewMainPage(int idUser) {
        initComponents();
        System.out.println(idUser);
        this.setLocationRelativeTo(null); //Inicializa al centro de la pantalla

        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/icono.png")); // Esto es para cambiar el icono de la app
        Image image = icon.getImage();
        setIconImage(image);
        this.idUser = idUser;

        // Inicializa la variable conexion
        conexion = new Conexion();
        System.out.println("Conexion creada correctamente.");

        String nombreUsuario = getNombreUsuarioPorId(idUser);
        jLabel20.setText(nombreUsuario);
        creditos = getCreditosPorId(idUser);
        jLabel20.setText(nombreUsuario);
        jLabel1.setText(String.valueOf(creditos));

        System.out.println("Cargando reservas disponibles...");
        mostrarReservasDisponibles();
    }

    private NewMainPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para validar las credenciales en la base de datos
    private String getNombreUsuarioPorId(int id) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión
        String nombre = "";

        try {
            // Consulta SQL para verificar las credenciales
            String consulta = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, id);

            ResultSet resultado = statement.executeQuery();
            // Si se encontraron resultados, las credenciales son válidas
            if (resultado.next()) {
                nombre = resultado.getString("Nombre");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombre;
    }

    private int getCreditosPorId(int id) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.DatabaseConnection(); // Obtén la conexión
        int creditos = 0;

        try {
            // Consulta SQL para obtener los créditos del usuario por su ID
            String consulta = "SELECT credito FROM usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setInt(1, id);

            ResultSet resultado = statement.executeQuery();
            // Si se encontraron resultados, obtenemos los créditos del usuario
            if (resultado.next()) {
                creditos = resultado.getInt("credito");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Recuerda cerrar la conexión y otros recursos relacionados con la base de datos
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return creditos;
    }

    private void mostrarReservasDisponibles() {
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            nuevoJScrollPane.getVerticalScrollBar().setUnitIncrement(25); // Configurar el desplazamiento a 25 píxeles

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel crearPanelReservaMejorado(JLabel imagen, String tipoEstancia, String direccion, int costeDia, int metros, int idReserva, JButton reservarButton) {
        JPanel panelReserva = new JPanel();
        panelReserva.setLayout(new BorderLayout());
        panelReserva.setBackground(Color.WHITE);
        panelReserva.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // JPanel para la información de la reserva
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // Margen de 10 en todos los lados
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // JLabel para el tipo de estancia
        agregarComponente(infoPanel, gbc, "<html><b>Estancia:</b> " + tipoEstancia + "</html>");

        // JLabel para la dirección
        agregarComponente(infoPanel, gbc, "<html><b>Dirección:</b> " + direccion + "</html>");

        // JLabel para el costo por día
        agregarComponente(infoPanel, gbc, "<html><b>Costo por día:</b> " + costeDia + "</html>");

        // JLabel para los metros
        agregarComponente(infoPanel, gbc, "<html><b>Metros:</b> " + metros + " cuadrados</html>");

        // JLabel y JDateChooser para la fecha de entrada
        agregarComponente(infoPanel, gbc, "<html><b>Fecha de Entrada:</b></html>");
        JDateChooser fechaEntradaChooser = new JDateChooser();
        fechaEntradaChooser.setDateFormatString("dd-MM-yyyy");
        fechaEntradaChooser.setPreferredSize(new Dimension(150, 30));
        fechaEntradaChooser.setBackground(Color.WHITE);
        agregarComponente(infoPanel, gbc, fechaEntradaChooser);

        // JLabel y JDateChooser para la fecha de salida
        agregarComponente(infoPanel, gbc, "<html><b>Fecha de Salida:</b></html>");
        JDateChooser fechaSalidaChooser = new JDateChooser();
        fechaSalidaChooser.setDateFormatString("dd-MM-yyyy");
        fechaSalidaChooser.setPreferredSize(new Dimension(150, 30));
        fechaSalidaChooser.setBackground(Color.WHITE);
        agregarComponente(infoPanel, gbc, fechaSalidaChooser);

        // Añadir botón de reserva con diseño personalizado
        JButton botonReserva = new JButton("Reservar");
        botonReserva.setBackground(new Color(255, 222, 89)); // Color verde
        botonReserva.setFont(new Font("Century Gothic", Font.BOLD, 14));
        botonReserva.setPreferredSize(new Dimension(150, 30));

        botonReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conexion conexion = new Conexion();
                Connection connection = conexion.DatabaseConnection();

                Date fechaEntrada = fechaEntradaChooser.getDate();
                Date fechaSalida = fechaSalidaChooser.getDate();

                if (fechaEntrada == null || fechaSalida == null) {
                    JOptionPane.showMessageDialog(null, "Las fechas de entrada y salida deben estar seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalDate localDateEntrada = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateSalida = fechaSalida.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate fechaActual = LocalDate.now();

                // Verificar si la fecha de inicio es posterior a la fecha actual
                if (localDateEntrada.isBefore(fechaActual)) {
                    JOptionPane.showMessageDialog(null, "La fecha de inicio de la reserva no puede ser anterior a la fecha actual", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long diasDiferencia = ChronoUnit.DAYS.between(localDateEntrada, localDateSalida);

                if (diasDiferencia <= 0) {
                    JOptionPane.showMessageDialog(null, "La fecha de salida debe ser posterior a la fecha de entrada", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int totalCoste = (int) (diasDiferencia * costeDia);

                // Verificar si ya existe una reserva para las fechas seleccionadas
                String selectReservaExistente = "SELECT * FROM reservas_usuarios WHERE id_reserva = ? AND (? BETWEEN fecha_solicitud AND fecha_fin_reserva OR ? BETWEEN fecha_solicitud AND fecha_fin_reserva)";
                try (PreparedStatement selectStatement = connection.prepareStatement(selectReservaExistente)) {
                    selectStatement.setInt(1, idReserva);
                    selectStatement.setDate(2, new java.sql.Date(fechaEntrada.getTime()));
                    selectStatement.setDate(3, new java.sql.Date(fechaSalida.getTime()));

                    ResultSet resultSet = selectStatement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Ya existe una reserva para esas fechas", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int opcion = JOptionPane.showOptionDialog(
                                null,
                                "El precio total de la reserva es de: " + totalCoste + "\n Pulse Ok si desea continuar",
                                "Advertencia",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null,
                                null,
                                null);

                        if (opcion == JOptionPane.OK_OPTION) {
                            creditos = creditos - totalCoste;
                            System.out.println(creditos);

                            String updateCreditos = "Update usuarios SET Credito = ? WHERE id = ?";
                            try (PreparedStatement updateStatementCreditos = connection.prepareStatement(updateCreditos)) {
                                updateStatementCreditos.setInt(1, creditos);
                                updateStatementCreditos.setInt(2, idUser);

                                int rowsAffectedCreditos = updateStatementCreditos.executeUpdate();
                                if (rowsAffectedCreditos > 0) {
                                    System.out.println("Actualización exitosa en la tabla usuarios");
                                    jLabel1.setText(String.valueOf(creditos));
                                } else {
                                    System.out.println("No se ha actualizado ninguna fila en la tabla usuarios");
                                    return;
                                }
                            } catch (SQLException er) {
                                er.printStackTrace();
                            }

                            String insertReservasUsuarios = "INSERT INTO reservas_usuarios (id_reserva,id_usuario, fecha_solicitud, fecha_fin_reserva, coste_reserva) VALUES (?, ?, ?, ?, ?)";

                            try (PreparedStatement insertStatement = connection.prepareStatement(insertReservasUsuarios)) {
                                insertStatement.setInt(1, idReserva);
                                insertStatement.setInt(2, idUser);
                                insertStatement.setDate(3, new java.sql.Date(fechaEntrada.getTime()));
                                insertStatement.setDate(4, new java.sql.Date(fechaSalida.getTime()));
                                insertStatement.setInt(5, totalCoste);

                                int rowsInserted = insertStatement.executeUpdate();
                                if (rowsInserted > 0) {
                                    System.out.println("Inserción exitosa en la tabla reservas_usuarios");
                                    JOptionPane.showMessageDialog(null, "Reserva realizada exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                                    fechaEntradaChooser.setDate(null);
                                    fechaSalidaChooser.setDate(null);
                                } else {
                                    System.out.println("No se ha insertado ninguna fila en la tabla reservas_usuarios");
                                }
                            } catch (SQLException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                } catch (SQLException errorr) {
                }
            }
        });

        agregarComponente(infoPanel, gbc, botonReserva);

        // Añadir elementos al panelReserva
        panelReserva.add(imagen, BorderLayout.WEST);
        panelReserva.add(infoPanel, BorderLayout.CENTER);

        return panelReserva;
    }

    private void agregarComponente(JPanel panel, GridBagConstraints gbc, Object componente) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if (componente instanceof JComponent) {
            panel.add((JComponent) componente, gbc);
        } else {
            panel.add(new JLabel(componente.toString()), gbc);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        casaRural = new javax.swing.JLabel();
        casaRuralTexto = new javax.swing.JLabel();
        apartamentos = new javax.swing.JLabel();
        apartamentosTexto = new javax.swing.JLabel();
        HotelesTexto = new javax.swing.JLabel();
        Hoteles = new javax.swing.JLabel();
        Apartahotel = new javax.swing.JLabel();
        ApartahotelTexto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnReservas = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        PanelReservas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 222, 89));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coin1.png"))); // NOI18N
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel5.setIconTextGap(1);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 20, 33, -1));

        jLabel1.setText("0");
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 20, 36, 30));

        jPanel5.setBackground(new java.awt.Color(255, 222, 89));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        casaRural.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        casaRural.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/casas_rurales.png"))); // NOI18N
        casaRural.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        casaRural.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        casaRural.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        casaRural.setIconTextGap(1);
        casaRural.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                casaRuralMouseClicked(evt);
            }
        });
        jPanel5.add(casaRural, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        casaRuralTexto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        casaRuralTexto.setText("Casas rurales");
        casaRuralTexto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        casaRuralTexto.setPreferredSize(new java.awt.Dimension(30, 20));
        casaRuralTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                casaRuralTextoMouseClicked(evt);
            }
        });
        jPanel5.add(casaRuralTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 80, 30));

        apartamentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        apartamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apartamentos.png"))); // NOI18N
        apartamentos.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        apartamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        apartamentos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        apartamentos.setIconTextGap(1);
        apartamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apartamentosMouseClicked(evt);
            }
        });
        jPanel5.add(apartamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        apartamentosTexto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        apartamentosTexto.setText("Apartamentos");
        apartamentosTexto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        apartamentosTexto.setPreferredSize(new java.awt.Dimension(30, 20));
        apartamentosTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apartamentosTextoMouseClicked(evt);
            }
        });
        jPanel5.add(apartamentosTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 90, 30));

        HotelesTexto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        HotelesTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HotelesTexto.setText("Hoteles");
        HotelesTexto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HotelesTexto.setPreferredSize(new java.awt.Dimension(30, 20));
        HotelesTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HotelesTextoMouseClicked(evt);
            }
        });
        jPanel5.add(HotelesTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 50, 30));

        Hoteles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Hoteles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Hoteles.png"))); // NOI18N
        Hoteles.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Hoteles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hoteles.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Hoteles.setIconTextGap(1);
        Hoteles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HotelesMouseClicked(evt);
            }
        });
        jPanel5.add(Hoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        Apartahotel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Apartahotel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apartahoteles.png"))); // NOI18N
        Apartahotel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Apartahotel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Apartahotel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        Apartahotel.setIconTextGap(1);
        Apartahotel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApartahotelMouseClicked(evt);
            }
        });
        jPanel5.add(Apartahotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        ApartahotelTexto.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        ApartahotelTexto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ApartahotelTexto.setText("Apartahoteles");
        ApartahotelTexto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ApartahotelTexto.setPreferredSize(new java.awt.Dimension(30, 20));
        ApartahotelTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApartahotelTextoMouseClicked(evt);
            }
        });
        jPanel5.add(ApartahotelTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 100, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 62, 450, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo_BOOK4U_Little.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel13.setIconTextGap(1);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/notify.png"))); // NOI18N
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel14.setIconTextGap(1);
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 100));

        jPanel4.setBackground(new java.awt.Color(255, 222, 89));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        jLabel27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel27.setIconTextGap(1);
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel17.setText("Inicio");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 50, 50, 23));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Favoritos");
        jLabel18.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 80, 23));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Reservas");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 100, 23));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Perfil");
        jLabel20.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 23));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/HomeUser.png"))); // NOI18N
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel29.setIconTextGap(1);
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btnReservas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reservas.png"))); // NOI18N
        btnReservas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnReservas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReservas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReservas.setIconTextGap(1);
        btnReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReservasMouseClicked(evt);
            }
        });
        jPanel4.add(btnReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 40, 40));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/favoritos.png"))); // NOI18N
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel31.setIconTextGap(1);
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 440, 80));

        PanelReservas.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelReservasLayout = new javax.swing.GroupLayout(PanelReservas);
        PanelReservas.setLayout(PanelReservasLayout);
        PanelReservasLayout.setHorizontalGroup(
            PanelReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        PanelReservasLayout.setVerticalGroup(
            PanelReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jPanel1.add(PanelReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        CreditPage creditPage = new CreditPage(idUser);
        creditPage.setVisible(true);
        this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btnReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservasMouseClicked
        MevesReservesPage MevesReservesPage = new MevesReservesPage(idUser, creditos);

        MevesReservesPage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnReservasMouseClicked

    private void casaRuralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_casaRuralMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Casa Rural'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_casaRuralMouseClicked

    private void casaRuralTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_casaRuralTextoMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Casa Rural'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_casaRuralTextoMouseClicked

    private void apartamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apartamentosMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Apartamento'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_apartamentosMouseClicked

    private void apartamentosTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apartamentosTextoMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Apartamento'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_apartamentosTextoMouseClicked

    private void HotelesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HotelesMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Hotel'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_HotelesMouseClicked

    private void HotelesTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HotelesTextoMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Hotel'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_HotelesTextoMouseClicked

    private void ApartahotelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApartahotelMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Apartahotel'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ApartahotelMouseClicked

    private void ApartahotelTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApartahotelTextoMouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL and tipo_estancia = 'Apartahotel'";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ApartahotelTextoMouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        String consulta = "SELECT id_reserva, tipo_estancia, coste_dia, metros, direccion, imagen FROM reservas WHERE id_usuario IS NULL";

        try (Connection connection = conexion.DatabaseConnection(); PreparedStatement statement = connection.prepareStatement(consulta)) {

            ResultSet resultado = statement.executeQuery();

            // Crear un nuevo panel para contener los resultados
            JPanel nuevoPanelDisponibles = new JPanel();
            nuevoPanelDisponibles.setLayout(new BoxLayout(nuevoPanelDisponibles, BoxLayout.Y_AXIS));

            while (resultado.next()) {
                int idReserva = resultado.getInt("id_reserva");
                String tipoEstanciaTexto = resultado.getString("tipo_estancia");
                String direccionTexto = resultado.getString("direccion");
                byte[] imagenBytes = resultado.getBytes("imagen");
                int costeDia = resultado.getInt("coste_dia");
                int metros = resultado.getInt("metros");

                ImageIcon imagenReserva = new ImageIcon(imagenBytes);
                Image imagenRedimensionada = imagenReserva.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                JLabel imagenRedimensionadaLabel = new JLabel(imagenRedimensionadaIcon);
                JButton reservarButton = new JButton("Reservar");

                JPanel panelReserva = crearPanelReservaMejorado(imagenRedimensionadaLabel, tipoEstanciaTexto, direccionTexto, costeDia, metros, idReserva, reservarButton);

                nuevoPanelDisponibles.add(panelReserva);
            }

            // Crear un nuevo JScrollPane con el nuevo panel
            JScrollPane nuevoJScrollPane = new JScrollPane(nuevoPanelDisponibles);
            nuevoJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            nuevoJScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajusta los márgenes
            nuevoJScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Remover todos los componentes del panel principal
            PanelReservas.removeAll();

            // Agregar el nuevo JScrollPane al panel principal
            PanelReservas.setLayout(new BorderLayout());
            PanelReservas.add(nuevoJScrollPane, BorderLayout.CENTER);

            // Actualizar la interfaz gráfica
            PanelReservas.revalidate();
            PanelReservas.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        ProfilePage ProfilePage = new ProfilePage(idUser);

        ProfilePage.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel27MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewMainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apartahotel;
    private javax.swing.JLabel ApartahotelTexto;
    private javax.swing.JLabel Hoteles;
    private javax.swing.JLabel HotelesTexto;
    private javax.swing.JPanel PanelReservas;
    private javax.swing.JLabel apartamentos;
    private javax.swing.JLabel apartamentosTexto;
    private javax.swing.JLabel btnReservas;
    private javax.swing.JLabel casaRural;
    private javax.swing.JLabel casaRuralTexto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
