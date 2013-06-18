package rds.testbed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main {
    public static void main(String[] args) {
        def context = new ClassPathXmlApplicationContext('/applicationContext.xml')
        def repo = context.getBean(ThingRepo)
        println "Saving bob"
        // Like this, won't cache on save because of https://hibernate.atlassian.net/browse/HHH-7964
        Thing saved = repo.save(new Thing('bob'))
        println "Loading bob"
        Thing loaded = repo.findByName('bob')
        assert !saved.is(loaded)
        assert saved.name == loaded.name
        println "bob is: $loaded"
        println "Loading bob again"
        println "bob is: " + repo.findByName('bob')
    }
}
