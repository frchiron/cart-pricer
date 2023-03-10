# Kata Seed Java Maven

This is a seed to bootstrap quickly a Java project with Maven,
including testing with Junit/assertj.

## Check everything is ok

```
./mvnw clean install
```

## Possible improvements

* how to regularly update dependencies versions (like renovatebot) with Github
* use maven daemon to speed up
* 

## Tips

* set new maven version by command-line
```
mvn -N wrapper:wrapper -Dmaven=3.5.2
```