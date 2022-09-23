package exercises03;

public class Person {

    public static void main(String[] args) {
        var p0 = new Person();
        var t1 = new Thread(() -> {
            var p1 = new Person();
            System.out.println("t1: " + p1.id);
        });
        var t2 = new Thread(() -> {
            var p2 = new Person(5L);
            System.out.println("t2: " + p2.id);
        });
        var t3 = new Thread(() -> {
            var p3 = new Person(40L);
            System.out.println("t3: " + p3.id);
        });
        var t4 = new Thread(() -> {
            var p4 = new Person();
            p4.changeZipAddr(200, "bruh");
            p0.changeZipAddr(100, "lmao");
            System.out.println("t4: " + p4.address + " " + p4.zip + ", " + p0.address + " " + p0.zip);
        });
        var t5 = new Thread(() -> {
            p0.changeZipAddr(400, "xd");
            System.out.println("t5: " + p0.address + " " + p0.zip);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("p0 final id: " + p0.id);
        System.out.println("p0 final addr: " + p0.address + " " + p0.zip);
        System.out.println("final id in class: " + Person._id);
    }

    private static long _id = -1;

    public final long id;
    private String name;
    private int zip;
    private String address;

    public Person() {
        synchronized (Person.class) {
            synchronized (this) {
                id = ++_id;
            }
        }
    }

    public Person(long i) {
        synchronized (Person.class) {
            synchronized (this) {
                if (_id == -1) {
                    id = i;
                    _id = i;
                } else {
                    id = ++_id;
                }
            }
        }
    }

    public synchronized void changeZipAddr(int n_zip, String n_addr) {
        zip = n_zip;
        address = n_addr;
    }

    public int getZip() {
        return zip;
    }

    public String getAddr() {
        return address + "";
    }

    public String getName() {
        return name + "";
    }
}
