package exercises09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ReadWords {

    public static void main(String[] args) {
        final Observer<String> display = new Observer<String>() {
            @Override
            public void onNext(String value) {
                System.out.println(value);
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: ");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete: All Done!");
            }

        };

        Observable<String> readWords = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> s) throws Exception {
                try {
                    BufferedReader reader = new BufferedReader(
                            // new
                            // FileReader(".\\week09\\code-exercises\\app\\bin\\main\\english-words.txt"));
                            new FileReader("./english-words.txt"));
                    String next = reader.readLine();
                    while (next != null) {
                        s.onNext(next);
                        next = reader.readLine();
                    }
                    reader.close();
                } catch (IOException exn) {
                    System.out.println(exn);
                }

            }
        });

        readWords.subscribe(display);

        for (int i = 0; i < 100; i++) {
            // readWords.take(100);
            var ele = readWords.firstElement().blockingGet();

            System.out.println(ele);
        }
    }

}
