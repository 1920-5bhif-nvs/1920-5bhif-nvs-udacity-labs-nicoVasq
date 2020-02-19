#### Fifth Lesson

Word Guessing App built with the MVVN Architecture in mind.



#### Mayor points

* Additional Lifecycle-libraray exercises
  * Alternatives to solve Lifecycle problems, like memory-loss

* Android App Architecture
  * MVVM
    * ViewModel (holds app's UI data)
      * Decide what data the ViewModel should hold
* LiveData
  * Lifecycle Aware (knows about the Lifecycle of its UI Controller observers)
    * Does its own cleanup
    * Notifies/Updates the UI Controllers if they are on screen
  * Observeable Dataholder Class
  * Communicating from the ViewModel to the UI Controller
* LiveData Encapsulation 
* Event / State
* Databinding
  * Databinding Expressions 