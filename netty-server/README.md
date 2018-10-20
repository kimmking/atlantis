# netty-server-demo

```
$ java -jar io.github.kimmking.netty.server.NettyServerApplication -Xmx2g -Xms2g 

$ wrk -c12 -t6 -d30s --timeout 1s --latency http://localhost:8088/test
  Running 30s test @ http://localhost:8088/test
    6 threads and 12 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency     3.93ms   26.91ms 460.89ms   97.32%
      Req/Sec    10.33k     2.34k   18.86k    85.91%
    Latency Distribution
       50%  166.00us
       75%  187.00us
       90%  308.00us
       99%  115.04ms
    1827812 requests in 30.10s, 190.00MB read
  Requests/sec:  60724.61
  Transfer/sec:      6.31MB
  
$ wrk -c20 -t4 -d300s --timeout 1s --latency http://localhost:8088/test
  Running 5m test @ http://localhost:8088/test
    4 threads and 20 connections
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency   608.24us    5.21ms 216.64ms   99.15%
      Req/Sec    15.68k     1.26k   27.91k    91.18%
    Latency Distribution
       50%  267.00us
       75%  290.00us
       90%  319.00us
       99%    2.95ms
    18692454 requests in 5.00m, 1.90GB read
  Requests/sec:  62309.08
  Transfer/sec:      6.48MB

```

```
$ ab -k -c 6 -n 1000000 http://localhost:8088/test
  This is ApacheBench, Version 2.3 <$Revision: 1826891 $>
  Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
  Licensed to The Apache Software Foundation, http://www.apache.org/
  
  Benchmarking localhost (be patient)
  Completed 100000 requests
  Completed 200000 requests
  Completed 300000 requests
  Completed 400000 requests
  Completed 500000 requests
  Completed 600000 requests
  Completed 700000 requests
  Completed 800000 requests
  Completed 900000 requests
  Completed 1000000 requests
  Finished 1000000 requests
  
  
  Server Software:        
  Server Hostname:        localhost
  Server Port:            8088
  
  Document Path:          /test
  Document Length:        14 bytes
  
  Concurrency Level:      6
  Time taken for tests:   16.236 seconds
  Complete requests:      1000000
  Failed requests:        0
  Keep-Alive requests:    1000000
  Total transferred:      109000000 bytes
  HTML transferred:       14000000 bytes
  Requests per second:    61591.44 [#/sec] (mean)
  Time per request:       0.097 [ms] (mean)
  Time per request:       0.016 [ms] (mean, across all concurrent requests)
  Transfer rate:          6556.12 [Kbytes/sec] received
  
  Connection Times (ms)
                min  mean[+/-sd] median   max
  Connect:        0    0   0.0      0       0
  Processing:     0    0   0.1      0      31
  Waiting:        0    0   0.1      0      31
  Total:          0    0   0.1      0      31
  
  Percentage of the requests served within a certain time (ms)
    50%      0
    66%      0
    75%      0
    80%      0
    90%      0
    95%      0
    98%      0
    99%      0
   100%     31 (longest request)
```