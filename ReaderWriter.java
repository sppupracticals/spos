package ReaderWriter;
import java.util.*;
import java.concurrent.Semaphore;

public class ReaderWriter {
    static int mutex = 1;
    static int database = 1;
    static int read_count = 0;

static void Reader() throws Exception {
    while(true) {
        mutex = wait(mutex);
        read_count = read_count + 1;
        if (read_count == 1) {
            database = signal(database);
        }
        mutex = signal(mutex);
        System.out.println(read_count+" Reading the Database !!!...");
        mutex = wait(mutex);
        read_count = read_count - 1;
        if (read_count == 0) {
            database = signal(database);
        }
        mutex = signal(mutex);
        System.out.println("Reading Finished !!!...");
        break;
    }
}

static int wait(int mutex) {
    while(mutex <= 0)
        break;
    mutex = mutex - 1;
    return mutex;
}

static int signal(int database) {
    database = database + 1;
    return database;
}

static void Writer() throws Exception {
    database = wait(database);
    System.out.println("Writing to the Database !!!...");
    database = signal(database);
    System.out.println("Writing Finished !!!...");
}

public static void main(String[] args) throws Exception {
    Reader();
    Reader();
    Writer();
}
}