// For week 10
// raup@itu.dk * 10/10/2021
package exercises10;

interface SimpleRWTryLockInterface {
    public boolean readerTryLock();

    public void readerUnlock() throws Exception;

    public boolean writerTryLock();

    public void writerUnlock() throws Exception;
}
