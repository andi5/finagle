package com.twitter.finagle.builder


import org.jboss.netty.channel.{Channels, ChannelPipelineFactory}
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.ssl.SslHandler

import com.twitter.finagle.http.{RequestLifecycleSpy}

class Http extends Codec {
  val clientPipelineFactory: ChannelPipelineFactory =
    new ChannelPipelineFactory {
      def getPipeline() = {
        val pipeline = Channels.pipeline()
        pipeline.addLast("httpCodec", new HttpClientCodec())
        pipeline.addLast("httpDechunker", new HttpChunkAggregator(10<<20))
        pipeline.addLast("lifecycleSpy", RequestLifecycleSpy)
        pipeline
      }
    }

  val serverPipelineFactory =
    new ChannelPipelineFactory {
      def getPipeline() = {
        val pipeline = Channels.pipeline()
        pipeline.addLast("httpCodec", new HttpServerCodec)
        pipeline.addLast("lifecycleSpy", RequestLifecycleSpy)
        pipeline
      }
    }
}

object Http extends Http
