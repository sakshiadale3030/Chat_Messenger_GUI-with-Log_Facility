# 💬 Java Client–Server Chat System 

## 📌 Abstract
This project implements a **Client–Server Chat Messenger** using **Java Swing**, **TCP socket programming**, and **multithreading**.  
It allows two-way real-time text communication between a server and a client through a graphical user interface.  
The system ensures safe communication using `DataInputStream` and `DataOutputStream`, maintains a server&client-side chat log, and supports graceful termination using a predefined `"bye"` protocol.

This project demonstrates core networking concepts, GUI development, file handling, and concurrent programming in Java.

---

## 🚀 Features

- Real-time two-way communication
- Swing-based Graphical User Interface
- TCP socket-based networking (Port **5100**)
- Background thread for receiving messages
- Client-side chat logging with timestamps
- Clean termination using `"bye"`
- Proper closing of sockets and streams
- Simple and beginner-friendly design

---

## 🧩 UML Diagram (Text Representation)


               +---------------------------------------------------+
               |             Client–Server Chat System             |
               +---------------------------------------------------+
                            | 
                            |-----------------------------------|
                            v                                   v
               +-------------------------------+   +--------------------------------+
               |          ServerLogin          |   |          ClientLogin           |
               +-------------------------------+   +--------------------------------+
               | - fobj : JFrame               |   | - fobj : JFrame                |
               | - bobj : JButton              |   | - bobj : JButton               |
               | - tobj : JTextField           |   | - tobj : JTextField            |
               | - messageLabel : JLabel       |   | - messageLabel : JLabel        |
               | - chatArea : JTextArea        |   | - chatArea : JTextArea         |
               | - pane : JScrollPane          |   | - pane : JScrollPane           |  
               |                               |   |                                |
               | - ssobj : ServerSocket        |   | - sobj : Socket                |
               | - sobj : Socket               |   | - in : DataInputStream         |
               | - in : DataInputStream        |   | - out : DataOutputStream       |
               | - out : DataOutputStream      |   | - logWriter : BufferedWriter   |  
               | - logWriter : BufferedWriter  |   |                                |
               +-------------------------------+   +--------------------------------+
               | + ServerLogin()               |   | + ClientLogin()                |
               | + actionPerformed(ActionEvent)|   | + actionPerformed(ActionEvent) |
               | + startServer()               |   | + startClient()                |
               +-------------------------------+   +--------------------------------+
                         ^ implements                         ^ implements
                         |                                    |
                  ActionListener                        ActionListener

---
## 🔁 Workflow

### Server Side
1. Server starts and creates `ServerSocket(5100)`
2. Waits for client connection
3. Accepts client socket
4. Creates input/output streams
5. Receives messages continuously
6. Terminates connection on `"bye"`

### Client Side
1. Client connects to `localhost:5100`
2. Starts background thread for receiving messages
3. Sends messages using GUI
4. Logs all messages into a file
5. Terminates cleanly on `"bye"`

---

## ⚙️ Technologies Used

- Java (JDK 17+)
- Java Swing (GUI)
- TCP Networking
- Multithreading
- DataInputStream / DataOutputStream
- File I/O (BufferedWriter)
- VS Code

---

## 📂 Project Structure

Client–Server Chat System /<br>
├── ClientLogin.java<br>
├── ServerLogin.java<br>
├── client_chat.log<br>
├── server_chat.log<br>
└── README.md

---

## 📝 Chat Logging

### Client-Side Logging
- File Name: `client_chat.log`
- Location: Project root directory
- Format: Timestamped plain text
- Mode: Append (previous chats preserved)

### Sample Log File
[2025-01-10T11:20:15] You : Hello<br>
[2025-01-10T11:20:18] Server : Hi<br>
[2025-01-10T11:20:25] You : bye<br>

---

## 🔒 Resource Management

The application ensures proper closing of:
- Socket
- ServerSocket
- DataInputStream
- DataOutputStream
- Log file writer

All resources are closed when `"bye"` is sent or received.

---

## ▶️ How to Run

### Compile the Programs

javac ServerLogin.java<br>
javac ClientLogin.java

---

🎯 Future Enhancements

Add usernames and authentication<br>
Multi-client support (threaded server)<br>
Improved GUI layout<br>
Message timestamps in GUI<br>
Emoji support<br>
Database-backed chat history<br>

---

🧾 Conclusion

This project successfully demonstrates a Java-based client–server communication system with a graphical interface and logging functionality.
It provides hands-on experience with network programming, multithreading, GUI development, and file handling, making it an excellent foundational project for understanding distributed systems in Java.

---

# 🙋 Author

Sakshi Adale.
