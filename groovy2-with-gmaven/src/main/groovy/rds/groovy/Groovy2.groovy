package rds.groovy

import groovy.transform.TypeChecked

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/26/13
 * Time: 9:28 PM
 */
@TypeChecked
//@CompileStatic
class Groovy2 {
    void sayHi() {
        try {
            String hi = "Hi"
            System.out.println(hi)
        } catch (IllegalStateException | IllegalArgumentException e) {
            println "This will never happen, but I wanted to see the multi-catch! $e"
        }
    }

    String someString() {
        def x = 5
        def y = 10_000 // <-- Look! A separator!
        String s = new Date() // Doesn't fail because of type coercion
//        Date d = "foo" // Would fail because String won't coerce to Date
        def t = doSomething('bob')
        x + s + y + t
    }

    def doSomething(Object o) {
        if (o instanceof String) o.toUpperCase() // <-- type inference allows calls without casts after instanceof check
        else o
    }

    void sayBye() {
        def x = 5
        x = 'Bye' // <-- flow typing--ugly, but allowed
        println x
    }
}
