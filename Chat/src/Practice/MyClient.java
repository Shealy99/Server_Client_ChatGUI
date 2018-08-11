package Practice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyClient extends JFrame {

    ClientConnection cc;

    private static javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private static javax.swing.JTextField jTextField1;
    private String name;

    public static void main(String[] args) {
        new MyClient();
    }

    public MyClient() {
        name = JOptionPane.showInputDialog("Enter your name pls!");
        Character first = name.charAt(0);
        String f = first.toString().toUpperCase();
        name = f + name.substring(1);
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);

        try {
            Socket s = new Socket("localhost", 2805);
            cc = new ClientConnection(s, this);
            cc.start();
//            listenForInput();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed(e);
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {

        if (!jTextArea1.getText().equals("")) {
            jTextArea1.setText(jTextArea1.getText() + "\n" + text);
        } else {
            jTextArea1.setText(text);
        }
        jTextField1.setText("");

    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTextField1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton1)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    String input;

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        input = jTextField1.getText();
        if (input.equals("quit")) {
            System.exit(0);
        }
        cc.sendStringtoServer(input);
    }

//    public void listenForInput() {
//
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            while (!sc.hasNextLine()) {
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            String in = sc.nextLine();
//            if (in.equals("quit")) {
//                System.exit(0);
//                break;
//            }
//            cc.sendStringtoServer(in);
//
//        }
//
//    }
}
