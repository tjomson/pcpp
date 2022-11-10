package exercises09;

import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FileRead {
    public static void main(String[] args) { 

        var fr_100 = new FileRead(args[0]);
        fr_100.readWords.take(100).subscribe(fr_100.display);
        
        var fr_22 = new FileRead(args[0]);
        fr_22.readWords.filter((s) -> s.length() >= 22).subscribe(fr_22.display);

        var fr_all = new FileRead(args[0]);
        fr_all.readWords.subscribe(fr_all.display);

        var fr_palin = new FileRead(args[0]);
        fr_palin.readWords.filter((s) -> isPalindrome(s)).subscribe(fr_palin.display);
    }
    
    private final BufferedReader reader;
    private final Stream<String> lines;

    public FileRead(String path) {
        Stream<String> temp_s = Stream.<String>empty();
        BufferedReader temp_r = new BufferedReader(new StringReader(""));
        try {
            temp_r = new BufferedReader(new FileReader(path));
            temp_s = temp_r.lines();
        } catch (IOException exn) { }
        reader = temp_r;
        lines = temp_s;
    }

    Observable<String> readWords = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            new Thread() {
                @Override
                public void run() {
                    try {
                        lines.forEach((s) -> {
                            e.onNext(s);
                        });
                        reader.close();
                    }
                    catch (IOException e) {}
                }
              }.start();
        }
      }); 

    Observer<String> display = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {  }
        @Override
        public void onNext(String value) {
            System.out.println(value);
        }
        @Override
        public void onError(Throwable e) {System.out.println("onError: "); }
        @Override
        public void onComplete() { System.out.println("onComplete: All Done!");   }
    };

    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
          if (s.charAt(start) != s.charAt(end))
            return false;
          start++;
          end--;
        }
        return true;
    }
}
