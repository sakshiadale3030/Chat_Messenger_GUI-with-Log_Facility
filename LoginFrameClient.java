import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.time.*;

class ClientLogin implements ActionListener
{
    JFrame fobj;
    JButton bobj;
    JTextField tobj;
    JTextArea chatArea;
    JScrollPane pane;
    JLabel messageLabel;

    Socket sobj;
    DataOutputStream out;
    DataInputStream in;

    BufferedWriter logWriter;

    public ClientLogin(String title, int width, int height)
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
            logWriter = new BufferedWriter(new FileWriter("client_chat.log", true));
        } catch (Exception e) {}

        startClient();
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

    public void startClient()
    {
        new Thread(() -> {
            try
            {
                sobj = new Socket("localhost", 5100);
                out = new DataOutputStream(sobj.getOutputStream());
                in = new DataInputStream(sobj.getInputStream());

                while (true)
                {
                    String reply = in.readUTF();
                    String log = time() + " Server : " + reply;

                    SwingUtilities.invokeLater(() ->
                        chatArea.append(log + "\n")
                    );

                    logWriter.write(log);
                    logWriter.newLine();
                    logWriter.flush();

                    if (reply.equalsIgnoreCase("bye"))
                        break;
                }
            }
            catch (Exception e)
            {
                try {
                    logWriter.write(time() + " Server disconnected");
                    logWriter.newLine();
                    logWriter.close();
                } catch (Exception ex) {}
            }
        }).start();
    }

    private String time()
    {
        return "[" + LocalDateTime.now() + "]";
    }
}

class LoginFrameClient
{
    public static void main(String[] args)
    {
        new ClientLogin("Client", 500, 300);
    }
}
