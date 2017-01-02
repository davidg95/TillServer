/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.JTill.jtillserver;

import io.github.davidg95.JTill.jtill.DBConnect;
import io.github.davidg95.JTill.jtill.LoginException;
import io.github.davidg95.JTill.jtill.Staff;
import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;
import javax.swing.JOptionPane;

/**
 *
 * @author 1301480
 */
public class TillServer {

    public static int PORT = 600;
    public static int MAX_CONNECTIONS = 10;
    public static int MAX_QUEUE = 10;

    public static Data data;
    public static DBConnect dbConnection;
    public static GUI g;

    private ServerSocket s;
    private ConnectionAcceptThread connThread;

    public static Timer updateTimer;
//    public static DatabaseUpdate updateTask;
    public static long updateInterval = 60000L;

    private static Properties properties;

    private static String hostName;

    public static Image icon;
    public static TrayIcon trayIcon;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless operation not currently supported");
            System.exit(0);
        }
        new TillServer().start();
    }

    public TillServer() {
        icon = new javax.swing.ImageIcon(getClass().getResource("/io/github/davidg95/JTill/resources/tillIcon.png")).getImage();
        loadProperties();
        dbConnection = new DBConnect();
        data = new Data(dbConnection, g);
        if (!GraphicsEnvironment.isHeadless()) {
            g = new GUI(data, dbConnection);
            setSystemTray();
        }
        try {
            s = new ServerSocket(PORT);
            connThread = new ConnectionAcceptThread(s, data);
        } catch (IOException ex) {
        }
    }

    public void start() {
        if (!GraphicsEnvironment.isHeadless()) {
            TillSplashScreen.showSplashScreen();
            g.databaseLogin();
        } else {
            headlessDatabaseLogin();
        }
        if (connThread != null) {
            connThread.start();
        }
        if (!GraphicsEnvironment.isHeadless()) {
            TillSplashScreen.hideSplashScreen();
            g.setVisible(true);
            g.login();
        } else {
            headlessLogin();
        }
    }

    public void headlessDatabaseLogin() {
        try {
            dbConnection.connect("jdbc:derby:TillEmbedded;create=false", "APP", "App");
        } catch (SQLException ex) {
                }
                }

    public void headlessLogin() {
        Staff staff;
        System.out.println("Enter Username");
        String username = new Scanner(System.in).nextLine();
        System.out.println("Enter password");
        String password = new Scanner(System.in).nextLine();

        if (username.equals("") || password.equals("")) {
            headlessLogin();
        }
        try {
            staff = data.login(username, password);
            System.out.println("You are logged in");
        } catch (LoginException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void loadProperties() {
        properties = new Properties();
        InputStream in;

        try {
            in = new FileInputStream("server.properties");

            properties.load(in);

            hostName = properties.getProperty("host");
            PORT = Integer.parseInt(properties.getProperty("port", Integer.toString(PORT)));
            MAX_CONNECTIONS = Integer.parseInt(properties.getProperty("max_conn", Integer.toString(MAX_CONNECTIONS)));
            MAX_QUEUE = Integer.parseInt(properties.getProperty("max_queue", Integer.toString(MAX_QUEUE)));

            in.close();
        } catch (FileNotFoundException | UnknownHostException ex) {
            saveProperties();
        } catch (IOException ex) {
        }
    }

    public static void saveProperties() {
        properties = new Properties();
        OutputStream out;

        try {
            out = new FileOutputStream("server.properties");

            hostName = InetAddress.getLocalHost().getHostName();

            properties.setProperty("host", hostName);
            properties.setProperty("port", Integer.toString(PORT));
            properties.setProperty("max_conn", Integer.toString(MAX_CONNECTIONS));
            properties.setProperty("max_queue", Integer.toString(MAX_QUEUE));

            properties.store(out, null);
            out.close();
        } catch (FileNotFoundException | UnknownHostException ex) {
        } catch (IOException ex) {
        }
    }

    private void setSystemTray() {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(icon);
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem showItem = new MenuItem("Show");
        MenuItem statusItem = new MenuItem("Status");

        aboutItem.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "JTill Server is running on port number "
                    + PORT + " with " + g.clientCounter + " connections.\n"
                    + dbConnection.toString(), "JTill Server",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        showItem.addActionListener((ActionEvent e) -> {
            g.setVisible(true);
            if (!g.isLoggedOn()) {
                g.login();
            }
        });

        statusItem.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Connected Clients: " + g.clientCounter + "/" + MAX_CONNECTIONS
                    + "\nDatabase Address- " + dbConnection.getAddress()
                    + "\nDatabase User- " + dbConnection.getUsername(),
                    "JTill Server Status", JOptionPane.INFORMATION_MESSAGE);
        });

        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.add(showItem);
        popup.add(statusItem);

        trayIcon.setPopupMenu(popup);
        trayIcon.setToolTip("JTill Server is running");

        trayIcon.addActionListener((ActionEvent e) -> {
            g.setVisible(true);
            if (!g.isLoggedOn()) {
                g.login();
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    public static DBConnect getDBConnection() {
        return dbConnection;
    }

    public static Data getData() {
        return data;
    }

    public static Image getIcon() {
        return icon;
    }

    public static String getHostName() {
        return hostName;
    }

//    public static void setUpdateTimer() {
//        updateTimer = new Timer();
//        updateTimer.schedule(updateTask, 10000L, updateInterval);
//    }
//
//    public static void resetUpdateTimer() {
//        updateTask.cancel();
//        updateTimer.cancel();
//        updateTimer.purge();
//        setUpdateTimer();
//    }
//    /**
//     * Timer class for updating the database.
//     */
//    public class DatabaseUpdate extends TimerTask {
//
//        @Override
//        public void run() {
//            try {
//                if (dbConnection.isConnected()) {
//                    g.setUpdateLabel("Updating Database");//Set the label
//                    g.log("Updating database");
//                    data.updateDatabase();
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            SwingUtilities.invokeLater(() -> {
//                                g.setUpdateLabel("");
//                            });
//                        }
//
//                    }, 5000L); //Clear the label after 5 seconds
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(TillServer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
}