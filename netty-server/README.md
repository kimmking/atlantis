# netty-server-demo

```
$ wrk -c12 -t6 -d30s  http://localhost:8088/test
Running 30s test @ http://localhost:8088/test
  6 threads and 12 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    13.76ms   49.76ms 652.23ms   93.34%
    Req/Sec     3.73k     2.14k   11.64k    66.61%
  643776 requests in 30.10s, 66.92MB read
Requests/sec:  21391.45
Transfer/sec:      2.22MB
```

```
$ ab -k -c 6 -n 100000 http://localhost:8088/test
This is ApacheBench, Version 2.3 <$Revision: 1826891 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 10000 requests
Completed 20000 requests
Completed 30000 requests
Completed 40000 requests
Completed 50000 requests
Completed 60000 requests
Completed 70000 requests
Completed 80000 requests
Completed 90000 requests
Completed 100000 requests
Finished 100000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8088

Document Path:          /test
Document Length:        14 bytes

Concurrency Level:      6
Time taken for tests:   4.048 seconds
Complete requests:      100000
Failed requests:        0
Keep-Alive requests:    100000
Total transferred:      10900000 bytes
HTML transferred:       1400000 bytes
Requests per second:    24702.86 [#/sec] (mean)
Time per request:       0.243 [ms] (mean)
Time per request:       0.040 [ms] (mean, across all concurrent requests)
Transfer rate:          2629.50 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.0      0       1
Processing:     0    0   0.3      0      29
Waiting:        0    0   0.3      0      29
Total:          0    0   0.3      0      29

Percentage of the requests served within a certain time (ms)
  50%      0
  66%      0
  75%      0
  80%      0
  90%      0
  95%      0
  98%      1
  99%      1
 100%     29 (longest request)
```