测试录音机+webRtc的VAD功能


执行
~~~
mvn clean package
~~~
生成libfvadTest-1.0-SNAPSHOT.jar
再把libfvad.so拷贝到当前目录下,执行
~~~
export LD_LIBRARY_PATH=.:$LD_LIBRARY_PATH
java -jar libfvadTest-1.0-SNAPSHOT.jar
~~~