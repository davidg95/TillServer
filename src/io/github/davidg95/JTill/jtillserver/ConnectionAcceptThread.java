/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.davidg95.JTill.jtillserver;

import io.github.davidg95.JTill.jtill.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread which accepts incoming connections from clients.
 *
 * @author David
 */
public class ConnectionAcceptThread extends Thread {

    public static int PORT_IN_USE;

    private final Settings settings;

    private final ServerSocket socket;
    private final DataConnect dc;

    public ConnectionAcceptThread(DataConnect dc, int PORT) throws IOException {
        super("ConnectionAcceptThread");
        this.socket = new ServerSocket(PORT);
        PORT_IN_USE = PORT;
        this.dc = dc;
        settings = Settings.getInstance();
    }

    public ConnectionAcceptThread(DataConnect dc) throws IOException {
        this(dc, Settings.DEFAULT_PORT);
    }

    @Override
    public void run() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(Integer.parseInt(settings.getSetting("max_conn")), Integer.parseInt(settings.getSetting("max_queue")), 50000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(Integer.parseInt(settings.getSetting("max_queue"))));
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            TillServer.g.log("JTill Server local IP address is " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            dc.getGUI().log(ex);
        }
        dc.getGUI().log("Ready to accept connections");
        for (;;) {
            try {
                Socket incoming = socket.accept();
                pool.submit(new ConnectionThread(socket.getInetAddress().getHostAddress(), dc, incoming));
            } catch (IOException ex) {
                Logger.getLogger(ConnectionAcceptThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
