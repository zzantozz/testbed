package rds.groovy;

class Foo {
    def go() {
        return new Bar().go()
    }
}

class Baz {
    String go() {
        return 'hi there'
    }
}
