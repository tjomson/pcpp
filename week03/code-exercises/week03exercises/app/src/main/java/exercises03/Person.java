package exercises03;

public class Person {
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
                }
                else {
                    id = ++_id;
                }
            }
        }
    }

    public synchronized void changeZipAddr(int n_zip, String n_addr) {
        zip = n_zip;
        address = n_addr;
    }

    public int getZip() { return zip; }
    public String getAddr() { return address+""; }
    public String getName() { return name+""; }
}
