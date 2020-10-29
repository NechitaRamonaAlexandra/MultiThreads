# MultiThreads
This app was meant to enhance my knowledge about threads in Java and to practice using them in an application which simulates the interaction between an arbitrary number of clients waiting at queues (i.e. in a bank, public institution, etc.)

---
## Main Considerations
From the puprose of this project, a thread is nothing more than a process running in background until it is told to stop or it's forcefully terminated. My approach was the following: since a server needs to do his job until there are no more clients to serve, it can be considered a thread. Another thread-like concept was the fact that in the simulation clients had to be sent at servers for a specific period of time. Therefore, those classes implement the interface `Runnable` and have the method `run()`.

Threads gives an unmeasurable amount of capabilities, such as `sleep(int millis)` which allows the process to be idle for a given time, `join()` which waits for the completion of the process, and so on. Therefore, whenever a client is at a server, the server needs to sleep for amount of time the client is there in order to simulate the idea of processing the given task.

## Adversities
This implementation might come up with a certain number of issues that need to be taken care of. For instance, when the two different threads are to access the same data (say, an array or a list) due to concurrency they might try to access it at the same time or at very short intervals and cause it to deliver inadequate values. Knowing this, any variable, variable array or list that is known to be shared by two different threads is declared as volatile. This way, it is safe to send both threads to the same variable because it will behave accordingly for the concurrent processes. Moreover, the Collection of `BlockingQueue` is also thread-safe since it has methods: take and put, will patiently wait for elements inside the queue instead of returning null or throwing any exceptions. In this manner, thread execution is not interrupted when the queue is empty. 

## Diagram
![Image](https://github.com/NechitaRamonaAlexandra/MultiThreads/blob/main/diagram.png)

## GUI
The GUI was done using Java Swift.


![Image](https://github.com/NechitaRamonaAlexandra/MultiThreads/blob/main/GUI.png)


