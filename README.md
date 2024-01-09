# Client-Server-Application

- Employs multi-threading to enable concurrent handling of client connections, ensuring optimal system performance
- Establishes socket-based communication between the client and server to send user requests and receive responses, while implementing error handling to enhance user experience

#Project Title: Train Ticket Price Calculator
#Description: Developed a Java-based Train Ticket Price Calculator as part of a software engineering project. The application consists of a client-server system that allows users to calculate the cost of train tickets based on various parameters such as the type of locomotive, seating class, and the number of adults and children.
#Key Features:
#Graphical User Interface (GUI)
  - Implemented a user-friendly GUI using Java's Swing library.
  - Included radio buttons for selecting the type of locomotive (diesel or steam) and seating class (caboose, presidential, first class, or open air).
  - Incorporated text fields for entering the number of adults and children.
  - Provided a button for triggering the calculation process.
#Client-Side (Lab5Client)
  - Established a socket connection to the server for seamless communication.
  - Implemented action handling to react to user inputs, dynamically adjusting the GUI based on selections.
  - Ensured a clean and intuitive interface for users to interact with the application.
#Server-Side (Lab4Server and Lab4ServerThread)
  - Developed a server application capable of handling multiple client connections concurrently.
  - Set up a server socket to listen for incoming connections on a specified port.
  - Created a separate thread (Lab4ServerThread) to manage each client's request independently. (Synchronization - Race Condition)
  - Implemented command processing logic to calculate ticket prices based on user input.
  - Incorporated error handling for scenarios such as insufficient input, invalid numbers, and number format errors.
#Technologies Used:
  - Java programming language for client and server implementation.
  - Java Swing library for creating the graphical user interface.
  - Socket programming for establishing communication between the client and server.
#Achievements:
  - Successfully implemented a functional and interactive train ticket price calculator.
  - Demonstrated proficiency in GUI development, client-server communication, and error handling.
  - Collaborated with team members to design and implement a comprehensive solution.
#Lessons Learned: This project provided valuable experience in developing interactive GUI applications, implementing socket communication, and addressing challenges related to concurrent programming and error handling in a networked environment. The collaborative nature of the project enhanced teamwork and communication skills.

