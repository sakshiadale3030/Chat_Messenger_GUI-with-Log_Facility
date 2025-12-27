import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.*;

class ServerLogin implements ActionListener
{
    JFrame fobj;
    JButton bobj;
    JTextField tobj;
    JTextArea chatArea;
    JScrollPane pane;
    JLabel messageLabel;

    ServerSocket ssobj;
    Socket sobj;
    DataInputStream in;
    DataOutputStream out;

    BufferedWriter logWriter;

    public ServerLogin(String title, int width, int height)
    {
        fobj = new JFrame(title);

        messageLabel = new JLabel("Message");
        messageLabel.setBounds(50, 25, 100, 30);

        tobj = new JTextField();
        tobj.setBounds(150, 25, 150, 30);

        bobj = new JButton("Send");
        bobj.setBounds(150, 75, 100, 30);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        pane = new JScrollPane(chatArea);
        pane.setBounds(50, 145, 400, 100);

        fobj.add(messageLabel);
        fobj.add(tobj);
        fobj.add(bobj);
        fobj.add(pane);

        bobj.addActionListener(this);

        fobj.setLayout(null);
        fobj.setSize(width, height);
        fobj.setVisible(true);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            logWriter = new BufferedWriter(new FileWriter("server_chat.log", true));
        } catch (Exception e) {}

        startServer();
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            String msg = tobj.getText();
            String log = time() + " You : " + msg;

            chatArea.append(log + "\n");
            logWriter.write(log);
            logWriter.newLine();
            logWriter.flush();

            out.writeUTF(msg);
            tobj.setText("");
        }
        catch (Exception ex) {}
    }

    public void startServer()
    {
        new Thread(() -> {
            try
            {
                ssobj = new ServerSocket(5100);
                logWriter.write(time() + " Server started");
                logWriter.newLine();
                logWriter.flush();

                sobj = ssobj.accept();
                logWriter.write(time() + " Client connected");
                logWriter.newLine();
                logWriter.flush();

                in = new DataInputStream(sobj.getInputStream());
                out = new DataOutputStream(sobj.getOutputStream());

                while (true)
                {
                    String msg = in.readUTF();
                    String log = time() + " Client : " + msg;

                    SwingUtilities.invokeLater(() ->
                        chatArea.append(log + "\n")
                    );

                    logWriter.write(log);
                    logWriter.newLine();
                    logWriter.flush();

                    if (msg.equalsIgnoreCase("bye"))
                        break;
                }

                logWriter.write(time() + " Client disconnected");
                logWriter.newLine();
                logWriter.close();
            }
            catch (Exception e) {}
        }).start();
    }

    private String time()
    {
        return "[" + LocalDateTime.now() + "]";
    }
}

class LoginFrameServer
{
    public static void main(String[] args)
    {
        new ServerLogin("Server", 500, 300);
    }
}
