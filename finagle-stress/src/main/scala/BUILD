scala_library(name='scala',
  dependencies=[
    '3rdparty:caliper',
    '3rdparty:netty',
    '3rdparty:scala-library',
    'finagle/finagle-core',
    'finagle/finagle-http',
    'finagle/finagle-ostrich4',
    'finagle/finagle-stress/src/main/thrift:thrift-scala',
    'finagle/finagle-thrift',
    'finagle/finagle-thriftmux',
    'ostrich',
    'util/util-app',
    'util/util-logging',
  ],
  sources=rglobs('*.scala'),
  provides=artifact(org='com.twitter',
                    name='finagle-stress',
                    repo='pants-support/ivy:internal',),
)

benchmark(name='bench',
  dependencies=[
    ":scala"
  ]
)

jvm_binary(name='stress',
  main='com.twitter.finagle.stress.LoadBalancerTest',
  dependencies=[
    ':scala'
  ]
)


