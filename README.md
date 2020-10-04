## Prometheus Java SimpleClient Demo

### Requirements
* JDK 8
* Maven

### How to compile
Run the following in command prompt/terminal.
```bash
mvn clean package
```

### How to run
Run the following in command prompt/terminal.
```bash
mvn jetty:run
```
Then, open [http://localhost:8080/prom-demo](http://localhost:8080/prom-demo) in your browser.

All metrics are exposed at [/metrics](http://localhost:8080/prom-demo/metrics)