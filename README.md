## Use Java 9 Jigsaw modules within your Tomcat web applications!

It is a classical HelloServlet Tomcat web application that renders "Hello World!" in a browser.
However the application is split into two Jigsaw modules: `helloworld.provider` and `helloworld.web.app`.
In addition to "Hello World!" output to a browser, it also outputs the module names of two implementation classes.

### Standard Tomcat builds and Jigsaw  

If you package the application into a war file (`mvn install` in the root of the project) and place
the resulting war file into a regular Tomcat and run it, point your browser to
`http://localhost:8080/helloworld-web-app-1.0-SNAPSHOT`, you will see that module names for the both classes are `null`.
That is because currently Tomcat loads all dependencies of a web application into an Unnamed Jigsaw module even if all
they are packed as Jigsaw modules.
What does it mean? It means that even if you split your web application into Jigsaw modules you cannot fully enjoy what
Jigsaw offers to you: Reliable Configuration and Strong Encapsulation.
Thus if you do not put a required module into the war file or access a non-exported functionality of another module,
nobody will catch you at runtime.

### How to load modules of your web application as Jigsaw modules in runtime?

If you want to benefit from Jigsaw for your web application running on Tomcat (yes, really benefit from
"Reliable Configuration" and "Strong Encapsulation") you can try my [Tomcat fork](https://github.com/pjBooms/tomcat):

1. Clone and build it with JDK 9
```
	git clone https://github.com/pjBooms/tomcat.git
	cd tomcat
	./ant
```

2. Clone and build this project
```
    git clone https://github.com/pjBooms/modular-war-example
    cd modular-war-example
    mvn install
```

3. Rename `/WEB-INF/lib` to `/WEB-INF/modules` in the target folder
   (`/WEB-INF/modules` is analog of module path for war files in my fork)
```
    cd helloworld-web-app/target/helloworld-web-app-1.0-SNAPSHOT/WEB-INF
    mv lib modules
```

4. Copy `helloworld-web-app/target/helloworld-web-app-1.0-SNAPSHOT` to `tomcat/output/build/webapps`

5. Run `tomcat/output/build/bin/catalina run`

5. Point your browser to `http://localhost:8080/helloworld-web-app-1.0-SNAPSHOT`

6. Enjoy the names of Jigsaw modules!
