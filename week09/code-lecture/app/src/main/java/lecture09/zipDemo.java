package lecture09;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* This example is taken from
   https://github.com/ReactiveX/RxJava/wiki/Combining-Observables#zip
   Modified by Jørgen Staunstrup, ITU, jst@itu.dk version Oct 31, 2022 */

public class zipDemo {
  public static void main(String[] args) { new zipDemo(); } 

  public zipDemo(){
    Observable<String> firstNames = Observable.just("James", "Jean-Luc", "Benjamin");
    Observable<String> lastNames = Observable.just("Kirk", "Picard", "Sisko");
    firstNames.zipWith(lastNames, (first, last) -> first + " " + last)
        .subscribe(item -> System.out.println(item));
    }  
}
