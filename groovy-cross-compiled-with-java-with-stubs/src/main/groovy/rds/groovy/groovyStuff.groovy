package rds.groovy;

class Foo {
    def go() {
        new Bar().go()
    }
}

class Baz {
    def go() {
        println 'hi there'
    }
}

def x = new groovyStuff()
println x
