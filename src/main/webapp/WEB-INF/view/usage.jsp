<!DOCTYPE HTML>

<html>
<head>
<title>Sample Application</title>
</head>
<body>

	<h1>Usage:</h1>

	<pre>
Usage:
 http://${pageContext.request.serverName}/${pageContext.request.contextPath}/SFDaaS/orekit/propagate& 
 &cf=0                    [default: ignore caching and proceed]
 &cf=1                    [Check if in cache, if in cache use. if not put in cache after propagation]
  &ca=127.0.0.1:11211     [Caching address and port]
  &ct=60                  [Default: 60 seconds to store in cache ]
  &ck={KEY}               [User supplied caching key, otherwise use built-in one]
  &sf=1                   [default = null, 1 means use my values ]
  &st=300                 [default = 1800 seconds]
  &t0=yyyymmddThhMMss.sss [only UTC Timezone]
  &tf=yyyymmddThhMMss.sss [only UTC Timezone]
  &r0=[x0,y0,z0]          [only in meters ]
  &v0=[vx0,vy0,vz0]       [only in meters/second ]


 Usage examples:
  1) <a href="http://localhost:8080/SFDaaS/orekit/propagate/usage"
			target="orekit_eg_1">Usage</a>
  2) <a
			href="http://localhost:8080/SFDaaS/orekit/propagate?t0=2010-05-28T12:00:00.000&tf=2011-05-28T12:00:00.000&r0=[3198022.67,2901879.73,5142928.95]&v0=[-6129.640631,4489.647187,1284.511245]"
			target="orekit_eg_2">Propagation</a>
  3) <a
			href="http://localhost:8080/SFDaaS/orekit/propagate?cf=1&t0=2010-05-28T12:00:00.000&tf=2011-05-28T12:00:00.000&r0=[3198022.67,2901879.73,5142928.95]&v0=[-6129.640631,4489.647187,1284.511245]"
			target="orekit_eg_3">Propagation with Memcaching using default values (should return 'HTTP Status 500' error because memcaching server and port are undefined)</a>
  4) <a
			href="http://localhost:8080/SFDaaS/orekit/propagate?cf=1&ca=127.0.0.1:11211&%20127.0.0.1:112112%20192.168.1.101:11211&t0=2010-05-28T12:00:00.000&tf=2011-05-28T12:00:00.000&r0=[3198022.67,2901879.73,5142928.95]&v0=[-6129.640631,4489.647187,1284.511245]"
			target="orekit_eg_4">Propagation with Memcaching using default values with 3 memcaching servers defined.</a>
  5) <a
			href="http://localhost:8080/SFDaaS/orekit/propagate?cf=1&ct=15&ca=127.0.0.1:11211&%20127.0.0.1:112112%20192.168.1.101:11211&t0=2010-05-28T12:00:00.000&tf=2011-05-28T12:00:00.000&r0=[3198022.67,2901879.73,5142928.95]&v0=[-6129.640631,4489.647187,1284.511245]"
			target="orekit_eg_5">Propagation with Memcaching using ct=15, i.e. cache for 15 seconds only</a>
  
  
<a
			href="http://localhost:8080/orekit-ee/propagate?t0=2010-05-29T12:00:00.000Z&tf=2010-05-28T13:00:00.000Z&r0=3198022.67,2901879.73,5142928.95&v0=-6129.640631,4489.647187,1284.511245"
			target="orekit_eg_5">
working example
</a>
</pre>
</body>
</html>